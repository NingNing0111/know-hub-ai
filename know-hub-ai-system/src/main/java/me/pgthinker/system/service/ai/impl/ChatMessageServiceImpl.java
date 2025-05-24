package me.pgthinker.system.service.ai.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.pgthinker.core.common.CoreCode;
import me.pgthinker.core.exception.BusinessException;
import me.pgthinker.system.mapper.ChatMessageMapper;
import me.pgthinker.system.model.entity.ai.ChatMessage;
import me.pgthinker.system.service.ai.ChatMessageService;
import me.pgthinker.system.service.ai.OriginFileResourceService;
import org.springframework.ai.chat.messages.*;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * @author pgthinker
 * @description 针对表【chat_message(对话消息)】的数据库操作Service实现
 * @createDate 2025-04-08 04:47:02
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl extends ServiceImpl<ChatMessageMapper, ChatMessage> implements ChatMessageService {

	private final OriginFileResourceService originFileResourceService;

	@Override
	public List<Message> toMessage(List<ChatMessage> chatMessages) {
		// 根据messageNo排序 从低到高
		chatMessages.sort(Comparator.comparingInt(ChatMessage::getMessageNo));

		return chatMessages.stream().map(chatMessage -> {
			String role = chatMessage.getRole().toLowerCase();

			Message message = switch (role) {
				case "user" -> {
					UserMessage userMessage = new UserMessage(chatMessage.getContent());
					userMessage.mutate().media(originFileResourceService.fromResourceId(chatMessage.getResourceIds()));
					yield userMessage;
				}
				case "system" -> new SystemMessage(chatMessage.getContent());
				case "assistant" -> new AssistantMessage(chatMessage.getContent(), Map.of(), List.of(),
						originFileResourceService.fromResourceId(chatMessage.getResourceIds()));
				default -> throw new BusinessException(CoreCode.SYSTEM_ERROR, "未知消息类型");
			};
			return message;
		}).toList();
	}

	@Override
	public List<ChatMessage> fromMessage(List<Message> messages) {
		return List.of();
	}

}
