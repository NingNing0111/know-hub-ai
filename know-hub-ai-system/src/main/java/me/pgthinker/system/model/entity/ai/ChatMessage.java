package me.pgthinker.system.model.entity.ai;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.pgthinker.core.pojo.BaseEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Project: me.pgthinker.system.model.entity.ai
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/4/8 03:06
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "chat_message", autoResultMap = true)
public class ChatMessage extends BaseEntity{

    /**
     * 聊天信息ID
     */
    @TableId(type = IdType.AUTO)
    private String id;
    /**
     * 所属对话
     */
    private String conversationId;
    /**
     * 消息序号
     */
    private Integer messageNo;
    /**
     * 对话内容
     */
    private String content;
    /**
     * 角色
     */
    private String role;
    /**
     * 对话是否附带资源 资源一般就包含图片、文件、视频等等
     */
    private Boolean hasMedia;
    /**
     * 附带的资源Id, 会附带多个
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> resourceIds = new ArrayList<>();


}
