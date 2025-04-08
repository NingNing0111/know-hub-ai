package me.pgthinker.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.pgthinker.system.model.entity.ai.ChatMessage;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author pgthinker
 * @description 针对表【chat_message(对话消息)】的数据库操作Mapper
 * @createDate 2025-04-08 04:47:02
 * @Entity generator.domain.ChatMessage
 */
@Mapper
public interface ChatMessageMapper extends BaseMapper<ChatMessage> {

}
