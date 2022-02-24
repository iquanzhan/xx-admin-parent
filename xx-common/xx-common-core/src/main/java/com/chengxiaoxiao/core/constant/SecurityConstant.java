package com.chengxiaoxiao.core.constant;

/**
 * 权限相关的通用常量
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2022/2/21 23:02
 */
public class SecurityConstant {

    /**
     * 权限验证需要忽略的路径信息
     */
    public static String[] excludeUrls = {"/login", "/logout", "/refresh", "/swagger-ui/**", "/swagger-ui/","/v2/api-docs","/swagger-resources","/doc.html","/webjars/**"};


    /**
     * 用户KEY字段
     */
    public static final String USER_KEY = "user_key";

    /**
     * 用户ID字段
     */
    public static final String USER_ID = "user_id";

    /**
     * 用户名字段
     */
    public static final String USER_NAME = "user_name";

    /**
     * 授权信息字段
     */
    public static final String AUTHORIZATION = "authorization";

    /**
     * 请求来源
     */
    public static final String FROM_SOURCE = "from-source";

    /**
     * 内部请求
     */
    public static final String INNER = "inner";

    /**
     * 登录用户
     */
    public static final String LOGIN_USER = "login_user";
}
