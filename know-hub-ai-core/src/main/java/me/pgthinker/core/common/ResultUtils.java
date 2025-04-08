package me.pgthinker.core.common;

/**
 * @Project: me.pgthinker.common
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2024/11/25 01:22
 * @Description:
 */
public class ResultUtils {

	/**
	 * 成功
	 * @param data
	 * @param <T>
	 * @return
	 */
	public static <T> BaseResponse<T> success(T data) {
		return new BaseResponse<>(CoreCode.SUCCESS.getCode(), data, CoreCode.SUCCESS.getMsg());
	}

	/**
	 * 失败
	 * @param errorCode
	 * @return
	 */
	public static BaseResponse error(ErrorCode errorCode) {
		return new BaseResponse<>(errorCode);
	}

	/**
	 * 失败
	 * @param code
	 * @param message
	 * @return
	 */
	public static BaseResponse error(int code, String message) {
		return new BaseResponse(code, null, message);
	}

	/**
	 * 失败
	 * @param errorCode
	 * @return
	 */
	public static BaseResponse error(ErrorCode errorCode, String message) {
		return new BaseResponse(errorCode.getCode(), null, message);
	}

}
