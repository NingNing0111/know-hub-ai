package com.ningning0111.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Project: com.ningning0111
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/4/2 16:56
 * @Description:
 */
@Data
@Table
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OneApi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否禁用
     */
    private Boolean disable;

    private String baseUrl;
    private String apiKey;
    private String describe;

}
