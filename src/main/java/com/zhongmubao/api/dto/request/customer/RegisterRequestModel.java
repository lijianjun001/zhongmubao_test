package com.zhongmubao.api.dto.request.customer;

import com.zhongmubao.api.dto.request.BaseRequest;

/**
 * 用户注册
 * @author 米立林
 */
public class RegisterRequestModel extends BaseRequest {
    private String account;
    private String password;
    private String referenceCode;
    private String smsCode;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getReferenceCode() {
        return referenceCode;
    }

    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }
}
