package com.chengxiaoxiao.auth.vo.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 用户登录信息表单
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2022-02-22 21:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户登录表单")
public class LoginInfoForm implements Serializable {
    @ApiModelProperty("用户名")
    @NotBlank(message = "请输入用户名")
    private String userName;
    @ApiModelProperty("密码")
    @NotBlank(message = "请输入密码")
    private String password;
}
