package com.chengxiaoxiao.xxadmin.system.api;


import com.chengxiaoxiao.core.constant.SecurityConstant;
import com.chengxiaoxiao.core.constant.ServiceNameConstant;
import com.chengxiaoxiao.core.vo.Result;
import com.chengxiaoxiao.xxadmin.system.entity.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * 远程调用用户接口
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2022-02-23 9:45
 */
@FeignClient(value = ServiceNameConstant.SYSTEM_SERVICE,url = "http://localhost:9001")
public interface RemoteUserInfoService {
    /**
     * 根据用户名查询用户信息
     *
     * @param userName 用户名
     * @param source   接口来源
     * @return 用户基本信息
     */
    @GetMapping("/user-info/info/{userName}")
    Result<UserInfo> getUserInfoByUserName(@PathVariable("userName") String userName, @RequestHeader(SecurityConstant.FROM_SOURCE) String source);
}
