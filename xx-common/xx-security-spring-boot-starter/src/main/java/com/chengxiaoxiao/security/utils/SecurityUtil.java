package com.chengxiaoxiao.security.utils;

import com.chengxiaoxiao.core.constant.TokenConstant;
import com.chengxiaoxiao.core.context.SecurityContextHolder;
import com.chengxiaoxiao.core.pojo.LoginUser;
import com.chengxiaoxiao.core.util.RequestUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 权限信息获取工具类
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2022/2/22 11:30
 */
public class SecurityUtil {
    /**
     * 获取用户ID
     * @return 用户id
     */
    public static String getUserId() {
        return SecurityContextHolder.getUserId();
    }
    /**
     * 获取用户KEY
     * @return 用户id
     */
    public static String getUserKey() {
        return SecurityContextHolder.getUserKey();
    }

    /**
     * 获取登录用户信息
     * @return 登录用户信息
     */
    public static LoginUser getLoginUser() {
        return SecurityContextHolder.getUser();
    }

    /**
     * 获取请求token
     * @return token
     */
    public static String getToken() {
        return getToken(Objects.requireNonNull(RequestUtil.getRequest()));
    }

    /**
     * 根据request获取请求token
     * @return token
     */
    public static String getToken(HttpServletRequest request) {
        String token = request.getHeader(TokenConstant.AUTHENTICATION);
        return replaceTokenPrefix(token);
    }

    /**
     * 裁剪token前缀
     * @return 截取前缀后的token
     */
    public static String replaceTokenPrefix(String token) {
        // 如果前端设置了令牌前缀，则裁剪掉前缀
        if (StringUtils.isNotEmpty(token) && token.startsWith(TokenConstant.PREFIX)) {
            token = token.replaceFirst(TokenConstant.PREFIX, "");
        }
        return token;
    }

    /**
     * 是否为管理员
     *
     * @param userId 用户ID
     * @return 结果
     */
    public static boolean isAdmin(Long userId) {
        return userId != null && 1L == userId;
    }
}
