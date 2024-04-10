package com.ningning0111.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ：何汉叁
 * @date ：2024/4/10 19:41
 * @description：TODO
 */
@Data
@Table
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Logs implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 调用的接口
     */
    private String methods;
    private String ip;
    /**
     * 操作时间
     */
    private String opertime;
    /**
     * 具体操作
     */
    private String ddesc;
}