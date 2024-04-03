package com.ningning0111.model.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Project: com.ningning0111.model.dto
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/4/3 22:29
 * @Description:
 */
public record UploadFileRequest(List<MultipartFile> files) {
}
