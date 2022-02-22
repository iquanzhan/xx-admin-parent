package com.chengxiaoxiao.core.exception;

/**
 * 内部认证异常
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2022/02/21 23:41
 */
public class InnerAuthException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public InnerAuthException(String message)
    {
        super(message);
    }
}
