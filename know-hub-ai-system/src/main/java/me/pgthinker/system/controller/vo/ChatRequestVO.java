package me.pgthinker.system.controller.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Project: me.pgthinker.system.controller.vo
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/4/10 08:32
 * @Description:
 */
@Data
public class ChatRequestVO {

	@NotNull(message = "对话ID不能为空")
	private String conversationId;

	@NotNull(message = "对话内容不能为空")
	private String content;

	private List<String> resourceIds;
	private List<String> knowledgeIds;

	@NotNull(message = "对话类型不能为空")
	private String chatType;

}
