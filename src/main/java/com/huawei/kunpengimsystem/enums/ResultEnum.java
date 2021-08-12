package com.huawei.kunpengimsystem.enums;

public enum ResultEnum {
    /**
     * 成功
     */
    SUCCESS(1001),

    /**
     * 失败
     */
    FAIL(1000),

    /**
     * 接口没找到
     */
    NOT_FOUND(1002),

    /**
     * 服务器内部错误
     */
    INTERNAL_SERVER_ERROR(1003);

    public int code;
    ResultEnum(int code) {
        this.code = code;
    }
}
