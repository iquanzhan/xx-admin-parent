package com.chengxiaoxiao.core.exception;

import org.apache.commons.lang3.StringUtils;

/**
 * 没有对应角色异常信息
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2022-02-22 14:46
 */
public class NotRoleException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NotRoleException(String message) {
        super(message);
    }

    public NotRoleException(String[] roles) {
        super(StringUtils.join(roles, ","));
    }
}