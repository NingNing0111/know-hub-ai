package me.pgthinker.system.service.ai.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
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
import me.pgthinker.system.service.ai.ChatMessageService;
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

	@Transactional(rollbackFor = Exception.class)
	@Override
	public ChatConversationVO getConversation(String conversationId) {
		LambdaQueryWrapper<ChatMessage> qw = new LambdaQueryWrapper<>();
		qw.eq(ChatMessage::getConversationId, conversationId);
		qw.orderByAsc(ChatMessage::getMessageNo);
		List<ChatMessage> chatMessages = chatMessageMapper.selectList(qw);
		List<ChatMessageVO> chatMessageVOS = transferChatMessage(chatMessages);
		ChatConversation chatConversation = this.getById(conversationId);
		ChatConversationVO chatConversationVO = new ChatConversationVO();
		chatConversationVO.setId(chatConversation.getId());
		chatConversationVO.setMessages(chatMessageVOS);
		chatConversationVO.setTitle(chatConversation.getTitle());
		chatConversationVO.setCreateTime(chatConversation.getCreateTime());
		return chatConversationVO;
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

	public List<ChatMessageVO> transferChatMessage(List<ChatMessage> chatMessages) {
		return chatMessages.stream().map(item -> {
			ChatMessageVO chatMessageVO = new ChatMessageVO();
			BeanUtils.copyProperties(item, chatMessageVO);
			return chatMessageVO;
		}).toList();
	}

}
