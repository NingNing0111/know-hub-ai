package me.pgthinker.system.service.ai.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.pgthinker.system.controller.vo.ChatMessageVO;
import me.pgthinker.system.service.ai.AIChatService;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

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
    @Override
    public Flux<ChatResponse> simpleChat(ChatMessageVO chatMessageVO) {
        return null;
    }

    @Override
    public Flux<ChatResponse> ragChat(ChatMessageVO chatMessageVO, String baseId) {
        return null;
    }
}
