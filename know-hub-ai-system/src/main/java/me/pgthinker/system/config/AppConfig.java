package me.pgthinker.system.config;

import lombok.RequiredArgsConstructor;
import me.pgthinker.system.memory.DatabaseChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Project: me.pgthinker.system.config
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/4/8 09:29
 * @Description:
 */
@Configuration
@RequiredArgsConstructor
public class AppConfig {

	@Bean
	public TokenTextSplitter tokenTextSplitter() {
		return new TokenTextSplitter();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
