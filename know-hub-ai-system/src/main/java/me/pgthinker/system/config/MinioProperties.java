package me.pgthinker.system.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Project: me.pgthinker.config
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/3/13 21:12
 * @Description:
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "minio")
public class MinioProperties {

	private String endpoint;

	private String accessKey;

	private String secretKey;

	private String defaultBucket = "default";

	private int defaultExpiry = 3600; // 默认1小时有效期

}
