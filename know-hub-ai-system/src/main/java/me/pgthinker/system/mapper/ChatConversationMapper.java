package me.pgthinker.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.pgthinker.system.model.entity.ai.ChatConversation;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author pgthinker
 * @description 针对表【chat_conversation(对话消息)】的数据库操作Mapper
 * @createDate 2025-04-08 04:47:02
 * @Entity generator.domain.ChatConversation
 */
@Mapper
public interface ChatConversationMapper extends BaseMapper<ChatConversation> {

}
