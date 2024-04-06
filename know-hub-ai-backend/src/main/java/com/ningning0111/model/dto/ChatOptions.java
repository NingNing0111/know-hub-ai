package com.ningning0111.model.dto;

import lombok.Data;
import org.springframework.ai.openai.OpenAiChatOptions;

/**
 * @Project: com.ningning0111.model.dto
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/4/2 19:36
 * @Description:
 */
public record ChatOptions(String model,Integer maxHistoryLength,String chatType,Float temperature) {}

