package me.pgthinker.system.service.ai;

import me.pgthinker.system.model.entity.ai.ChatMessage;
import org.springframework.ai.chat.messages.Message;

import java.util.List;

/**
 * @Project: me.pgthinker.system.service.ai
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/4/8 03:33
 * @Description:
 */
public interface ChatMessageService {
    /**
     * 将ChatMessage转换为Spring AI的Message
     * @param chatMessages
     * @return
     */
     List<Message> toMessage(List<ChatMessage> chatMessages);

    /**
     * 将Spring AI的Message 转换为ChatMessage
     * @param messages
     * @return
     */
    List<ChatMessage> fromMessage(List<Message> messages);
}
