package me.pgthinker.system.service.ai;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.pgthinker.system.constant.AppConstant;
import me.pgthinker.system.mapper.ChatConversationMapper;
import me.pgthinker.system.mapper.ChatMessageMapper;
import me.pgthinker.system.model.entity.ai.ChatMessage;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	private final ChatMessageService chatMessageService;

	@Transactional(rollbackFor = Exception.class)
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
			List<String> resourceIds = (List)message.getMetadata().get(AppConstant.CHAT_MEDIAS);
			if(resourceIds != null && !resourceIds.isEmpty()) {
				chatMessage.setHasMedia(true);
				chatMessage.setResourceIds(resourceIds);
			}else{
				chatMessage.setHasMedia(false);
				chatMessage.setResourceIds(List.of());
			}
			chatMessageList.add(chatMessage);
		}
		log.debug("对话记录入库：{}", chatMessageList);
		chatMessageMapper.insert(chatMessageList);
	}

	@Override
	public List<Message> get(String conversationId, int lastN) {
		LambdaQueryWrapper<ChatMessage> qw = new LambdaQueryWrapper<>();
		qw.eq(ChatMessage::getConversationId, conversationId);
		qw.orderByDesc(ChatMessage::getCreateTime);
		qw.eq(ChatMessage::getIsClean, false);
		qw.last(" LIMIT " + lastN);

		List<ChatMessage> chatMessages = chatMessageMapper.selectList(qw);
		return chatMessageService.toMessage(chatMessages);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void clear(String conversationId) {
		LambdaQueryWrapper<ChatMessage> qw = new LambdaQueryWrapper<>();
		qw.eq(ChatMessage::getConversationId, conversationId);
		List<ChatMessage> chatMessages = chatMessageMapper.selectList(qw);
		chatMessages.forEach(item->{
			item.setIsClean(true);
		});
		chatMessageMapper.updateById(chatMessages);
	}

}
