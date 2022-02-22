package com.chengxiaoxiao.security.annotation;

import java.lang.annotation.*;

/**
 * 内部认证注解
 *
 * @Description: 主要用来校验仅支持内部请求
 * @Author: Cheng XiaoXiao
 * @Date: 2022/2/21  22:50
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InnerAuth {
    /**
     * 是否校验用户信息
     */
    boolean isUser() default false;
}
