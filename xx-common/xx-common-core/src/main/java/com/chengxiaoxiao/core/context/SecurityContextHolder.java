package com.chengxiaoxiao.core.context;


import cn.hutool.core.util.StrUtil;
import com.alibaba.ttl.TransmittableThreadLocal;
import com.chengxiaoxiao.core.exception.GlobalException;
import com.chengxiaoxiao.core.pojo.LoginUser;
import com.chengxiaoxiao.core.vo.CodeMsg;


/**
 * 登录用户保存容器
 *
 * @Author Cheng xiaoxiao
 * @CreateTime 2020/12/02 16:41
 */
public class SecurityContextHolder {

    private static final TransmittableThreadLocal<LoginUser> USER_THREAD_LOCAL = new TransmittableThreadLocal<>();

    /**
     * 设置当前线程中的用户
     *
     * @param user 用户信息
     */
    public static void setUser(LoginUser user) {
        USER_THREAD_LOCAL.set(user);
    }

    /**
     * 获取线程中的用户
     *
     * @return 当前登录用户
     */
    public static LoginUser getUser() {
        return USER_THREAD_LOCAL.get();
    }

    /**
     * 移除保存的信息
     */
    public static void remove() {
        USER_THREAD_LOCAL.remove();
    }

    /**
     * 获取登录用户的ID
     *
     * @return 登录用户ID
     */
    public static String getUserId() {
        LoginUser user = getUser();

        if (user == null) {
            throw new GlobalException(CodeMsg.USER_NOT_LOGIN_ERROR);
        }
        return StrUtil.nullToDefault(user.getId(), StrUtil.EMPTY);
    }

    /**
     * 获取登录用户的KEY
     *
     * @return 登录用户KEY
     */
    public static String getUserKey() {
        LoginUser user = getUser();

        if (user == null) {
            throw new GlobalException(CodeMsg.USER_NOT_LOGIN_ERROR);
        }
        return StrUtil.nullToDefault(user.getToken(), StrUtil.EMPTY);
    }
}
