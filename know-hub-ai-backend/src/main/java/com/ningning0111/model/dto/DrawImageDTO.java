package com.ningning0111.model.dto;

/**
 * @Project: com.ningning0111.model.dto
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/4/8 21:16
 * @Description: 绘图的请求对象
 */

public record DrawImageDTO(String prompt,DrawOptions options) {

/**
 *
 * @param prompt 图片描述
 * @param options 图片配置
 */
}
