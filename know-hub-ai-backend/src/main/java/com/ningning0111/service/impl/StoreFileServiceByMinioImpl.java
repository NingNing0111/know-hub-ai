package com.ningning0111.service.impl;

import com.ningning0111.common.BaseResponse;
import com.ningning0111.model.dto.QueryFileDTO;
import com.ningning0111.service.StoreFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author ：何汉叁
 * @date ：2024/4/7 21:16
 * @description：知识库（Minio文件存储服务版）
 * 写不动了以后慢慢写
 */
@Service("storeFileServiceByMinioImpl")
@Slf4j
public class StoreFileServiceByMinioImpl implements StoreFileService {
    @Override
    public BaseResponse queryPage(QueryFileDTO request) {
        return null;
    }

    @Override
    public BaseResponse deleteFiles(List<Long> ids) {
        return null;
    }

    @Override
    public BaseResponse fileStore(MultipartFile file) {
        return null;
    }

    @Override
    public BaseResponse filesStore(List<MultipartFile> files) {
        return null;
    }

    @Override
    public VectorStore randomGetVectorStore() {
        return null;
    }
}