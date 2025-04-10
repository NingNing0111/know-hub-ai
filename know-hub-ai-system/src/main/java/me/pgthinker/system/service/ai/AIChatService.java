package me.pgthinker.system.service.ai;

import me.pgthinker.system.controller.vo.ChatMessageVO;
import me.pgthinker.system.controller.vo.ChatRequestVO;
import me.pgthinker.system.model.entity.ai.ChatMessage;
import org.springframework.ai.chat.model.ChatResponse;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * @Project: me.pgthinker.system.service.ai
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/4/8 05:23
 * @Description:
 */
public interface AIChatService {

	/**
	 * 简单的流式对话
	 * @param chatMessageVO
	 * @return
	 */
	Flux<ChatResponse> simpleChat(ChatMessageVO chatMessageVO);

	/**
	 * 多模态对话
	 * @param chatMessageVO
	 * @return
	 */
	Flux<ChatResponse> multimodalChat(ChatMessageVO chatMessageVO);

	/**
	 * RAG对话
	 * @param chatMessageVO
	 * @param baseIds 知识库ID列表
	 * @return
	 */
	Flux<ChatResponse> simpleRAGChat(ChatMessageVO chatMessageVO, List<String> baseIds);

	/**
	 * 多模态的RAG对话
	 * @param chatMessageVO
	 * @return
	 */
	Flux<ChatResponse> multimodalRAGChat(ChatMessageVO chatMessageVO, List<String> baseIds);

	/**
	 * 统一接口对话
	 * @param chatRequestVO
	 * @return
	 */
	Flux<ChatResponse> unifyChat(ChatRequestVO chatRequestVO);

}
