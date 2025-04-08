package me.pgthinker.system.service.ai.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.pgthinker.system.controller.vo.ChatConversationVO;
import me.pgthinker.system.mapper.ChatConversationMapper;
import me.pgthinker.system.model.entity.ai.ChatConversation;
import me.pgthinker.system.service.ai.ChatConversationService;
import org.springframework.stereotype.Service;

/**
* @author pgthinker
* @description 针对表【chat_conversation(对话消息)】的数据库操作Service实现
* @createDate 2025-04-08 04:47:02
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class ChatConversationServiceImpl extends ServiceImpl<ChatConversationMapper, ChatConversation> implements ChatConversationService {

    @Override
    public ChatConversationVO getConversation(String conversationId) {
        return null;
    }

    @Override
    public ChatConversationVO createConversation(ChatConversationVO conversation) {
        return null;
    }
}




