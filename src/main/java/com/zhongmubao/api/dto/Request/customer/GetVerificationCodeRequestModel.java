package com.zhongmubao.api.dto.Request.customer;

public class GetVerificationCodeRequestModel {
    private String type;
    private String phone;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
