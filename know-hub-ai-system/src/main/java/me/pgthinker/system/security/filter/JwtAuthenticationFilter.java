package me.pgthinker.system.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.pgthinker.core.common.CoreCode;
import me.pgthinker.core.common.ErrorCode;
import me.pgthinker.core.exception.BusinessException;
import me.pgthinker.system.config.web.SecurityProperties;
import me.pgthinker.system.security.service.JwtService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @Project: me.pgthinker.system.security.filter
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/3/30 18:09
 * @Description: JWT 认证 Filter
 */
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtService jwtService;

	private final UserDetailsService userDetailsService;

	private final AntPathMatcher pathMatcher = new AntPathMatcher();

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String servletPath = request.getServletPath();
		// 1. 从Header提取Token
		final String authHeader = request.getHeader("Authorization");
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			// 如果是AI对话 没有jwt 则无权限
			if (pathMatcher.match("/ai/chat/**", servletPath)) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			}
			else {
				filterChain.doFilter(request, response);
			}
			return;
		}

		final String jwt = authHeader.substring(7);
		final String username = jwtService.getUsernameFromToken(jwt);

		// 2. 验证Token有效性
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			try {
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);

				if (jwtService.validateToken(jwt, username)) {
					// 3. 构建Authentication对象并存入SecurityContext
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
							null, userDetails.getAuthorities());
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authToken);

					// 4. 无感刷新逻辑
					if (jwtService.isTokenExpiringSoon(jwt)) {
						String newToken = jwtService.refreshToken(jwt);
						response.setHeader("New-Access-Token", newToken);
					}
				}
			}
			catch (Exception e) {
				e.printStackTrace();
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			}
		}
		filterChain.doFilter(request, response);
	}

}
