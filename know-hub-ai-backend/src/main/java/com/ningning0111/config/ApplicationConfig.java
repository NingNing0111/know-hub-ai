package com.ningning0111.config;

import com.ningning0111.service.StoreFileService;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Objects;


/**
 * @Project: com.ningning0111.config
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/4/2 13:52
 * @Description:
 */
@Configuration
public class ApplicationConfig {

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlAuto;
    private final JdbcTemplate jdbcTemplate;

    public ApplicationConfig(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * ETL中的DocumentTransformer的实现，将文本数据源转换为多个分割段落
     * @return
     */
    @Bean
    public TokenTextSplitter tokenTextSplitter() {
        return new TokenTextSplitter();
    }

    /**
     * 自动删除向量数据库中的向量数据表并创建
     * @param service
     * @param jdbcTemplate
     * @return
     */
    @Bean
    public VectorStore vectorStore( StoreFileService service, JdbcTemplate jdbcTemplate) {
        if(Objects.equals(ddlAuto, "create")){
            jdbcTemplate.execute("drop table if exists vector_store cascade");
        }
        return service.randomGetVectorStore();
    }







}
