package com.chengxiaoxiao.openfeign.aspect;

import com.chengxiaoxiao.core.exception.GlobalException;
import com.chengxiaoxiao.core.vo.CodeMsg;
import com.chengxiaoxiao.core.vo.Result;
import com.chengxiaoxiao.openfeign.annotation.DisGlobalExceptionHandle;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.ProxyMethodInvocation;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * FeignClient 拦截器，接口出现异常信息的统一处理
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2022-02-23 10:17
 */
@Aspect
@Component
@FeignClient
public class FeignClientAspect {

    @AfterReturning(value = "@within(org.springframework.cloud.openfeign.FeignClient) *(..)", returning = "returnVal")
    public void before(JoinPoint joinPoint, Object returnVal) throws Exception {
        try {
            MethodInvocationProceedingJoinPoint methodInvocationProceedingJoinPoint = (MethodInvocationProceedingJoinPoint) joinPoint;
            Field field = methodInvocationProceedingJoinPoint.getClass().getDeclaredField("methodInvocation");
            field.setAccessible(true);

            ProxyMethodInvocation proxyMethodInvocation = (ProxyMethodInvocation) field.get(methodInvocationProceedingJoinPoint);

            if (!proxyMethodInvocation.getMethod().isAnnotationPresent(DisGlobalExceptionHandle.class)
                    && returnVal instanceof Result) {
                Result<?> result = (Result<?>) returnVal;

                if (CodeMsg.SUCCESS.getCode().equals(result.getCode())) {
                    throw new GlobalException(new CodeMsg(result.getCode(), result.getMsg()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }
}
