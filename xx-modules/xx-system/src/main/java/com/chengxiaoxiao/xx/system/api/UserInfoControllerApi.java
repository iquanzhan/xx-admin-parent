package com.chengxiaoxiao.xx.system.api;

import com.chengxiaoxiao.core.vo.PageResult;
import com.chengxiaoxiao.core.vo.Result;
import com.chengxiaoxiao.xx.system.entity.UserInfo;
import com.chengxiaoxiao.xx.system.vo.query.UserInfoQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;


/**
 * 用户信息 接口Swagger Api
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2022-02-22 22:42:36
 */
@Api(tags = "用户信息模块", description = "实现对用户信息数据的基本操作")
public interface UserInfoControllerApi {


    /**
     * 按照条件查询用户信息
     *
     * @param userInfoQuery 查询条件
     * @return 结果信息
     */
    @ApiOperation(value = "按照条件查询用户信息")
    Result<List<UserInfo>> list(UserInfoQuery userInfoQuery);

    /**
     * 分页及条件查询用户信息
     *
     * @param page          页码
     * @param size          分页大小
     * @param userInfoQuery 查询条件
     * @return 分页结果信息
     */
    @ApiOperation(value = "分页及条件查询用户信息")
    Result<PageResult<UserInfo>> search(@ApiParam(name = "page", value = "当前页", required = true, type = "path") Long page,
                                        @ApiParam(name = "size", value = "页码大小", required = true, type = "path") Long size,
                                        UserInfoQuery userInfoQuery);


    /**
     * 根据用户名获取用户信息
     *
     * @param userName 用户名
     * @return 用户信息
     */
    @ApiOperation(value = "根据用户名获取用户信息")
    Result<UserInfo> list(@ApiParam(name = "userName", value = "用户名", type = "path") String userName);

    /**
     * 根据ID获取用户信息详情
     *
     * @param id 用户信息ID
     * @return 用户信息详细信息
     */
    @ApiOperation(value = "获取单个用户信息详情")
    @ApiParam(name = "id", value = "用户信息ID", required = true, type = "path")
    Result<UserInfo> detail(String id);

    /**
     * 增加用户信息
     *
     * @param userInfo 用户信息增加实体
     * @return 增加后的用户信息
     */
    @ApiOperation(value = "增加用户信息")
    Result<UserInfo> add(UserInfo userInfo);

    /**
     * 修改用户信息
     *
     * @param id       用户信息ID
     * @param userInfo 用户信息修改实体
     * @return 修改后的用户信息
     */
    @ApiOperation(value = "修改用户信息")
    Result<UserInfo> updateById(@ApiParam(name = "id", value = "用户信息ID", type = "path") String id, UserInfo userInfo);

    /**
     * 删除用户信息
     *
     * @param id 用户信息ID
     * @return 删除结果信息
     */
    @ApiOperation(value = "删除单个用户信息")
    Result<Boolean> deleteById(@ApiParam(name = "id", value = "用户信息ID", type = "path") String id);

    /**
     * 删除用户信息
     *
     * @param idList 用户信息ID数组
     * @return 删除结果信息
     */
    @ApiOperation(value = "批量删除用户信息")
    Result<Boolean> batchRemove(List<String> idList);

}
