package com.chengxiaoxiao.auth.api;

import com.chengxiaoxiao.auth.vo.form.LoginInfoForm;
import com.chengxiaoxiao.auth.vo.vo.LoginInfoVo;
import com.chengxiaoxiao.core.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户模块Swagger API
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2022-02-22 20:10
 */
@Api(tags = "用户模块", description = "实现对用户的的基本操作")
public interface UserControllerApi {

    /**
     * 用户登录
     *
     * @param loginInfoForm 用户登录表单
     * @return 登录信息
     */
    @ApiOperation("用户登录")
    Result<LoginInfoVo> login(LoginInfoForm loginInfoForm);

    /**
     * 用户注销
     *
     * @return 状态信息
     */
    @ApiOperation("用户注销")
    Result<Boolean> logout();

    /**
     * 主动刷新token有效期
     * @return 刷新状态
     */
    Result<Boolean> refresh();
}
