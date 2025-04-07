package me.pgthinker.system.service.ai.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import me.pgthinker.system.mapper.ChatMessageMapper;
import me.pgthinker.system.model.entity.ai.ChatMessage;
import me.pgthinker.system.service.ai.ChatMessageService;
import org.springframework.ai.chat.messages.Message;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author pgthinker
* @description 针对表【chat_message(对话消息)】的数据库操作Service实现
* @createDate 2025-04-08 04:47:02
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl extends ServiceImpl<ChatMessageMapper, ChatMessage>
    implements ChatMessageService {

    @Override
    public List<Message> toMessage(List<ChatMessage> chatMessages) {
        return List.of();
    }

    @Override
    public List<ChatMessage> fromMessage(List<Message> messages) {
        return List.of();
    }
}




