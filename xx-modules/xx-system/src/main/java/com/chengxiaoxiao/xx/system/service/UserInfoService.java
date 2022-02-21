package com.chengxiaoxiao.xx.system.service;

import com.chengxiaoxiao.xx.system.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 用户 服务类
 *
 * @Description:
 * @Author: Cheng xiaoxiao
 * @Date: 2022-02-21 19:19:21
 */
public interface UserInfoService extends IService<UserInfo> {
    /**
     * 获取用户详情
     *
     * @param id ID
     * @return 单个用户详情
     */
    UserInfo detailById(String id);

    /**
     * 增加用户信息
     *
     * @param userInfo 用户信息
     */
    void saveUserInfo(UserInfo userInfo);

    /**
     * 修改用户
     *
     * @param userInfo 用户表对象
     */
    void updateUserInfoById(UserInfo userInfo);

    /**
     * 根据ID删除
     *
     * @param id ID
     */
    void deleteById(String id);
}
