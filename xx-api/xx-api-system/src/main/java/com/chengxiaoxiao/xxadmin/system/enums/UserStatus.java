package com.chengxiaoxiao.xxadmin.system.enums;

/**
 * 用户状态信息
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2022-02-24 17:35
 */
public enum UserStatus {
    /**
     * "正常"
     */
    OK(0, "正常"),
    /**
     * 停用
     */
    DISABLE(1, "停用");

    private final Integer code;
    private final String info;

    UserStatus(Integer code, String info) {
        this.code = code;
        this.info = info;
    }

    public Integer getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
