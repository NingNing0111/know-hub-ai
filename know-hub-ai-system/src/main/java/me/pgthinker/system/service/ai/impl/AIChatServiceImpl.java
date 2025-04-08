package me.pgthinker.system.service.ai.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.pgthinker.system.controller.vo.ChatMessageVO;
import me.pgthinker.system.service.ai.AIChatService;
import me.pgthinker.system.service.ai.ChatConversationService;
import me.pgthinker.system.service.ai.LLMService;
import me.pgthinker.system.service.ai.OriginFileResourceService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.Media;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static me.pgthinker.system.constant.AppConstant.CHAT_CONVERSATION_NAME;
import static me.pgthinker.system.constant.AppConstant.CHAT_MAX_LENGTH;

/**
 * @Project: me.pgthinker.system.service.ai.impl
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/4/8 05:25
 * @Description:
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AIChatServiceImpl implements AIChatService {

	private final LLMService llmService;

	private final OriginFileResourceService originFileResourceService;

	private final ChatConversationService chatConversationService;

	private final DatabaseChatMemory databaseChatMemory;

	@Override
	public Flux<ChatResponse> simpleChat(ChatMessageVO chatMessageVO) {
		ChatModel chatModel = llmService.getChatModel();
		// 构建Meta信息
		ChatClient chatClient = ChatClient.builder(chatModel).build();
		return chatClient.prompt().user(user -> {
			user.param(CHAT_CONVERSATION_NAME, chatMessageVO.getConversationId());
			user.text(chatMessageVO.getContent());
		})
			.advisors(MessageChatMemoryAdvisor.builder(databaseChatMemory)
				.chatMemoryRetrieveSize(CHAT_MAX_LENGTH)
				.conversationId(chatMessageVO.getConversationId())
				.build())
			.stream()
			.chatResponse();
	}

	@Override
	public Flux<ChatResponse> multimodalChat(ChatMessageVO chatMessageVO) {
		ChatModel chatModel = llmService.getChatModel();
		Message userMessage;
		List<String> resourceIds = chatMessageVO.getResourceIds();
		if (resourceIds != null && !resourceIds.isEmpty()) {
			List<Media> medias = originFileResourceService.fromResourceId(resourceIds);
			userMessage = new UserMessage(chatMessageVO.getContent(), medias);
		}
		else {
			userMessage = new UserMessage(chatMessageVO.getContent());
		}

		ChatClient chatClient = ChatClient.builder(chatModel)
			.defaultAdvisors(MessageChatMemoryAdvisor.builder(new InMemoryChatMemory()).build())
			.build();
		chatClient.prompt("hi").call().content();
		return null;
	}

	@Override
	public Flux<ChatResponse> simpleRAGChat(ChatMessageVO chatMessageVO, String baseId) {
		return null;
	}

	@Override
	public Flux<ChatResponse> multimodalRAGChat(ChatMessageVO chatMessageVO, String baseId) {
		return null;
	}

}
