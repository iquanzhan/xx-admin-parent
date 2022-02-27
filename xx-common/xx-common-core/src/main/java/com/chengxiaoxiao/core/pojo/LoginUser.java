package com.chengxiaoxiao.core.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * 登录用户信息
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2020/6/22 10:52
 */
@Data
public class LoginUser implements Serializable {
    /**
     * 用户ID
     */
    private String id;
    
    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户昵称
     */
    private String token;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * ip
     */
    private String ip;

    /**
     * 用户所拥有的角色信息
     */
    private Set<String> roles;

    /**
     * 用户所拥有的的权限信息
     */
    private Set<String> permissions;
}
