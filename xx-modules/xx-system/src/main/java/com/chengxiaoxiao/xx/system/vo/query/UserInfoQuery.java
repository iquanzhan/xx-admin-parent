package com.chengxiaoxiao.xx.system.vo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * 用户信息 查询对象
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2022-02-22 22:42:36
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "用户信息条件查询对象", description = "用于条件查询使用")
public class UserInfoQuery implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "用户账号")
    private String userName;
    @ApiModelProperty(value = "用户昵称")
    private String nickName;
    @ApiModelProperty(value = "用户邮箱")
    private String email;
    @ApiModelProperty(value = "手机号码")
    private String phoneNumber;
    @ApiModelProperty(value = "用户性别（0男 1女 2未知）")
    private Integer sex;
    @ApiModelProperty(value = "帐号状态（0正常 1停用）")
    private Integer status;
    @ApiModelProperty(value = "备注")
    private String remark;


}
