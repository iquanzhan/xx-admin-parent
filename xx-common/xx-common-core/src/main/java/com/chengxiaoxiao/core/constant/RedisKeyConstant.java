package com.chengxiaoxiao.core.constant;

/**
 * Redis Key统一管理
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2020/6/18 9:54
 */
public class RedisKeyConstant {
    /**
     * redis 默认的过期时间，单位分钟
     */
    public static final long TIMEOUT = 60L;

    /**
     * 缓存有效期，默认720（分钟）
     */
    public final static long EXPIRATION = 720;

    /**
     * 缓存刷新时间，默认120（分钟）
     */
    public final static long REFRESH_TIME = 120;





    /**
     * 应用总体前缀
     */
    private static final String PREFIX = "xx-admin-";


    /***************************************************用户权限中心*******************************************************/
    /**
     * 验证中心基础路径
     */
    private static final String MODULE_AUTH_BASE_KEY = PREFIX + "auth-center:";
    /**
     * 用户TOKEN KEY
     */
    public static final String USER_TOKEN = MODULE_AUTH_BASE_KEY + "login-tokens:";

    /***************************************************用户权限中心*******************************************************/
}
