package com.zhongmubao.api.config;

public enum ResultStatus {
    SUCCESS(100, "成功"),
    FAIL(-1, "服务器繁忙，请稍候再试"),
    PARAMETER_MISSING(-2, "参数不完整"),
    USERNAME_OR_PASSWORD_ERROR(-1001, "用户名或密码错误"),
    USER_NOT_FOUND(-1002, "用户不存在"),
    USER_NOT_LOGIN(-1003, "用户未登录"),
    DELAYED_CARD_NOT_EXIT(-1004, "延时卡不存在"),
    RED_PACKAGE_NOT_EXIT(-1005, "红包不存在"),
    MEGRE_CARD_NOT_EXIT(-1006, "合并卡不存在"),
    SECRET_GIFT_NOT_EXIT(-1007, "神秘礼物不存在");

    /**
     * 返回码
     */
    private int code;

    /**
     * 返回结果描述
     */
    private String message;

    ResultStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
