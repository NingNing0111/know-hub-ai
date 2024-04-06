package com.ningning0111;

import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class KnowHubAiApplicationTests {

    @Autowired
    private ChatClient chatClient;

    @Test
    void contextLoads() {
        String hi = chatClient.call("鲁迅为什么打周树人");
        System.out.println(hi);
    }


}
