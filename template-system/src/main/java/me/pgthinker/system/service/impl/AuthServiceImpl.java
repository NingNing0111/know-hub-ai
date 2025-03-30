package me.pgthinker.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.pgthinker.core.common.CoreCode;
import me.pgthinker.core.exception.BusinessException;
import me.pgthinker.system.config.web.SecurityProperties;
import me.pgthinker.system.controller.vo.AuthVO;
import me.pgthinker.system.controller.vo.UserLoginVO;
import me.pgthinker.system.mapper.SystemRoleMapper;
import me.pgthinker.system.mapper.SystemUserMapper;
import me.pgthinker.system.mapper.SystemUserRoleMapper;
import me.pgthinker.system.model.entity.SystemRole;
import me.pgthinker.system.model.entity.SystemUser;
import me.pgthinker.system.model.entity.SystemUserRole;
import me.pgthinker.system.security.service.JwtService;
import me.pgthinker.system.service.AuthService;
import me.pgthinker.system.service.SystemRoleService;
import me.pgthinker.system.utils.SecurityFrameworkUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Project: me.pgthinker.system.service.impl
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/3/30 19:04
 * @Description:
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final PasswordEncoder passwordEncoder;

	private final SystemUserMapper userMapper;

	private final SystemRoleMapper roleMapper;

	private final SystemUserRoleMapper userRoleMapper;

	private final JwtService jwtService;

	private final SecurityProperties securityProperties;

	@PostConstruct
	public void init() {
		if (securityProperties.getAdminInit()) {
			// 判断有没有role角色
			LambdaQueryWrapper<SystemRole> qw = new LambdaQueryWrapper<>();
			qw.eq(SystemRole::getName, "root");
			List<SystemRole> systemRoles = roleMapper.selectList(qw);
			SystemRole systemRole = new SystemRole();

			if (systemRoles.isEmpty()) {
				systemRole.setName("root");
				systemRole.setDescription("超级管理员");
				roleMapper.insert(systemRole);
			}
			else {
				systemRole = systemRoles.get(0);
			}
			// 判断有没有超级管理员用户
			SystemUser systemUser = userMapper.getUserWithRolesAndPermissions("root");
			if (systemUser == null) {
				systemUser = new SystemUser();
				systemUser.setUsername("root");
				systemUser.setPassword(passwordEncoder.encode(securityProperties.getPassword()));
				userMapper.insert(systemUser);
			}
			// 插入关联信息
			SystemUserRole systemUserRole = new SystemUserRole();
			systemUserRole.setRoleId(systemRole.getId());
			systemUserRole.setUserId(systemUser.getId());
			userRoleMapper.insert(systemUserRole);
			log.info("Init User Password: [{}]", securityProperties.getPassword());
		}
	}

	@Override
	public AuthVO login(UserLoginVO userLoginVO) {
		String username = userLoginVO.getUsername();
		String password = userLoginVO.getPassword();
		SystemUser user = userMapper.getUserWithRolesAndPermissions(username);
		if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
			throw new BusinessException(CoreCode.USER_ACCOUNT_ERROR);
		}
		AuthVO authVO = new AuthVO();
		List<SystemRole> roles = user.getRoles();
		if (roles == null || roles.isEmpty()) {
			authVO.setRoles(new ArrayList<>());
		}
		else {
			authVO.setRoles(roles.stream().map(SystemRole::getName).toList());
		}
		String token = jwtService.generateToken(username);
		authVO.setToken(token);
		authVO.setUsername(username);
		return authVO;
	}

	@Override
	public AuthVO userInfo() {
		SystemUser loginUser = SecurityFrameworkUtil.getLoginUser();
		if (loginUser == null) {
			throw new BusinessException(CoreCode.TOKEN_INVALID);
		}
		SystemUser user = userMapper.getUserWithRolesAndPermissions(loginUser.getUsername());
		AuthVO authVO = new AuthVO();
		authVO.setUsername(user.getUsername());
		authVO.setRoles(user.getRoles().stream().map(SystemRole::getName).toList());
		authVO.setToken(null);
		return null;
	}

}
