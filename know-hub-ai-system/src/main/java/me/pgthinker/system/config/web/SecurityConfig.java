package me.pgthinker.system.config.web;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.pgthinker.core.exception.GlobalExceptionHandler;
import me.pgthinker.system.security.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

/**
 * @Project: me.pgthinker.system.config.web
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/3/30 18:44
 * @Description:
 */
@Configuration
@Data
@EnableMethodSecurity
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {

	private final UserDetailsService userDetailsService;

	private final JwtAuthenticationFilter jwtAuthenticationFilter;

	private final SecurityProperties securityProperties;

	private final PasswordEncoder passwordEncoder;

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder);
		return authProvider;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf(AbstractHttpConfigurer::disable);
		List<String> allowList = securityProperties.getAllowList();
		String[] allowUrls = allowList.toArray(new String[0]);
		log.info("allowList ====> {}", allowList);
		httpSecurity
			.authorizeHttpRequests(req -> req.requestMatchers(allowUrls).permitAll().anyRequest().authenticated());
		httpSecurity.authenticationProvider(authenticationProvider())
			.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return httpSecurity.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	public GlobalExceptionHandler globalExceptionHandler() {
		return new GlobalExceptionHandler();
	}

}
