package com.chengxiaoxiao.core.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录用户信息
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2020/6/22 10:52
 */
@Data
public class LoginUser implements Serializable {
    private String id;
    private String userName;
    private String nickName;
}
