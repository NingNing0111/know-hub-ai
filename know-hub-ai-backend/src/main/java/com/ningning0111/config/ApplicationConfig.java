package com.ningning0111.config;

import com.ningning0111.service.StoreService;
import lombok.Data;
import org.springframework.ai.autoconfigure.openai.OpenAiAutoConfiguration;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Project: com.ningning0111.config
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/4/2 13:52
 * @Description:
 */
@Configuration
@Data
public class ApplicationConfig {
    /**
     * ETL中的DocumentTransformer的实现，将文本数据源转换为多个分割段落
     * @return
     */
    @Bean
    public TokenTextSplitter tokenTextSplitter() {
        return new TokenTextSplitter();
    }

    @Bean
    public VectorStore vectorStore(StoreService service) {
        return service.randomGetVectorStore();
    }
}
