package com.chengxiaoxiao.xx.system.vo.vo;

import com.chengxiaoxiao.xx.system.entity.DepartmentInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 部门树形结构表
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2022-02-27 15:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("部门树形结构")
public class DepartmentTreeVo extends DepartmentInfo {
    @ApiModelProperty("部门树形结构")
    private List<DepartmentTreeVo> children;
}
