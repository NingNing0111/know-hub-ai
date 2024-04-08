package com.ningning0111.service;

import com.ningning0111.common.BaseResponse;
import com.ningning0111.model.dto.QueryFileDTO;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Project: com.ningning0111.service
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/4/5 20:03
 * @Description:
 */
public interface StoreFileService {
    // 分页查询所有上传的文件
    BaseResponse queryPage(QueryFileDTO request);

    // 删除文件
    BaseResponse deleteFiles(List<Long> ids);

    // 上传文件
    BaseResponse fileStore(MultipartFile file);

    BaseResponse filesStore(List<MultipartFile> files);

    // 初始化向量数据库操作接口
    VectorStore randomGetVectorStore();

}
