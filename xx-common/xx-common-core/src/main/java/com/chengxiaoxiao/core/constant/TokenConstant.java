package com.chengxiaoxiao.core.constant;

/**
 * TOKEN相关常量信息
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2022/2/22  11:38
 */
public class TokenConstant {
    /**
     * token前缀
     */
    public static final String PREFIX = "xxadmin-";
    /**
     * token 中的Header标识
     */
    public static final String AUTHENTICATION = "Authentication";

    /**
     * 令牌秘钥
     */
    public final static String SECRET = "abcdefghijklmnopqrstuvwxyz";

    /**
     * token过期时间
     */
    public final static long EXPIRE_TIME = 86400;

    /**
     * 签发者
     */
    public final static String ISSUER = "xx-admin";

    /**
     * 主题
     */
    public final static String SUBJECT = "security";



}
