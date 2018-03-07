package com.zhongmubao.api.dto.request.system;

/**
 * 发送验证码
 * @author 米立林
 */
public class SendSmsRequestModel {
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
