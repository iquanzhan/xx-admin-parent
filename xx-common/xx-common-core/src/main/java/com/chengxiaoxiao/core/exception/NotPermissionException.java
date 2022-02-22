package com.chengxiaoxiao.core.exception;

import org.apache.commons.lang3.StringUtils;

/**
 * 不包含指定权限异常
 *
 * @Description:
 * @Author: Cheng Xiaoxiao
 * @Date: 2022-02-22 14:54
 */
public class NotPermissionException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NotPermissionException(String message) {
        super(message);
    }

    public NotPermissionException(String[] roles) {
        super(StringUtils.join(roles, ","));
    }
}