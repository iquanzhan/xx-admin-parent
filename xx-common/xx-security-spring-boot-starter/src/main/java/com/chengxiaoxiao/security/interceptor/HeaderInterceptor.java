package com.chengxiaoxiao.security.interceptor;

import com.chengxiaoxiao.core.context.SecurityContextHolder;
import com.chengxiaoxiao.core.pojo.LoginUser;
import com.chengxiaoxiao.security.auth.AuthUtil;
import com.chengxiaoxiao.security.utils.SecurityUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义请求头拦截器，将Header数据封装到线程变量中方便获取
 *
 * @Description: 此拦截器会同时验证当前用户有效期自动刷新有效期
 * @Author: Cheng Xiaoxiao
 * @Date: 2022-02-22 14:39
 */
public class HeaderInterceptor implements AsyncHandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        String token = SecurityUtil.getToken();
        if (StringUtils.isNotEmpty(token)) {
            LoginUser loginUser = AuthUtil.getLoginUser(token);
            if (null != loginUser) {
                AuthUtil.verifyLoginUserExpire(loginUser);
                SecurityContextHolder.setUser(loginUser);
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        SecurityContextHolder.remove();
    }
}
