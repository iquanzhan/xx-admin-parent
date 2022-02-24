package com.chengxiaoxiao.security.auth;

import com.chengxiaoxiao.core.pojo.LoginUser;
import com.chengxiaoxiao.security.annotation.RequiresPermissions;
import com.chengxiaoxiao.security.annotation.RequiresRoles;

/**
 * 权限工具类
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2022/2/22  11:18
 */
public class AuthUtil {

    public static AuthLogic authLogic = new AuthLogic();

    /**
     * 校验是否登录,如果未登录抛出异常
     */
    public static void checkLogin() {
        authLogic.checkLogin();
    }

    /**
     * 校验用户是否包含角色
     *
     * @param requiresRoles 接口中的角色
     */
    public static void checkRole(RequiresRoles requiresRoles) {
        authLogic.checkRole(requiresRoles);
    }

    /**
     * 校验用户是否包含权限
     *
     * @param requiresPermissions 接口中的权限
     */
    public static void checkPermission(RequiresPermissions requiresPermissions) {
        authLogic.checkPermission(requiresPermissions);
    }

    /**
     * 根据token获取登录用户信息
     *
     * @param token token
     * @return 登录用户信息
     */
    public static LoginUser getLoginUser(String token) {
        return authLogic.getLoginUser(token);
    }

    /**
     * 判断用户是否过期，会自动刷新token有效期
     *
     * @param loginUser 登录用户信息
     */
    public static void verifyLoginUserExpire(LoginUser loginUser) {
        authLogic.verifyLoginUserExpire(loginUser);
    }

    /**
     * 根据用户KEY删除注销用户
     *
     * @param userKey 用户KEY
     */
    public static void logoutByUserKey(String userKey) {
        authLogic.logoutByUserKey(userKey);
    }
}
