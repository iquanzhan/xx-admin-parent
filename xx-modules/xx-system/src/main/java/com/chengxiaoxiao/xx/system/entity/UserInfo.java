package com.chengxiaoxiao.xx.system.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息表 实体对象
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2022-02-22 22:42:36
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "UserInfo对象", description = "用户信息表")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "用户账号")
    private String userName;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "用户邮箱")
    private String email;

    @ApiModelProperty(value = "手机号码")
    private String phoneNumber;

    @ApiModelProperty(value = "用户性别（0男 1女 2未知）")
    private Integer sex;

    @ApiModelProperty(value = "头像地址")
    private String avatar;

    @ApiModelProperty(value = "帐号状态（0正常 1停用）", hidden = true)
    private Integer status;

    @ApiModelProperty(value = "删除标志（0代表存在 1代表删除）", hidden = true)
    @TableField(fill = FieldFill.INSERT)
    private String deleteStatus;

    @ApiModelProperty(value = "最后登录IP", hidden = true)
    private String loginIp;

    @ApiModelProperty(value = "最后登录时间", hidden = true)
    private Date loginDate;

    @ApiModelProperty(value = "创建者", hidden = true)
    @TableField(fill = FieldFill.INSERT)
    private String creator;

    @ApiModelProperty(value = "创建时间", hidden = true)
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新者", hidden = true)
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String editor;

    @ApiModelProperty(value = "更新时间", hidden = true)
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "备注")
    private String remark;


}
