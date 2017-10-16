package com.zhongmubao.api.dto.Request;

import com.zhongmubao.api.config.enmu.SmsType;

/**
 * 手机号作为参数，目前用户发送验证码
 */
public class SendSmsCodeRequestModel {
    private String phone;
    private String type;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
