package me.pgthinker.system.service.ai;

import me.pgthinker.system.controller.vo.ChatMessageVO;
import me.pgthinker.system.model.entity.ai.ChatMessage;
import org.springframework.ai.chat.model.ChatResponse;
import reactor.core.publisher.Flux;

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
     * RAG对话
     * @param chatMessageVO
     * @param baseId 知识库ID
     * @return
     */
    Flux<ChatResponse> ragChat(ChatMessageVO chatMessageVO, String baseId);
}
