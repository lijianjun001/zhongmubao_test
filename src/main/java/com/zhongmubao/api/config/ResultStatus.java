package com.zhongmubao.api.config;

/**
 * 返回结果状态
 *
 * @author 米立林
 */
public enum ResultStatus {
    SUCCESS(100, "成功"),
    FAIL(-1, "服务器繁忙，请稍候再试"),
    PARAMETER_MISSING(-2, "参数不完整"),
    PARAMETER_ERROR(-3, "参数错误"),
    USERNAME_OR_PASSWORD_ERROR(-1001, "用户名或密码错误"),
    USER_NOT_FOUND(-1002, "用户不存在"),
    USER_NOT_LOGIN(-1003, "用户未登录"),
    DELAYED_CARD_NOT_EXIT(-1004, "延时卡不存在"),
    RED_PACKAGE_NOT_EXIT(-1005, "红包不存在"),
    MERGE_CARD_NOT_EXIT(-1006, "合并卡不存在"),
    SECRET_GIFT_NOT_EXIT(-1007, "神秘礼物不存在"),
    TELEPHONE_CARD_NOT_EXIT(-1008, "充值卡不存在或已使用"),
    MAX_MONTH_SHAY(-1009, "本月分享达到最大分享数,请下月再来"),
    DEVICE_OFFLINE(-1010, "设备已离线"),
    USER_EXISTS_ERROR(-1011, "用户已存在"),
    USER_PASSWORD_LENGTH_ERROR(-1012, "密码长度应设置在6-16位之间"),
    ACCOUNT_EMPTY_ERROR(-1013, "账号不能为空"),
    PASSWORD_EMPTY_ERROR(-1014, "密码不能为空"),
    DATA_QUERY_FAILED(-2000, "未查到该数据"),
    YUNYING_PHONE_ERROR(-6, "由于运营商维护系统将于2018年1月1日恢复充值"),
    TOUTIAO_CALL_FAILED(-3001, "回传失败"),
    PARAMETER_CODE_ERROR(-4001, "验证码错误"),
    PARAMETER_CODE_INVALID(-4002, "验证码已失效"),
    REDEEM_PASSWORD_ERROR(-4003, "赎回密码错误"),
    INVALID_PHONE_ERROR(-4004, "不是有效的手机号"),
    INVALID_RECOMMEND_ERROR(-4005, "推荐人不存在"),
    INVALID_RECOMMEND_CODE_ERROR(-4006, "推荐码不能为空");

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
