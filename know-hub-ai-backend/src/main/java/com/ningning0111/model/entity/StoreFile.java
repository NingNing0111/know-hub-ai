package com.ningning0111.model.entity;

import com.ningning0111.conver.JpaConverterListJson;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @Project: com.ningning0111.model.entity
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/4/3 21:09
 * @Description:
 */
@Data
@Table
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 文件名称
     */
    @Column(columnDefinition = "TEXT")
    private String fileName;

    /**
     * 该文件分割出的多段向量文本ID
     */
    @Convert(converter = JpaConverterListJson.class)
    @Column(columnDefinition = "TEXT")
    private List<String> vectorId;

    /**
     * 创建时间/上传时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
