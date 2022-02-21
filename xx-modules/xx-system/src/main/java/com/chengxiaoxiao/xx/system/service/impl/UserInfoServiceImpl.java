package com.chengxiaoxiao.xx.system.service.impl;

import com.chengxiaoxiao.xx.system.entity.UserInfo;
import com.chengxiaoxiao.xx.system.mapper.UserInfoMapper;
import com.chengxiaoxiao.xx.system.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


/**
 * 用户 服务实现类
 *
 * @Description:
 * @Author: Cheng xiaoxiao
 * @Date: 2022-02-21 19:19:21
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
    /**
     * 获取用户详情
     *
     * @param id ID
     * @return 单个用户详情
     */
    @Override
    public UserInfo detailById(String id) {
        return this.getById(id);
    }

    /**
     * 增加用户信息
     *
     * @param userInfo 用户信息
     */
    @Override
    public void saveUserInfo(UserInfo userInfo) {
        this.baseMapper.insert(userInfo);
    }

    /**
     * 修改用户
     *
     * @param userInfo 用户表对象
     */
    @Override
    public void updateUserInfoById(UserInfo userInfo) {
        this.baseMapper.updateById(userInfo);
    }

    /**
     * 根据ID删除
     *
     * @param id ID
     */
    @Override
    public void deleteById(String id) {
        this.removeById(id);
    }
}
