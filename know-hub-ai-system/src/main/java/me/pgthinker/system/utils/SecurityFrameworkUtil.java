package me.pgthinker.system.utils;

import lombok.extern.slf4j.Slf4j;
import me.pgthinker.core.common.CoreCode;
import me.pgthinker.core.exception.BusinessException;
import me.pgthinker.system.model.entity.user.SystemUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * @Project: me.pgthinker.utils
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/3/13 22:54
 * @Description:
 */
@Slf4j
public class SecurityFrameworkUtil {

	public static final String AUTHORIZATION_BEARER = "Bearer";

	public static Authentication getAuthentication() {
		SecurityContext context = SecurityContextHolder.getContext();
		Optional.ofNullable(context).orElseThrow(() -> new BusinessException(CoreCode.OPERATION_ERROR));
		return context.getAuthentication();
	}

	public static SystemUser getLoginUser() {
		Authentication authentication = getAuthentication();
		if (authentication.getPrincipal() instanceof SystemUser) {
			return (SystemUser) authentication.getPrincipal();
		}
		else {
			throw new BusinessException(CoreCode.OPERATION_ERROR);
		}
	}

	public static Long getCurrUserId() {
		SystemUser loginUser = getLoginUser();
		return loginUser != null ? loginUser.getId() : null;
	}

	public static Optional<SystemUser> tryGetLoginUser() {
		try {
			SystemUser loginUser = getLoginUser();
			return Optional.ofNullable(loginUser);
		}
		catch (Exception e) {
			return Optional.empty();
		}

	}

}
