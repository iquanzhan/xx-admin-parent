package com.chengxiaoxiao.xx.system.service;

import com.chengxiaoxiao.xx.system.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 用户信息 服务类
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2022-02-22 22:42:36
 */
public interface UserInfoService extends IService<UserInfo> {
    /**
     * 获取用户信息详情
     *
     * @param id ID
     * @return 单个用户信息详情
     */
    UserInfo detailById(String id);

    /**
     * 增加用户信息
     *
     * @param userInfo 用户信息
     */
    void saveUserInfo(UserInfo userInfo);

    /**
     * 修改用户信息
     *
     * @param userInfo 用户信息表对象
     */
    void updateUserInfoById(UserInfo userInfo);

    /**
     * 根据ID删除
     *
     * @param id ID
     */
    void deleteById(String id);

    /**
     * 根据用户名获取用户信息
     * @param userName 用户名
     * @return 用户信息
     */
    UserInfo getUserInfoByUserName(String userName);
}
