package com.zhongmubao.api.dto.request.customer;

import com.zhongmubao.api.dto.request.BaseRequest;

/**
 * 登录请求参数
 *
 * @author 米立林
 */
public class LoginRequestModel extends BaseRequest {
    /**
     * 登录账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
    /**
     * 短信验证码
     */
    private String code;
    /**
     * 请求ip
     */
    private String ip;


    /**
     * 微信ID
     */
    private String openId;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
