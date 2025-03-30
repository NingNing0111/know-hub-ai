package me.pgthinker.system.controller;

import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import me.pgthinker.core.common.BaseResponse;
import me.pgthinker.core.common.ResultUtils;
import me.pgthinker.system.controller.vo.AuthVO;
import me.pgthinker.system.controller.vo.UserLoginVO;
import me.pgthinker.system.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project: me.pgthinker.system.controller
 * @Author: NingNing0111
 * @Github: https://github.com/ningning0111
 * @Date: 2025/3/30 18:46
 * @Description:
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    @PermitAll
    public BaseResponse<AuthVO> login(@RequestBody UserLoginVO userLoginVO) {
        return ResultUtils.success(authService.login(userLoginVO));

    }
}
