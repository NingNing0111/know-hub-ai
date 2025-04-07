package me.pgthinker.system.security.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import me.pgthinker.system.config.web.SecurityProperties;
import me.pgthinker.system.security.service.JwtService;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Project: me.pgthinker.system.security.service.impl
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/3/30 17:31
 * @Description:
 */
@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

	private final SecurityProperties securityProperties;

	@Override
	public String generateToken(Map<String, Object> claims, String subject) {
		return buildToken(subject, claims, securityProperties.getExpiration());
	}

	@Override
	public String generateToken(String subject) {
		return buildToken(subject, new HashMap<>(), securityProperties.getRefreshExpiration());
	}

	/**
	 * 构建 JWT Token
	 */
	private String buildToken(String subject, Map<String, Object> claims, long expiration) {
		return Jwts.builder()
			.setClaims(claims)
			.setSubject(subject)
			.setIssuedAt(new Date())
			.setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
			.signWith(getSecretKey())
			.compact();
	}

	@Override
	public String getUsernameFromToken(String token) {
		Claims claims = parseToken(token);
		return claims.getSubject();
	}

	/**
	 * 解析 JWT Token
	 */
	public Claims parseToken(String token) {
		return Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parseClaimsJws(token).getBody();
	}

	@Override
	public boolean validateToken(String token, String subject) {
		try {
			Claims claims = parseToken(token);
			return claims.getSubject().equals(subject) && !isTokenExpired(claims);
		}
		catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean validateToken(String token) {
		try {
			parseToken(token);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean isTokenExpiringSoon(String token) {
		Claims claims = parseToken(token);
		long expirationTime = claims.getExpiration().getTime();
		long currentTime = System.currentTimeMillis();
		long threshold = securityProperties.getExpiration() * 1000L / 4; // 过期前25%时间触发刷新
		return (expirationTime - currentTime) < threshold;
	}

	@Override
	public String refreshToken(String token) {
		Claims claims = parseToken(token);
		return buildToken(claims.getSubject(), new HashMap<>(claims), securityProperties.getExpiration());
	}

	@Override
	public long getRemainingTime(String token) {
		Claims claims = parseToken(token);
		return claims.getExpiration().getTime() - System.currentTimeMillis();
	}

	private boolean isTokenExpired(Claims claims) {
		return claims.getExpiration().before(new Date());
	}

	/**
	 * 使用 PBKDF2 派生安全密钥（256位）
	 */
	public SecretKey getSecretKey() {
		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			KeySpec spec = new PBEKeySpec(securityProperties.getSecret().toCharArray(),
					securityProperties.getSalt().getBytes(StandardCharsets.UTF_8), 10000, // 迭代次数
					256 // 密钥长度（256位 = 32字节）
			);
			byte[] derivedKey = factory.generateSecret(spec).getEncoded();
			return new SecretKeySpec(derivedKey, "HmacSHA256");
		}
		catch (Exception e) {
			throw new RuntimeException("Failed to derive JWT key", e);
		}
	}

}
