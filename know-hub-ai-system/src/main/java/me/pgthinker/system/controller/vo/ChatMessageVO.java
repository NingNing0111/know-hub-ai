package me.pgthinker.system.controller.vo;

import lombok.Data;

import java.util.List;

/**
 * @Project: me.pgthinker.system.controller.vo
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/4/8 05:07
 * @Description:
 */
@Data
public class ChatMessageVO {

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


	private List<String> resourceIds;

	/**
	 * 资源列表
	 */
	private List<ResourceVO> resources;



}
