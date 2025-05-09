/*
 * Copyright 2023-2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.pgthinker.system.advisor;

import org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.api.*;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.MessageAggregator;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

/**
 * Memory is retrieved added as a collection of messages to the prompt
 *
 * @author Christian Tzolov
 * @author NingNing0111
 * @since 1.0.0
 *
 * Spring AI 1.0.0-M6 version:
 * userParams在经过MessageChatMemoryAdvisor处理后，进入到ChatMemory无法获取，详见97行代码 Spring
 * AI目前正在准备构建更方便的更改参数的方式,详情参考下面的issues:
 * https://github.com/spring-projects/spring-ai/issues/2701
 * https://github.com/spring-projects/spring-ai/issues/2655
 *
 *
 *
 */
public class MessageChatMemoryAdvisor extends AbstractChatMemoryAdvisor<ChatMemory> {

	public MessageChatMemoryAdvisor(ChatMemory chatMemory) {
		super(chatMemory);
	}

	public MessageChatMemoryAdvisor(ChatMemory chatMemory, String defaultConversationId, int chatHistoryWindowSize) {
		this(chatMemory, defaultConversationId, chatHistoryWindowSize, Advisor.DEFAULT_CHAT_MEMORY_PRECEDENCE_ORDER);
	}

	public MessageChatMemoryAdvisor(ChatMemory chatMemory, String defaultConversationId, int chatHistoryWindowSize,
			int order) {
		super(chatMemory, defaultConversationId, chatHistoryWindowSize, true, order);
	}

	public static Builder builder(ChatMemory chatMemory) {
		return new Builder(chatMemory);
	}

	@Override
	public AdvisedResponse aroundCall(AdvisedRequest advisedRequest, CallAroundAdvisorChain chain) {

		advisedRequest = this.before(advisedRequest);

		AdvisedResponse advisedResponse = chain.nextAroundCall(advisedRequest);

		this.observeAfter(advisedResponse);

		return advisedResponse;
	}

	@Override
	public Flux<AdvisedResponse> aroundStream(AdvisedRequest advisedRequest, StreamAroundAdvisorChain chain) {

		Flux<AdvisedResponse> advisedResponses = this.doNextWithProtectFromBlockingBefore(advisedRequest, chain,
				this::before);

		return new MessageAggregator().aggregateAdvisedResponse(advisedResponses, this::observeAfter);
	}

	private AdvisedRequest before(AdvisedRequest request) {

		String conversationId = this.doGetConversationId(request.adviseContext());

		int chatMemoryRetrieveSize = this.doGetChatMemoryRetrieveSize(request.adviseContext());

		// 1. Retrieve the chat memory for the current conversation.
		List<Message> memoryMessages = this.getChatMemoryStore().get(conversationId, chatMemoryRetrieveSize);

		// 2. Advise the request messages list.
		List<Message> advisedMessages = new ArrayList<>(request.messages());
		advisedMessages.addAll(memoryMessages);

		// 3. Create a new request with the advised messages.
		AdvisedRequest advisedRequest = AdvisedRequest.from(request).messages(advisedMessages).build();

		// 4. Add the new user input to the conversation memory., request.userParams()
		// UserMessage userMessage = new UserMessage(request.userText(), request.media());
		UserMessage userMessage = new UserMessage(request.userText(), request.media(), request.userParams());

		this.getChatMemoryStore().add(this.doGetConversationId(request.adviseContext()), userMessage);

		return advisedRequest;
	}

	private void observeAfter(AdvisedResponse advisedResponse) {

		List<Message> assistantMessages = advisedResponse.response()
			.getResults()
			.stream()
			.map(g -> (Message) g.getOutput())
			.toList();

		this.getChatMemoryStore().add(this.doGetConversationId(advisedResponse.adviseContext()), assistantMessages);
	}

	public static class Builder extends AbstractBuilder<ChatMemory> {

		protected Builder(ChatMemory chatMemory) {
			super(chatMemory);
		}

		public MessageChatMemoryAdvisor build() {
			return new MessageChatMemoryAdvisor(this.chatMemory, this.conversationId, this.chatMemoryRetrieveSize,
					this.order);
		}

	}

}
