package me.pgthinker.core.common;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Project: me.pgthinker.common
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2024/11/25 01:22
 * @Description:
 */
@Data
@NoArgsConstructor
public class BaseResponse<T> implements Serializable {

	private int code;

	private T data;

	private String message;

	public BaseResponse(int code, T data, String message) {
		this.code = code;
		this.data = data;
		this.message = message;
	}

	public BaseResponse(int code, T data) {
		this(code, data, "");
	}

	public BaseResponse(ErrorCode errorCode) {
		this(errorCode.getCode(), null, errorCode.getMsg());
	}

}
