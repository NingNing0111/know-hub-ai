package com.ningning0111.exception;

import com.ningning0111.common.BaseResponse;
import com.ningning0111.common.ErrorCode;
import com.ningning0111.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.retry.TransientAiException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.io.IOException;

/**
 * @Project: com.ningning0111.exception
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/3 12:19
 * @Description: 全局异常处理类
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> businessExceptionHandler(BusinessException e) {
        return ResultUtils.error(e.getCode(), e.getMessage());
    }


    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> runtimeExceptionHandler(RuntimeException e) {
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR, e.getMessage());
    }


}