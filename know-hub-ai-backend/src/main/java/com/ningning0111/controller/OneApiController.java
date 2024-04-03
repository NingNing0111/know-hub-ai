package com.ningning0111.controller;

import com.ningning0111.common.ApplicationConstant;
import com.ningning0111.common.BaseResponse;
import com.ningning0111.common.ErrorCode;
import com.ningning0111.common.ResultUtils;
import com.ningning0111.model.dto.AddApiRequest;
import com.ningning0111.model.dto.QueryApiRequest;
import com.ningning0111.service.OneApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Project: com.ningning0111.controller
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/4/2 17:42
 * @Description:
 */
@RestController
@RequestMapping(ApplicationConstant.API_VERSION + "/one-api")
@RequiredArgsConstructor
public class OneApiController {
    private final OneApiService oneApiService;

    @PostMapping("/add")
    BaseResponse addOneApi(@RequestBody AddApiRequest request){
        String apiKey = request.apiKey();
        if(apiKey == null){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR,"key is empty");
        }
        return oneApiService.addOneApi(request);
    }

    /**
     * 查询所有未禁止的key
     * @param queryApiRequest
     * @return
     */
    @GetMapping("/select")
    BaseResponse selectApi(@RequestBody QueryApiRequest queryApiRequest){
        return oneApiService.selectApi(PageRequest.of(queryApiRequest.page()-1, queryApiRequest.pageSize()));
    }
}
