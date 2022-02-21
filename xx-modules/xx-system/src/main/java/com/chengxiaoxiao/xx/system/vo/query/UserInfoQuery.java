package com.chengxiaoxiao.xx.system.vo.query;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * 用户 查询对象
 *
 * @Description:
 * @Author: Cheng xiaoxiao
 * @Date: 2022-02-21 19:19:21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "UserInfo对象", description = "用户表")
public class UserInfoQuery implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "密码")
    private String password;


}
