package com.ningning0111.model.dto;

import java.util.List;

/**
 * @Project: com.ningning0111.model.dto
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/4/2 19:32
 * @Description: 聊天记录
 */
public record ChatDTO(List<Message> messages, ChatOptions chatOptions, String prompt) {
}
