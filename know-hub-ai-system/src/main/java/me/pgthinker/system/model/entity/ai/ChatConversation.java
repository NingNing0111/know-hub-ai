package me.pgthinker.system.model.entity.ai;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.pgthinker.core.pojo.BaseEntity;

/**
 * @Project: me.pgthinker.system.model.entity.ai
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/4/8 03:23
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ChatConversation extends BaseEntity {
    private String id;
    /**
     * 系统提示词 标题
     */
    private String title;
    /**
     * 对话人
     */
    private Long userId;
}
