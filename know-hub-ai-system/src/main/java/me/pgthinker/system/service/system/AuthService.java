package me.pgthinker.system.service.system;

import me.pgthinker.system.controller.vo.AuthVO;
import me.pgthinker.system.controller.vo.UserLoginVO;

/**
 * @Project: me.pgthinker.system.service
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/3/30 19:03
 * @Description:
 */
public interface AuthService {

	AuthVO login(UserLoginVO userLoginVO);

	AuthVO userInfo();

}
