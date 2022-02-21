package com.chengxiaoxiao.xx.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chengxiaoxiao.core.validator.group.AddGroup;
import com.chengxiaoxiao.core.validator.group.UpdateGroup;
import com.chengxiaoxiao.core.vo.PageResult;
import com.chengxiaoxiao.core.vo.Result;
import com.chengxiaoxiao.xx.system.api.UserInfoControllerApi;
import com.chengxiaoxiao.xx.system.entity.UserInfo;
import com.chengxiaoxiao.xx.system.service.UserInfoService;
import com.chengxiaoxiao.xx.system.vo.query.UserInfoQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 用户 接口控制器
 *
 * @Description:
 * @Author: Cheng xiaoxiao
 * @Date: 2022-02-21 19:19:21
 */
@RestController
@RequestMapping("/user-info")
public class UserInfoController implements UserInfoControllerApi {
    @Resource
    private UserInfoService userInfoService;


    /**
    * 分页及条件查询用户信息
    * @param page 页码
    * @param size 分页大小
    * @param  userInfoQuery 查询条件
    * @return 分页结果信息
    */
    @Override
    @GetMapping("/{page}/{size}")
    public Result<PageResult<UserInfo>> search(@PathVariable Long page, @PathVariable Long size, UserInfoQuery userInfoQuery) {
        if (page <= 0) {
            page = 1L;
        }
        if (size <= 0) {
            size = 10L;
        }
        Page<UserInfo> pageParam = new Page<>(page, size);
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
         if (!StringUtils.isEmpty(userInfoQuery.getUsername())) {
            wrapper.like("username", userInfoQuery.getUsername());
         }
         if (!StringUtils.isEmpty(userInfoQuery.getPassword())) {
            wrapper.like("password", userInfoQuery.getPassword());
         }
        userInfoService.page(pageParam, wrapper);
        PageResult<UserInfo> pageResult = new PageResult<UserInfo>(pageParam.getTotal(), pageParam.getSize(), pageParam.getCurrent(), pageParam.getRecords());

        return Result.success(pageResult);
    }

    /**
    * 根据ID获取用户详情
    *
    * @param id 用户ID
    * @return 用户详细信息
    */
    @Override
    @GetMapping("/{id}")
    public Result<UserInfo> detail(@PathVariable String id) {
        return Result.success(userInfoService.detailById(id));
    }

    /**
    * 增加用户
    *
    * @param userInfo 用户增加实体
    * @return 增加后的用户信息
    */
    @Override
    @PostMapping
    public Result<UserInfo> add(@RequestBody @Validated(AddGroup.class) UserInfo userInfo) {
        userInfoService.saveUserInfo(userInfo);
        return Result.success(userInfo);
    }

    /**
    * 修改用户
    *
    * @param id             用户ID
    * @param userInfo 用户修改实体
    * @return 修改后的用户信息
    */
    @Override
    @PutMapping("/{id}")
    public Result<UserInfo> updateById(@PathVariable String id,@RequestBody @Validated(UpdateGroup.class) UserInfo userInfo) {
        userInfo.setId(id);
        userInfoService.updateUserInfoById(userInfo);
        return Result.success(userInfoService.getById(id));
    }


    /**
    * 删除用户
    *
    * @param id 用户ID
    * @return 删除结果信息
    */
    @Override
    @DeleteMapping("/{id}")
    public Result<Boolean> deleteById(@PathVariable String id) {
        userInfoService.deleteById(id);
        return Result.success(true);
    }

    /**
    * 删除用户
    *
    * @param idList 用户ID数组
    * @return 删除结果信息
    */
    @Override
    @DeleteMapping("/batchRemove")
    public Result<Boolean> batchRemove(@RequestBody List<String> idList) {
        userInfoService.removeByIds(idList);
        return Result.success(true);
    }
}
