package me.pgthinker.system.controller.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @Project: me.pgthinker.system.controller.vo
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/4/8 07:57
 * @Description:
 */
@Data
public class KnowledgeBaseVO {

	private String id;

	@NotBlank(message = "知识库名称不能为空")
	private String name;

	private String description;

	// 创建人
	private Long author;

	private String authorName;

}
