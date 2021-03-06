package com.chengxiaoxiao.auth.service.impl;

import cn.hutool.core.util.IdUtil;
import com.chengxiaoxiao.auth.service.UserService;
import com.chengxiaoxiao.auth.vo.form.LoginInfoForm;
import com.chengxiaoxiao.auth.vo.vo.LoginInfoVo;
import com.chengxiaoxiao.core.constant.RedisKeyConstant;
import com.chengxiaoxiao.core.constant.SecurityConstant;
import com.chengxiaoxiao.core.constant.TokenConstant;
import com.chengxiaoxiao.core.exception.GlobalException;
import com.chengxiaoxiao.core.pojo.LoginUser;
import com.chengxiaoxiao.core.util.IpUtil;
import com.chengxiaoxiao.core.util.JwtUtil;
import com.chengxiaoxiao.core.util.RequestUtil;
import com.chengxiaoxiao.core.vo.CodeMsg;
import com.chengxiaoxiao.core.vo.Result;
import com.chengxiaoxiao.security.auth.AuthUtil;
import com.chengxiaoxiao.security.service.TokenService;
import com.chengxiaoxiao.security.utils.SecurityUtil;
import com.chengxiaoxiao.xxadmin.system.api.RemoteUserInfoService;
import com.chengxiaoxiao.xxadmin.system.entity.UserInfo;
import com.chengxiaoxiao.xxadmin.system.enums.UserStatus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户信息业务逻辑
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2022-02-27 19:22
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    RemoteUserInfoService remoteUserInfoService;
    @Resource
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Resource
    TokenService tokenService;

    /**
     * 进行用户登录
     *
     * @param loginInfoForm 用户登录信息
     * @return 登录后信息
     */
    @Override
    public LoginInfoVo login(LoginInfoForm loginInfoForm) {
        Result<UserInfo> userInfoResult = remoteUserInfoService.getUserInfoByUserName(loginInfoForm.getUserName(), SecurityConstant.INNER);
        UserInfo userInfo = userInfoResult.getData();
        if (userInfo == null) {
            throw new GlobalException(CodeMsg.USER_PASSWORD_INCORRENT);
        }

        //判断用户状态
        if (UserStatus.DISABLE.getCode().equals(userInfo.getStatus())) {
            throw new GlobalException(CodeMsg.USER_LOCKED);
        }

        //校验用户密码是否正确
        if (!bCryptPasswordEncoder.matches(loginInfoForm.getPassword(), userInfo.getPassword())) {
            throw new GlobalException(CodeMsg.USER_PASSWORD_INCORRENT);
        }


        String token = IdUtil.fastUUID();

        LoginUser loginUser = new LoginUser();
        loginUser.setId(userInfo.getId());
        loginUser.setUserName(userInfo.getUserName());
        loginUser.setNickName(userInfo.getNickName());
        loginUser.setToken(token);
        loginUser.setIp(IpUtil.getIpAddr(RequestUtil.getRequest()));

        tokenService.refreshToken(loginUser);

        // Jwt存储信息
        Map<String, Object> claimsMap = new HashMap<String, Object>();
        claimsMap.put(SecurityConstant.USER_KEY, token);
        claimsMap.put(SecurityConstant.USER_ID, loginUser.getId());
        claimsMap.put(SecurityConstant.USER_NAME, loginUser.getUserName());

        LoginInfoVo loginInfoVo = new LoginInfoVo();
        loginInfoVo.setToken(TokenConstant.PREFIX + JwtUtil.createToken(claimsMap));
        loginInfoVo.setExpireTime(RedisKeyConstant.EXPIRATION);

        return loginInfoVo;
    }

    /**
     * 进行用户注销操作
     */
    @Override
    public void logout() {
        String token = SecurityUtil.getToken();
        if (StringUtils.isNotEmpty(token)) {
            //获取用户key，在redis中进行删除
            String userKey = SecurityUtil.getUserKey();
            AuthUtil.logoutByUserKey(userKey);
        }

    }

    /**
     * 获取登录用户信息
     *
     * @return 登录用户信息
     */
    @Override
    public LoginUser getUserInfo() {
        return SecurityUtil.getLoginUser();
    }
}

