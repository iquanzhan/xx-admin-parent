package com.chengxiaoxiao.auth.service;

import com.chengxiaoxiao.auth.vo.form.LoginInfoForm;
import com.chengxiaoxiao.auth.vo.vo.LoginInfoVo;
import com.chengxiaoxiao.core.pojo.LoginUser;
import org.springframework.stereotype.Service;

/**
 * 用户业务逻辑
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2022-02-22 21:56
 */
@Service
public interface UserService {

    /**
     * 进行用户登录
     *
     * @param loginInfoForm 用户登录信息
     * @return 登录后信息
     */
    LoginInfoVo login(LoginInfoForm loginInfoForm);

    /**
     * 进行用户注销操作
     */
    void logout();

    /**
     * 获取登录用户信息
     *
     * @return 登录用户信息
     */
    LoginUser getUserInfo();
}
