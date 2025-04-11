package me.pgthinker.core.common;

/**
 * @Project: me.pgthinker.common
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2024/11/25 01:23
 * @Description:
 */
public interface CoreCode {

	/**
	 * 成功
	 */
	ErrorCode SUCCESS = new ErrorCode(0, "success");

	ErrorCode PARAMS_ERROR = new ErrorCode(400, "请求参数错误");

	ErrorCode NO_AUTH_ERROR = new ErrorCode(403, "无权限");

	ErrorCode FORBIDDEN_ERROR = new ErrorCode(402, "禁止访问");

	ErrorCode NOT_FOUND_ERROR = new ErrorCode(404, "请求数据不存在");

	ErrorCode REFRESH_TOKEN = new ErrorCode(405, "令牌异常需刷新");

	ErrorCode RATE_LIMIT_ERROR = new ErrorCode(406, "请求过于频繁");

	ErrorCode SYSTEM_ERROR = new ErrorCode(544, "系统内部异常");

	ErrorCode OPERATION_ERROR = new ErrorCode(545, "操作失败");

	ErrorCode UPDATE_ERROR = new ErrorCode(546, "更新失败");

	ErrorCode DELETE_ERROR = new ErrorCode(547, "删除失败");

	ErrorCode DATABASE_ERROR = new ErrorCode(548, "数据库操作异常");

	ErrorCode THIRD_PARTY_ERROR = new ErrorCode(555, "第三方服务异常");

	// 业务错误（50xx-59xx）
	/// 用户业务
	ErrorCode USER_NOT_FOUND = new ErrorCode(1000_000_100, "用户不存在");

	ErrorCode USER_ACCOUNT_ERROR = new ErrorCode(1000_000_101, "用户名或密码错误");

	ErrorCode USER_EXISTS = new ErrorCode(1000_000_102, "用户已存在");

	ErrorCode USER_LOCKED = new ErrorCode(1000_000_103, "账户已锁定");

	ErrorCode CAPTCHA_ERROR = new ErrorCode(1000_000_104, "验证码错误");

	ErrorCode NOT_LOGIN_ERROR = new ErrorCode(1000_000_105, "未登录");

	ErrorCode TOKEN_EXPIRED = new ErrorCode(1000_000_106, "令牌已过期");

	ErrorCode TOKEN_INVALID = new ErrorCode(1000_000_107, "无效令牌");

	/// 文件业务
	ErrorCode FILE_UPLOAD_ERROR = new ErrorCode(1000_000_204, "文件上传失败");

	ErrorCode FILE_NOT_FOUND = new ErrorCode(1000_000_205, "文件不存在");

}
