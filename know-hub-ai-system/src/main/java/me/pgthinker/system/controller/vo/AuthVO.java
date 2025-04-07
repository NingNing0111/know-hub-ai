package me.pgthinker.system.controller.vo;

import lombok.Data;

import java.util.List;

/**
 * @Project: me.pgthinker.model.vo
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/3/13 21:28
 * @Description:
 */
@Data
public class AuthVO {

	private String username;

	private String token;

	private List<String> roles; // 角色

}
