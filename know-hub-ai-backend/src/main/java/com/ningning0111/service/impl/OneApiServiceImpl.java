package com.ningning0111.service.impl;

import com.ningning0111.common.ApplicationConstant;
import com.ningning0111.common.BaseResponse;
import com.ningning0111.common.ErrorCode;
import com.ningning0111.common.ResultUtils;
import com.ningning0111.model.dto.AddApiDTO;
import com.ningning0111.model.dto.OneApiDTO;
import com.ningning0111.model.entity.OneApi;
import com.ningning0111.exception.BusinessException;
import com.ningning0111.repository.OneApiRepository;
import com.ningning0111.service.OneApiService;
import groovy.util.logging.Slf4j;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import cn.hutool.core.util.StrUtil;
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
    @Value("${spring.ai.openai.base-url}")
    private String defaultBaseUrl = ApplicationConstant.DEFAULT_BASE_URL;
    @Value("${spring.ai.openai.api-key}")
    private String defaultApiKey;
    // 基于内存存储所有可用的key，减少每次对话对数据库的操作
    private static List<OneApi> apiList = new Vector<>();
    private final OneApiRepository oneApiRepository;

    /**
     * 从数据库中加载api信息到内存中
     */
    @PostConstruct
    @Override
    public void reloadOneApiInfo() {
        List<OneApi> findList = oneApiRepository.findAllByDisableIsFalse();
        // 如果数据库内没有数据，则加载配置的api
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

    // 随机获取一个可用的api
    @Override
    public OneApi randomGetOne() {
        int size = apiList.size();
        int randIndex = new Random().nextInt(size);
        return apiList.get(randIndex);
    }


    /**
     * 添加api
     * @param request
     * @return
     */
    @Transactional(rollbackOn = Exception.class)
    @Override
    public BaseResponse addOneApi(AddApiDTO request) {
        try {
            if (StrUtil.isBlank(request.apiKey())){
                return ResultUtils.error(ErrorCode.APIKEY_ERROR);
            }
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

    @Override
    public BaseResponse changeApi(Long id) {
        OneApi oneApi= oneApiRepository.getReferenceById(id);
        if (oneApi.getId() == null){
            return ResultUtils.error(ErrorCode.NOT_FOUND_ERROR);
        }
        oneApi.setDisable(!oneApi.getDisable());
        oneApi.setUpdateTime(new Date(System.currentTimeMillis()));
        try{
            oneApiRepository.save(oneApi);
            return ResultUtils.success("更新成功");
        }catch (Exception e){
            throw new BusinessException(ErrorCode.UPDATE_ERROR,e.getMessage());
        }
    }

    @Override
    public BaseResponse selectById(Long id) {
        OneApi oneApi= oneApiRepository.getReferenceById(id);
        return ResultUtils.success(oneApi);
    }

    @Override
    public BaseResponse deleteById(Long id) {
        OneApi oneApi= oneApiRepository.getReferenceById(id);
        if (oneApi.getDisable()){
            return ResultUtils.error(ErrorCode.DELETE_ERROR);
        }
        apiList.remove(oneApi);
        oneApiRepository.deleteById(id);
        return ResultUtils.success("删除成功");
    }

    @Override
    public BaseResponse deleteByIds(List<Long> ids) {
        try{
            List<OneApi> oneApis = oneApiRepository.findAllByIdIn(ids);
            for(OneApi oneApi : oneApis){
                apiList.remove(oneApi);
            }
            oneApiRepository.deleteByIdIn(ids);
            return ResultUtils.success("删除成功");
        } catch (Exception e){
            return ResultUtils.error(ErrorCode.DELETE_ERROR);
        }
    }

    @Override
    public BaseResponse change(OneApiDTO oneApiDTO) {
        OneApi oneApi = oneApiRepository.getReferenceById(oneApiDTO.id());
        oneApi.setApiKey(oneApi.getApiKey());
        oneApi.setBaseUrl(oneApiDTO.baseUrl());
        oneApi.setDisable(oneApiDTO.disable());
        oneApi.setUpdateTime(new Date(System.currentTimeMillis()));
        oneApi.setDescribe(oneApiDTO.describe());
        oneApiRepository.save(oneApi);
        return ResultUtils.success(oneApiDTO);
    }

}
