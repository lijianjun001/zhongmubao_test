package com.zhongmubao.api.components.hf.request;

/**
 * 基础请求类
 *
 * @author 孙阿龙
 */
public class HfBaseRequest {

    /**
     * 签名验证
     */
    private String chkValue;


    public String getChkValue() {
        return chkValue;
    }

    public void setChkValue(String chkValue) {
        this.chkValue = chkValue;
    }

}
