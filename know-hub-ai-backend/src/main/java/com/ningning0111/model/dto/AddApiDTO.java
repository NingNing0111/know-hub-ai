package com.ningning0111.model.dto;

import com.ningning0111.common.ApplicationConstant;

/**
 * @Project: com.ningning0111.model.dto
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/4/2 17:36
 * @Description:
 */
public record AddApiDTO(String baseUrl, String apiKey, String describe) {
    /**
     *
     * @param baseUrl default value: https://api.openai.com
     * @param apiKey required
     * @param describe optional
     */
    public AddApiDTO(String baseUrl, String apiKey, String describe){
        this.baseUrl = baseUrl == null ? ApplicationConstant.DEFAULT_BASE_URL : baseUrl;
        this.apiKey = apiKey;
        this.describe = describe;
    }
}
