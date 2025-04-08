package me.pgthinker.system.controller.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Project: me.pgthinker.system.controller.vo
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/4/8 05:08
 * @Description:
 */
@Data
public class ChatConversationVO {

	private String id;

	private String title;

	private LocalDateTime createTime;

	private List<ChatMessageVO> messages;

}
