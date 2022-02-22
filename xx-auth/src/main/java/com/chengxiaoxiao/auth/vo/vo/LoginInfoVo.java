package com.chengxiaoxiao.auth.vo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户登录的信息
 *
 * @Description: 用户登录之后返回的信息
 * @Author: Cheng XiaoXiao
 * @Date: 2022-02-22 21:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户登录的信息")
public class LoginInfoVo implements Serializable {
    @ApiModelProperty("token")
    private String token;
    @ApiModelProperty("过期时间,单位(分钟)")
    private String expireTime;
}
