package com.chengxiaoxiao.xx.system.api;

import com.chengxiaoxiao.core.vo.PageResult;
import com.chengxiaoxiao.core.vo.Result;
import com.chengxiaoxiao.xx.system.entity.DepartmentInfo;
import com.chengxiaoxiao.xx.system.vo.query.DepartmentInfoQuery;
import com.chengxiaoxiao.xx.system.vo.vo.DepartmentTreeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;


/**
 * 部门 接口Swagger Api
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2022-02-27 15:20:14
 */
@Api(tags = "部门模块", description = "实现对部门数据的基本操作")
public interface DepartmentInfoControllerApi {


    /**
     * 按照条件查询部门信息
     *
     * @param departmentInfoQuery 查询条件
     * @return 结果信息
     */
    @ApiOperation(value = "按照条件查询部门信息")
    Result<List<DepartmentInfo>> list(DepartmentInfoQuery departmentInfoQuery);

    /**
     * 分页及条件查询部门信息
     *
     * @param page                页码
     * @param size                分页大小
     * @param departmentInfoQuery 查询条件
     * @return 分页结果信息
     */
    @ApiOperation(value = "分页及条件查询部门信息")
    Result<PageResult<DepartmentInfo>> search(@ApiParam(name = "page", value = "当前页", required = true, type = "path") Long page,
                                              @ApiParam(name = "size", value = "页码大小", required = true, type = "path") Long size,
                                              DepartmentInfoQuery departmentInfoQuery);

    /**
     * 根据ID获取部门详情
     *
     * @param id 部门ID
     * @return 部门详细信息
     */
    @ApiOperation(value = "获取单个部门详情")
    @ApiParam(name = "id", value = "部门ID", required = true, type = "path")
    Result<DepartmentInfo> detail(String id);

    /**
     * 增加部门信息
     *
     * @param departmentInfo 部门增加实体
     * @return 增加后的部门信息
     */
    @ApiOperation(value = "增加部门信息")
    Result<DepartmentInfo> add(DepartmentInfo departmentInfo);

    /**
     * 修改部门信息
     *
     * @param id             部门ID
     * @param departmentInfo 部门修改实体
     * @return 修改后的部门信息
     */
    @ApiOperation(value = "修改部门信息")
    Result<DepartmentInfo> updateById(@ApiParam(name = "id", value = "部门ID", type = "path") String id, DepartmentInfo departmentInfo);

    /**
     * 删除部门信息
     *
     * @param id 部门ID
     * @return 删除结果信息
     */
    @ApiOperation(value = "删除单个部门信息")
    Result<Boolean> deleteById(@ApiParam(name = "id", value = "部门ID", type = "path") String id);

    /**
     * 删除部门信息
     *
     * @param idList 部门ID数组
     * @return 删除结果信息
     */
    @ApiOperation(value = "批量删除部门信息")
    Result<Boolean> batchRemove(List<String> idList);


    /**
     * 查询部门树形结构
     *
     * @return 部门树形结构
     */
    @ApiOperation(value = "查询部门树形结构")
    Result<List<DepartmentTreeVo>> tree();

}
