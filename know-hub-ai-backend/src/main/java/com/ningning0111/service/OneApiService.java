package com.ningning0111.service;

import com.ningning0111.common.BaseResponse;
import com.ningning0111.model.dto.AddApiDTO;
import com.ningning0111.model.dto.OneApiDTO;
import com.ningning0111.model.entity.OneApi;
import org.springframework.data.domain.PageRequest;

import java.util.List;

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



    BaseResponse addOneApi(AddApiDTO request);

    BaseResponse selectApi(PageRequest pageRequest);

    BaseResponse changeApi(Long id);

    BaseResponse selectById(Long id);

    BaseResponse deleteById(Long id);

    BaseResponse deleteByIds(List<Long> ids);

    BaseResponse change(OneApiDTO oneApiDTO);
}
