package me.pgthinker.system.config.web;

import lombok.Data;
import org.postgresql.util.PasswordUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Project: me.pgthinker.security
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/3/13 21:04
 * @Description:
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "security")
public class SecurityProperties {

	/**
	 * 白名单
	 */
	private List<String> allowList = new ArrayList<>();

	/**
	 * JWT 密钥
	 */
	private String secret = UUID.randomUUID().toString();

	/**
	 * 盐值
	 */
	private String salt = UUID.randomUUID().toString();

	/**
	 * JWT过期时间
	 */
	private long expiration = 24 * 60 * 60; // a day

	/**
	 * 刷新Token的过期时间
	 */
	private long refreshExpiration = 7 * 24 * 60 * 60; // 7 day

	/**
	 * 默认密码
	 */
	private String password = UUID.randomUUID().toString();

	/**
	 * 是否初始化
	 */
	private Boolean adminInit = false;

}
