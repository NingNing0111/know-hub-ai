package me.pgthinker.core.exception;

import lombok.extern.slf4j.Slf4j;
import me.pgthinker.core.common.BaseResponse;
import me.pgthinker.core.common.CoreCode;
import me.pgthinker.core.common.ResultUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Project: me.pgthinker.core.exception
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/3/30 17:08
 * @Description:
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
		e.printStackTrace();
		return ResultUtils.error(CoreCode.SYSTEM_ERROR, e.getMessage());
	}

}
