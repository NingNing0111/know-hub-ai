package me.pgthinker.system.service.ai;

import com.baomidou.mybatisplus.extension.service.IService;
import me.pgthinker.system.controller.vo.ChatConversationVO;
import me.pgthinker.system.model.entity.ai.ChatConversation;

import java.util.List;

/**
 * @author pgthinker
 * @description 针对表【chat_conversation(对话消息)】的数据库操作Service
 * @createDate 2025-04-08 04:47:02
 */
public interface ChatConversationService extends IService<ChatConversation> {

	/**
	 * 获取对话记录详情
	 * @param conversationId
	 * @return
	 */
	ChatConversationVO getConversation(String conversationId);

	/**
	 * 创建对话
	 * @param conversation
	 * @return
	 */
	ChatConversationVO createConversation(ChatConversationVO conversation);

	/**
	 * 用户的对话列表
	 * @return
	 */
	List<ChatConversationVO> listConversation();

	/**
	 * 删除对话记录
	 * @param conversationId
	 * @return
	 */
	Boolean removeConversation(String conversationId);

}
