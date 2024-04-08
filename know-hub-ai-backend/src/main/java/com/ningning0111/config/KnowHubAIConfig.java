package com.ningning0111.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Project: com.ningning0111.config
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/4/8 18:52
 * @Description:
 */
@ConfigurationProperties(prefix = "know-hub-ai")
@Configuration
@Data
public class KnowHubAIConfig {
    /**
     * 是否开启MinIO
     */
    private boolean minioEnabled;


}
