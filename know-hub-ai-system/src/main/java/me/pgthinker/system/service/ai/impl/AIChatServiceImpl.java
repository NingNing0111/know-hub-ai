package me.pgthinker.system.service.ai.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.pgthinker.system.controller.vo.ChatMessageVO;
import me.pgthinker.system.mapper.KnowledgeBaseMapper;
import me.pgthinker.system.model.entity.ai.KnowledgeBase;
import me.pgthinker.system.model.entity.user.SystemUser;
import me.pgthinker.system.service.ai.*;
import me.pgthinker.system.utils.SecurityFrameworkUtil;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.model.Media;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

import static me.pgthinker.system.constant.AppConstant.*;

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

	private final DatabaseChatMemory databaseChatMemory;

	private final KnowledgeBaseMapper knowledgeBaseMapper;

	@Value("classpath:prompts/RAG.txt")
	private Resource ragPromptResource;

	@Override
	public Flux<ChatResponse> simpleChat(ChatMessageVO chatMessageVO) {
		ChatModel chatModel = llmService.getChatModel();
		// 构建Meta信息
		ChatClient chatClient = ChatClient.builder(chatModel).build();

		return chatClient.prompt().user(user -> {
			user.param(CHAT_CONVERSATION_NAME, chatMessageVO.getConversationId());
			user.text(chatMessageVO.getContent());
		})
			.advisors(new SimpleLoggerAdvisor(),
					MessageChatMemoryAdvisor.builder(databaseChatMemory)
						.chatMemoryRetrieveSize(CHAT_MAX_LENGTH)
						.conversationId(chatMessageVO.getConversationId())
						.build())
			.stream()
			.chatResponse();
	}

	@Override
	public Flux<ChatResponse> multimodalChat(ChatMessageVO chatMessageVO) {
		ChatModel chatModel = llmService.getMultimodalModel();
		List<String> resourceIds = chatMessageVO.getResourceIds();
		ChatClient chatClient = ChatClient.builder(chatModel).build();
		return chatClient.prompt().user(user -> {
			user.param(CHAT_CONVERSATION_NAME, chatMessageVO.getConversationId());
			user.param(CHAT_MEDIAS, chatMessageVO.getResourceIds());
			user.text(chatMessageVO.getContent());
			if (resourceIds != null && !resourceIds.isEmpty()) {
				List<Media> medias = originFileResourceService.fromResourceId(resourceIds);
				user.media(medias.toArray(new Media[0]));
			}
		})
			.advisors(new SimpleLoggerAdvisor(),
					MessageChatMemoryAdvisor.builder(databaseChatMemory)
						.conversationId(chatMessageVO.getConversationId())
						.chatMemoryRetrieveSize(CHAT_MAX_LENGTH)
						.build())
			.stream()
			.chatResponse();
	}

	@Override
	public Flux<ChatResponse> simpleRAGChat(ChatMessageVO chatMessageVO, String baseId) {
		ChatModel chatModel = llmService.getChatModel();
		// 构建Meta信息
		ChatClient chatClient = ChatClient.builder(chatModel).build();
		PromptTemplate template = new PromptTemplate(ragPromptResource);
		String ragTemplate = template.getTemplate();

		// 向量查询条件
		SearchRequest searchRequest = SearchRequest.builder()
			.topK(RAG_TOP_K)
			.query(chatMessageVO.getContent())
			.filterExpression(buildBaseAccessFilter(baseId))
			.build();

		return chatClient.prompt().user(user -> {
			user.param(CHAT_CONVERSATION_NAME, chatMessageVO.getConversationId());
			user.text(chatMessageVO.getContent());
		})
			.advisors(new SimpleLoggerAdvisor(),
					QuestionAnswerAdvisor.builder(llmService.getVectorStore())
						.userTextAdvise(ragTemplate)
						.searchRequest(searchRequest)
						.build(),
					MessageChatMemoryAdvisor.builder(databaseChatMemory)
						.chatMemoryRetrieveSize(CHAT_MAX_LENGTH)
						.conversationId(chatMessageVO.getConversationId())
						.build()

			)
			.stream()
			.chatResponse();
	}

	@Override
	public Flux<ChatResponse> multimodalRAGChat(ChatMessageVO chatMessageVO, String baseId) {
		return null;
	}

	// meta ==> { "user_id"、"knowledge_base_id"、"document_id"}
	private String buildBaseAccessFilter(String knowledgeBaseId) {
		SystemUser user = SecurityFrameworkUtil.getLoginUser();
		StringBuilder metaFilterSqlSb = new StringBuilder();
		metaFilterSqlSb.append(" 0 = 1 ");
		// 防止SQL注入
		KnowledgeBase knowledgeBase = knowledgeBaseMapper.selectById(knowledgeBaseId);
		if (knowledgeBase != null) {
			metaFilterSqlSb.append("OR knowledge_base_id = ").append(knowledgeBaseId);
		}

		return metaFilterSqlSb.toString();
	}

}
