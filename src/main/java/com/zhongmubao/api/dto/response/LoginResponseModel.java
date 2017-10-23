package com.zhongmubao.api.dto.response;

public class LoginResponseModel {
    //随机生成的uuid
    private String token;

    public LoginResponseModel() {

    }
    public LoginResponseModel(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
