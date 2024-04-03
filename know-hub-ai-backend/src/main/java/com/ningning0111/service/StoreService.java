package com.ningning0111.service;

import com.ningning0111.common.BaseResponse;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Project: com.ningning0111.service
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/4/2 16:44
 * @Description:
 */
public interface StoreService {
    BaseResponse pdfStore(MultipartFile file);

    BaseResponse txtStore(MultipartFile file);

    BaseResponse excelStore(MultipartFile file);

    BaseResponse htmlStore(MultipartFile file);

    BaseResponse jsonStore(MultipartFile file);

    VectorStore randomGetVectorStore();
}
