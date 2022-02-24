package com.chengxiaoxiao.xx.system.controller;

import com.chengxiaoxiao.xxadmin.system.entity.UserInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chengxiaoxiao.core.validator.group.AddGroup;
import com.chengxiaoxiao.core.validator.group.UpdateGroup;
import com.chengxiaoxiao.core.vo.PageResult;
import com.chengxiaoxiao.core.vo.Result;
import com.chengxiaoxiao.xx.system.api.UserInfoControllerApi;
import com.chengxiaoxiao.xx.system.service.UserInfoService;
import com.chengxiaoxiao.xx.system.vo.query.UserInfoQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 用户信息 接口控制器
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2022-02-22 22:42:36
 */
@RestController
@RequestMapping("/user-info")
public class UserInfoController implements UserInfoControllerApi {
    @Resource
    private UserInfoService userInfoService;


    /**
     * 获取条件查询信息
     *
     * @param userInfoQuery 用户查询条件
     * @return MP查询封装
     */
    private QueryWrapper<UserInfo> getUserInfoQueryWrapper(UserInfoQuery userInfoQuery) {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(userInfoQuery.getUserName())) {
            wrapper.like("user_name", userInfoQuery.getUserName());
        }
        if (!StringUtils.isEmpty(userInfoQuery.getNickName())) {
            wrapper.like("nick_name", userInfoQuery.getNickName());
        }
        if (!StringUtils.isEmpty(userInfoQuery.getEmail())) {
            wrapper.like("email", userInfoQuery.getEmail());
        }
        if (!StringUtils.isEmpty(userInfoQuery.getPhoneNumber())) {
            wrapper.like("phone_number", userInfoQuery.getPhoneNumber());
        }
        if (null != userInfoQuery.getSex()) {
            wrapper.eq("sex", userInfoQuery.getSex());
        }
        if (null != userInfoQuery.getStatus()) {
            wrapper.eq("status", userInfoQuery.getStatus());
        }
        if (!StringUtils.isEmpty(userInfoQuery.getRemark())) {
            wrapper.like("remark", userInfoQuery.getRemark());
        }
        return wrapper;
    }

    /**
     * 按照条件查询用户信息
     *
     * @param userInfoQuery 查询条件
     * @return 结果信息
     */
    @Override
    @GetMapping
    public Result<List<UserInfo>> list(UserInfoQuery userInfoQuery) {
        return Result.success(userInfoService.list(getUserInfoQueryWrapper(userInfoQuery)));
    }

    /**
     * 分页及条件查询用户信息
     *
     * @param page          页码
     * @param size          分页大小
     * @param userInfoQuery 查询条件
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

        userInfoService.page(pageParam, getUserInfoQueryWrapper(userInfoQuery));
        PageResult<UserInfo> pageResult = new PageResult<UserInfo>(pageParam.getTotal(), pageParam.getSize(), pageParam.getCurrent(), pageParam.getRecords());

        return Result.success(pageResult);
    }

    /**
     * 根据用户名获取用户登录信息
     *
     * @param userName 用户名
     * @return 用户信息
     */
    @GetMapping("/info/{userName}")
    @Override
    public Result<UserInfo> getUserInfoByUserName(@PathVariable("userName") String userName) {
        return Result.success(userInfoService.getUserInfoByUserName(userName));
    }

    /**
     * 根据ID获取用户信息详情
     *
     * @param id 用户信息ID
     * @return 用户信息详细信息
     */
    @Override
    @GetMapping("/{id}")
    public Result<UserInfo> detail(@PathVariable String id) {
        return Result.success(userInfoService.detailById(id));
    }

    /**
     * 增加用户信息
     *
     * @param userInfo 用户信息增加实体
     * @return 增加后的用户信息
     */
    @Override
    @PostMapping
    public Result<UserInfo> add(@RequestBody @Validated(AddGroup.class) UserInfo userInfo) {
        userInfoService.saveUserInfo(userInfo);
        return Result.success(userInfo);
    }

    /**
     * 修改用户信息
     *
     * @param id       用户信息ID
     * @param userInfo 用户信息修改实体
     * @return 修改后的用户信息
     */
    @Override
    @PutMapping("/{id}")
    public Result<UserInfo> updateById(@PathVariable String id, @RequestBody @Validated(UpdateGroup.class) UserInfo userInfo) {
        userInfo.setId(id);
        userInfoService.updateUserInfoById(userInfo);
        return Result.success(userInfoService.getById(id));
    }


    /**
     * 删除用户信息
     *
     * @param id 用户信息ID
     * @return 删除结果信息
     */
    @Override
    @DeleteMapping("/{id}")
    public Result<Boolean> deleteById(@PathVariable String id) {
        userInfoService.deleteById(id);
        return Result.success(true);
    }

    /**
     * 删除用户信息
     *
     * @param idList 用户信息ID数组
     * @return 删除结果信息
     */
    @Override
    @DeleteMapping("/batchRemove")
    public Result<Boolean> batchRemove(@RequestBody List<String> idList) {
        userInfoService.removeByIds(idList);
        return Result.success(true);
    }
}
