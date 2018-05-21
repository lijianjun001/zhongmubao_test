package com.zhongmubao.api.dto.request.mp;

/***
 * 获取微信小程序session和openid的请求类
 * @author 孙阿龙
 */
public class JsCode2SessionRequestModel {
    /**
     * 微信code
     */
    public String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
