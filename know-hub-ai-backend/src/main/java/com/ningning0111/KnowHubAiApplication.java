package com.ningning0111;

import org.springframework.ai.autoconfigure.openai.OpenAiAutoConfiguration;
import org.springframework.ai.autoconfigure.vectorstore.pgvector.PgVectorStoreAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        exclude = {
                PgVectorStoreAutoConfiguration.class,
                OpenAiAutoConfiguration.class
        }
)
public class KnowHubAiApplication {

    public static void main(String[] args) {
        SpringApplication.run(KnowHubAiApplication.class, args);
    }

}
