package com.zhongmubao.api.components.hf.response;

import com.google.gson.annotations.SerializedName;

/**
 * 基础相应
 *
 * @author 孙阿龙
 */
public class HfBaseResponse {

    /**
     * 请求标识
     */
    @SerializedName("CmdId")
    private String cmdId;

    /**
     * 响应码
     */
    @SerializedName("RespCode")
    private String respCode;

    /**
     * 响应描述
     */
    @SerializedName("RespDesc")
    private String respDesc;

    /**
     * 签名
     */
    @SerializedName("ChkValue")
    private String chkValue;

    /**
     * 商户号
     */
    @SerializedName("MerCustId")
    private String merCustId;

    public String getCmdId() {
        return cmdId;
    }

    public void setCmdId(String cmdId) {
        this.cmdId = cmdId;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespDesc() {
        return respDesc;
    }

    public void setRespDesc(String respDesc) {
        this.respDesc = respDesc;
    }

    public String getChkValue() {
        return chkValue;
    }

    public void setChkValue(String chkValue) {
        this.chkValue = chkValue;
    }

    public String getMerCustId() {
        return merCustId;
    }

    public void setMerCustId(String merCustId) {
        this.merCustId = merCustId;
    }
}
