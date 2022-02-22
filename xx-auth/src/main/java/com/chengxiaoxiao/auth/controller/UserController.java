package com.chengxiaoxiao.auth.controller;

import com.chengxiaoxiao.auth.api.UserControllerApi;
import com.chengxiaoxiao.auth.service.UserService;
import com.chengxiaoxiao.auth.vo.form.LoginInfoForm;
import com.chengxiaoxiao.auth.vo.vo.LoginInfoVo;
import com.chengxiaoxiao.core.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户模块接口
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2022-02-22 21:33
 */
@RestController
@RequestMapping("/user")
public class UserController implements UserControllerApi {

    @Resource
    UserService userService;

    /**
     * 用户登录
     *
     * @param loginInfoForm 用户登录表单
     * @return 登录信息
     */
    @Override
    @PostMapping("/login")
    public Result<LoginInfoVo> login(@RequestBody LoginInfoForm loginInfoForm) {
        return Result.success(userService.login(loginInfoForm));
    }

    /**
     * 用户注销
     *
     * @return 状态信息
     */
    @Override
    public Result<Boolean> logout() {
        return null;
    }

    /**
     * 主动刷新token有效期
     *
     * @return 刷新状态
     */
    @Override
    public Result<Boolean> refresh() {
        return null;
    }
}
