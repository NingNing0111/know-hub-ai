package me.pgthinker.system.security.service;

import java.util.Map;

/**
 * @Project: me.pgthinker.core.service.web
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/3/30 17:24
 * @Description:
 */
public interface JwtService {

	/**
	 * 生成JWT令牌
	 * @param claims 自定义声明
	 * @param subject 主题
	 * @return 生成的JWT令牌
	 */
	String generateToken(Map<String, Object> claims, String subject);

	/**
	 * 生成JWT令牌（使用默认声明）
	 * @param subject 主题
	 * @return 生成的JWT令牌
	 */
	String generateToken(String subject);

	/**
	 * 从令牌中解析主题
	 * @param token JWT令牌
	 * @return 主题
	 */
	String getUsernameFromToken(String token);

	/**
	 * 验证令牌是否有效
	 * @param token JWT令牌
	 * @param subject 主题
	 * @return 是否有效
	 */
	boolean validateToken(String token, String subject);

	/**
	 * 验证令牌是否有效（不验证主题）
	 * @param token JWT令牌
	 * @return 是否有效
	 */
	boolean validateToken(String token);

	/**
	 * 检查令牌是否即将过期（用于无感刷新）
	 * @param token JWT令牌
	 * @return 是否即将过期
	 */
	boolean isTokenExpiringSoon(String token);

	/**
	 * 刷新令牌（生成新令牌并保持声明不变）
	 * @param token 旧令牌
	 * @return 新令牌
	 */
	String refreshToken(String token);

	/**
	 * 获取令牌剩余有效时间（毫秒）
	 * @param token JWT令牌
	 * @return 剩余有效时间（毫秒）
	 */
	long getRemainingTime(String token);

}
