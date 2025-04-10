package me.pgthinker.system.service.ai.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.pgthinker.system.controller.vo.ChatConversationVO;
import me.pgthinker.system.controller.vo.ChatMessageVO;
import me.pgthinker.system.mapper.ChatConversationMapper;
import me.pgthinker.system.mapper.ChatMessageMapper;
import me.pgthinker.system.model.entity.ai.ChatConversation;
import me.pgthinker.system.model.entity.ai.ChatMessage;
import me.pgthinker.system.service.ai.ChatConversationService;
import me.pgthinker.system.utils.SecurityFrameworkUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pgthinker
 * @description 针对表【chat_conversation(对话消息)】的数据库操作Service实现
 * @createDate 2025-04-08 04:47:02
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ChatConversationServiceImpl extends ServiceImpl<ChatConversationMapper, ChatConversation>
		implements ChatConversationService {

	private final ChatMessageMapper chatMessageMapper;

	@Override
	public ChatConversationVO getConversation(String conversationId) {
		ChatConversation chatConversation = this.getById(conversationId);
		return transferChatConversation(List.of(chatConversation)).get(0);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public ChatConversationVO createConversation(ChatConversationVO conversation) {
		String title = conversation.getTitle();
		if (title.length() > 16) {
			title = title.substring(0, 16);
		}
		ChatConversation chatConversation = new ChatConversation();
		chatConversation.setTitle(title);
		chatConversation.setUserId(SecurityFrameworkUtil.getCurrUserId());
		this.saveOrUpdate(chatConversation);
		ChatConversationVO chatConversationVO = new ChatConversationVO();
		chatConversationVO.setId(chatConversation.getId());
		chatConversationVO.setCreateTime(chatConversation.getCreateTime());
		chatConversationVO.setTitle(title);
		chatConversationVO.setMessages(new ArrayList<>());
		return chatConversationVO;
	}

	@Override
	public List<ChatConversationVO> listConversation() {
		LambdaQueryWrapper<ChatConversation> qw = new LambdaQueryWrapper<>();
		qw.orderByDesc(ChatConversation::getCreateTime);
		qw.eq(ChatConversation::getUserId, SecurityFrameworkUtil.getCurrUserId());
		qw.last(" LIMIT 30");
		List<ChatConversation> list = this.list(qw);
		return transferChatConversation(list);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Boolean removeConversation(String conversationId) {
		return this.removeById(conversationId);
	}

	public List<ChatConversationVO> transferChatConversation(List<ChatConversation> chatConversations) {
		return chatConversations.stream().map(item -> {
			LambdaQueryWrapper<ChatMessage> qw = new LambdaQueryWrapper<>();
			qw.eq(ChatMessage::getConversationId, item.getId());
			qw.orderByAsc(ChatMessage::getCreateTime);
			List<ChatMessage> chatMessages = chatMessageMapper.selectList(qw);
			List<ChatMessageVO> chatMessageVOS = this.transferChatMessage(chatMessages);

			ChatConversationVO chatConversationVO = new ChatConversationVO();
			chatConversationVO.setId(item.getId());
			chatConversationVO.setTitle(item.getTitle());
			chatConversationVO.setCreateTime(item.getCreateTime());
			chatConversationVO.setMessages(chatMessageVOS);
			return chatConversationVO;
		}).toList();
	}

	public List<ChatMessageVO> transferChatMessage(List<ChatMessage> chatMessages) {
		return chatMessages.stream().map(item -> {
			ChatMessageVO chatMessageVO = new ChatMessageVO();
			BeanUtils.copyProperties(item, chatMessageVO);
			return chatMessageVO;
		}).toList();
	}

}
