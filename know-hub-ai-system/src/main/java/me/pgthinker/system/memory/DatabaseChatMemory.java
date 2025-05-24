package me.pgthinker.system.memory;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.pgthinker.system.constant.AppConstant;
import me.pgthinker.system.mapper.ChatConversationMapper;
import me.pgthinker.system.mapper.ChatMessageMapper;
import me.pgthinker.system.model.entity.ai.ChatMessage;
import me.pgthinker.system.service.ai.ChatMessageService;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.content.Content;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static me.pgthinker.system.constant.AppConstant.CHAT_MAX_LENGTH;
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

	private final ChatMessageService chatMessageService;

	private final int maxMessages = CHAT_MAX_LENGTH;

	@Override
	public List<Message> get(String conversationId) {
		LambdaQueryWrapper<ChatMessage> qw = new LambdaQueryWrapper<>();
		qw.eq(ChatMessage::getConversationId, conversationId);
		qw.orderByAsc(ChatMessage::getCreateTime);
		qw.last(" LIMIT " + maxMessages);
		List<ChatMessage> chatMessages = chatMessageMapper.selectList(qw);
		log.info("context:{}", chatMessages);
		return chatMessageService.toMessage(chatMessages);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void add(String conversationId, List<Message> messages) {
		// log.info("Save Meta:{}", messages.stream().map(Content::getMetadata).toList());
		ArrayList<ChatMessage> chatMessageList = new ArrayList<>();
		for (int i = 0; i < messages.size(); i++) {
			Message message = messages.get(i);
			ChatMessage chatMessage = new ChatMessage();
			chatMessage.setConversationId(conversationId);
			chatMessage.setMessageNo(i + 1);
			chatMessage.setContent(message.getText());
			chatMessage.setRole(message.getMetadata().get(MESSAGE_TYPE).toString());
			List<String> resourceIds = (List) message.getMetadata().get(AppConstant.CHAT_MEDIAS);
			if (resourceIds != null && !resourceIds.isEmpty()) {
				chatMessage.setHasMedia(true);
				chatMessage.setResourceIds(resourceIds);
			}
			else {
				chatMessage.setHasMedia(false);
				chatMessage.setResourceIds(List.of());
			}
			chatMessageList.add(chatMessage);
		}
		chatMessageMapper.insert(chatMessageList);

	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void clear(String conversationId) {
		LambdaQueryWrapper<ChatMessage> qw = new LambdaQueryWrapper<>();
		qw.eq(ChatMessage::getConversationId, conversationId);
		chatMessageMapper.delete(qw);
	}

}
