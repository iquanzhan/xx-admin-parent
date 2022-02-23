package com.chengxiaoxiao.openfeign.annotation;

import java.lang.annotation.*;

/**
 * 取消全局异常处理
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2022-02-23 10:32
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DisGlobalExceptionHandle {
}
