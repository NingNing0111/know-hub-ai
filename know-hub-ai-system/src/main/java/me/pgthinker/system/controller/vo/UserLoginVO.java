package me.pgthinker.system.controller.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @Project: me.pgthinker.model.vo
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/3/13 21:27
 * @Description:
 */
@Data
public class UserLoginVO {

	@NotBlank(message = "用户名不能为空")
	@Size(min = 5, max = 30, message = "用户名长度必须介于5到30之间")
	private String username;

	@NotBlank(message = "用户名不能为空")
	@Size(min = 6, max = 24, message = "密码长度必须介于6到24之间")
	private String password;

}
