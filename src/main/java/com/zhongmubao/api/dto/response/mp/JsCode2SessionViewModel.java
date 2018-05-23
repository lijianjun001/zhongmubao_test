package com.zhongmubao.api.dto.response.mp;

/***
 * 获取微信小程序session和openid的返回类
 * @author 孙阿龙
 */
public class JsCode2SessionViewModel {
    /**
     * openId
     */
    public String openId;

    /**
     * session_key
     */
    public String session_key;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }
}
