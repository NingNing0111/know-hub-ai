package com.ningning0111.service;

import com.ningning0111.common.BaseResponse;
import com.ningning0111.model.dto.AddApiRequest;
import com.ningning0111.model.entity.OneApi;

/**
 * @Project: com.ningning0111.service
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/4/2 17:09
 * @Description:
 */
public interface OneApiService {
    /**
     * 加载OneApi列表
     */
    void reloadOneApiInfo();

    OneApi randomGetOne();

    BaseResponse queryList();

    BaseResponse addOneApi(AddApiRequest request);
}
