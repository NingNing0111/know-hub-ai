package com.ningning0111.service.impl;

import com.ningning0111.common.ApplicationConstant;
import com.ningning0111.common.BaseResponse;
import com.ningning0111.common.ErrorCode;
import com.ningning0111.common.ResultUtils;
import com.ningning0111.model.dto.AddApiRequest;
import com.ningning0111.model.dto.QueryApiRequest;
import com.ningning0111.model.entity.OneApi;
import com.ningning0111.exception.BusinessException;
import com.ningning0111.repository.OneApiRepository;
import com.ningning0111.service.OneApiService;
import groovy.util.logging.Slf4j;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.*;

/**
 * @Project: com.ningning0111.service
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/4/2 17:12
 * @Description:
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OneApiServiceImpl implements OneApiService {
    @Value("${know-hub-ai.baseUrl}")
    private String defaultBaseUrl = ApplicationConstant.DEFAULT_BASE_URL;
    @Value("${know-hub-ai.apiKey}")
    private String defaultApiKey;
    private static List<OneApi> apiList = new Vector<>();
    private final OneApiRepository oneApiRepository;

    @PostConstruct
    @Override
    public void reloadOneApiInfo() {
        List<OneApi> findList = oneApiRepository.findAllByDisableIsFalse();
        if(findList.isEmpty()){
            long currMillis = System.currentTimeMillis();
            OneApi oneApi = oneApiRepository.save(OneApi.builder()
                    .apiKey(defaultApiKey)
                    .disable(false)
                    .baseUrl(defaultBaseUrl)
                    .createTime(new Date(currMillis))
                    .updateTime(new Date(currMillis))
                    .build());
            apiList.add(oneApi);
        }else{
            apiList.clear();
            apiList.addAll(findList);
        }
    }

    @Override
    public OneApi randomGetOne() {
        int size = apiList.size();
        int randIndex = new Random().nextInt(size);
        return apiList.get(randIndex);
    }

    @Override
    public BaseResponse queryList() {
        return null;
    }

    @Override
    public BaseResponse addOneApi(AddApiRequest request) {
        try {
            long currMillis = System.currentTimeMillis();
            OneApi oneApi = OneApi.builder()
                    .apiKey(request.apiKey())
                    .baseUrl(request.baseUrl())
                    .describe(request.describe())
                    .createTime(new Date(currMillis))
                    .updateTime(new Date(currMillis))
                    .disable(false)
                    .build();
            oneApiRepository.save(oneApi);
            reloadOneApiInfo();
            return ResultUtils.success("添加成功");
        }catch (Exception e){
            throw new BusinessException(ErrorCode.OPERATION_ERROR,e.getMessage());
        }
    }

    @Override
    public BaseResponse selectApi(PageRequest pageRequest) {
        List<OneApi> oneApis = oneApiRepository.findAllByDisableIsFalse(pageRequest);
        return ResultUtils.success(oneApis);
    }

}
