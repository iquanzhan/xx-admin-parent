package com.chengxiaoxiao.security.aspect;

import com.chengxiaoxiao.core.constant.SecurityConstant;
import com.chengxiaoxiao.core.exception.InnerAuthException;
import com.chengxiaoxiao.core.util.RequestUtil;
import com.chengxiaoxiao.security.annotation.InnerAuth;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.core.Ordered;

import java.util.Objects;

/**
 * 内部服务调用验证处理
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2022/2/21  22:57
 */
public class InnerAuthAspect implements Ordered {
    @Around("@annotation(innerAuth)")
    public Object innerAround(ProceedingJoinPoint point, InnerAuth innerAuth) throws Throwable {

        String source = Objects.requireNonNull(RequestUtil.getRequest()).getHeader(SecurityConstant.FROM_SOURCE);

        // 内部请求验证
        if (!StringUtils.equals(SecurityConstant.INNER, source)) {
            throw new InnerAuthException("没有内部访问权限，不允许访问");
        }

        // 用户信息验证
        String userid = RequestUtil.getRequest().getHeader(SecurityConstant.USER_ID);

        if (innerAuth.isUser() && StringUtils.isEmpty(userid)) {
            throw new InnerAuthException("内部调用失败：没有设置用户信息，不允许访问 ");
        }
        return point.proceed();
    }

    /**
     * 确保在权限认证aop执行前执行
     */
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 1;
    }
}
