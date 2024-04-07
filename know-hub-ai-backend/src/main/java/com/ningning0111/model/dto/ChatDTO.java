package com.ningning0111.model.dto;

import java.util.List;

/**
 * @Project: com.ningning0111.model.dto
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/4/2 19:32
 * @Description: chat request dto
 */


public record ChatDTO(List<Message> messages, ChatOptions chatOptions, String prompt) {
    /**
     * @param messages history context message
     * @param chatOptions chat settings
     * @param prompt user's question
     */
}
