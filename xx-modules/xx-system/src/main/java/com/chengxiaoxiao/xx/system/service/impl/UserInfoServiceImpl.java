package com.chengxiaoxiao.xx.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chengxiaoxiao.core.exception.GlobalException;
import com.chengxiaoxiao.core.vo.CodeMsg;
import com.chengxiaoxiao.xxadmin.system.entity.UserInfo;
import com.chengxiaoxiao.xx.system.mapper.UserInfoMapper;
import com.chengxiaoxiao.xx.system.service.UserInfoService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 用户信息 服务实现类
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2022-02-22 22:42:36
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 获取用户信息详情
     *
     * @param id ID
     * @return 单个用户信息详情
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
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        this.baseMapper.insert(userInfo);
    }

    /**
     * 修改用户信息
     *
     * @param userInfo 用户信息表对象
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

    /**
     * 根据用户名获取用户信息
     *
     * @param userName 用户名
     * @return 用户信息
     */
    @Override
    public UserInfo getUserInfoByUserName(String userName) {
        UserInfo userInfo = this.getOne(new QueryWrapper<UserInfo>().eq("user_name", userName));
        if (null == userInfo) {
            throw new GlobalException(CodeMsg.USER_PASSWORD_INCORRENT);
        }
        return userInfo;
    }
}
