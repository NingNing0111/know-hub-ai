package com.ningning0111.service;

import com.ningning0111.common.BaseResponse;
import com.ningning0111.model.dto.ChatRequest;
import org.springframework.ai.chat.ChatResponse;
import reactor.core.publisher.Flux;

/**
 * @Project: com.ningning0111.service
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/4/2 16:38
 * @Description:
 */
public interface ChatService {
    Flux<ChatResponse> simpleChat(ChatRequest chatRequest);

    Flux<ChatResponse> ragChat(ChatRequest chatRequest);

    BaseResponse noStreamSimpleChat(ChatRequest chatRequest);

    BaseResponse noStreamRagChat(ChatRequest chatRequest);
}
