package com.ningning0111.service;

import com.ningning0111.common.BaseResponse;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Project: com.ningning0111.service
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/4/2 16:44
 * @Description:
 */
public interface StoreService {
    BaseResponse fileStore(MultipartFile file);

    BaseResponse filesStore(List<MultipartFile> files);

    VectorStore randomGetVectorStore();
}
