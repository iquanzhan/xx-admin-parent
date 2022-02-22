package com.chengxiaoxiao.security.service;

import com.chengxiaoxiao.core.constant.RedisKeyConstant;
import com.chengxiaoxiao.core.pojo.LoginUser;
import com.chengxiaoxiao.core.util.JwtUtil;
import com.chengxiaoxiao.redis.service.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

import static com.chengxiaoxiao.core.constant.RedisKeyConstant.USER_TOKEN;

/**
 * token业务类
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2022/2/22  14:22
 */
@Component
public class TokenService {

    @Resource
    private RedisService redisService;

    /**
     * 根据token获取用户信息
     *
     * @param token token
     * @return 登录用户
     */
    public LoginUser getLoginUser(String token) {
        LoginUser user = null;
        try {
            if (StringUtils.isNotEmpty(token)) {
                String userId = JwtUtil.getUserId(token);
                user = redisService.getCacheObject(getTokenKey(userId));
                return user;
            }
        } catch (Exception e) {
        }
        return user;
    }

    /**
     * 获取token在redis中的键
     *
     * @param token token
     * @return redis key
     */
    private String getTokenKey(String token) {
        return USER_TOKEN + token;
    }

    /**
     * 校验token是否过期
     *
     * @param loginUser 登录用户信息
     */
    public void verifyToken(LoginUser loginUser) {
        long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= RedisKeyConstant.REFRESH_TIME) {
            refreshToken(loginUser);
        }
    }

    /**
     * 刷新token的过期时间
     *
     * @param loginUser 登录用户
     */
    private void refreshToken(LoginUser loginUser) {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + RedisKeyConstant.EXPIRATION * 60 * 1000);
        String userKey = getTokenKey(loginUser.getToken());
        redisService.setCacheObject(userKey, loginUser, RedisKeyConstant.EXPIRATION, TimeUnit.MINUTES);
    }
}
