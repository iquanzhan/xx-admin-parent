package com.chengxiaoxiao.xx.system.vo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * 部门信息 查询对象
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2022-02-27 15:20:14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "部门条件查询对象", description = "用于条件查询使用")
public class DepartmentInfoQuery implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "部门名称")
    private String departmentName;
    @ApiModelProperty(value = "备注")
    private String remark;


}
