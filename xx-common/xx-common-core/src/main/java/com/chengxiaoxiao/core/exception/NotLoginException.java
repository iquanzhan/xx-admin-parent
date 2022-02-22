package com.chengxiaoxiao.core.exception;

/**
 * 用户没有登录异常
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2022/2/22  11:44
 */
public class NotLoginException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public NotLoginException(String message)
    {
        super(message);
    }
}