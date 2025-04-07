package me.pgthinker.core.exception;

import me.pgthinker.core.common.ErrorCode;

/**
 * @Project: me.pgthinker.exception
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2024/11/25 01:20
 * @Description:
 */
public class BusinessException extends RuntimeException {

	/**
	 * 错误码
	 */
	private final int code;

	public BusinessException(int code, String message) {
		super(message);
		this.code = code;
	}

	public BusinessException(ErrorCode errorCode) {
		super(errorCode.getMsg());
		this.code = errorCode.getCode();
	}

	public BusinessException(ErrorCode errorCode, String message) {
		super(message);
		this.code = errorCode.getCode();
	}

	public int getCode() {
		return code;
	}

}
