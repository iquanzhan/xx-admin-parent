package com.chengxiaoxiao.xxadmin.system.api;

import cn.hutool.system.UserInfo;
import com.chengxiaoxiao.core.constant.SecurityConstant;
import com.chengxiaoxiao.core.constant.ServiceNameConstant;
import com.chengxiaoxiao.core.vo.Result;
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
@FeignClient(value = ServiceNameConstant.SYSTEM_SERVICE)
public interface RemoteUserInfoService {
    /**
     * 根据用户名查询用户信息
     * @param userName 用户名
     * @param source 接口来源
     * @return 用户基本信息
     */
    @GetMapping("/info/{userName}")
    Result<UserInfo> list(@PathVariable("userName") String userName, @RequestHeader(SecurityConstant.FROM_SOURCE) String source);
}
