package me.pgthinker.system.service.ai.impl;

import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import me.pgthinker.system.mapper.ChatConversationMapper;
import me.pgthinker.system.mapper.ChatMessageMapper;
import me.pgthinker.system.model.entity.ai.ChatMessage;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.AbstractMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.MessageType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.ai.chat.messages.AbstractMessage.MESSAGE_TYPE;

/**
 * @Project: me.pgthinker.system.service.ai.impl
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/4/8 03:03
 * @Description: TODO: 待商榷
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DatabaseChatMemory implements ChatMemory {

	private final ChatMessageMapper chatMessageMapper;

	private final ChatConversationMapper chatConversationMapper;

	@Override
	public void add(String conversationId, List<Message> messages) {
		ArrayList<ChatMessage> chatMessageList = new ArrayList<>();
		for (int i = 0; i < messages.size(); i++) {
			Message message = messages.get(i);
			ChatMessage chatMessage = new ChatMessage();
			chatMessage.setConversationId(conversationId);
			chatMessage.setMessageNo(i);
			chatMessage.setContent(message.getText());
			chatMessage.setRole(message.getMetadata().get(MESSAGE_TYPE).toString());
		}
	}

	@Override
	public List<Message> get(String conversationId, int lastN) {
		return List.of();
	}

	@Override
	public void clear(String conversationId) {

	}

}
