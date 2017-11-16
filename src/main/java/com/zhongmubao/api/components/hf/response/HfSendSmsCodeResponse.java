package com.zhongmubao.api.components.hf.response;

import com.google.gson.annotations.SerializedName;

/**
 * 发送短信请求结果
 *
 * @author 孙阿龙
 */
public class HfSendSmsCodeResponse {

    @SerializedName("CmdId")
    private String cmdId;

    @SerializedName("RespCode")
    private String respCode;

    @SerializedName("RespDesc")
    private String respDesc;

    @SerializedName("ChkValue")
    private String chkValue;

    @SerializedName("MerCustId")
    private String merCustId;

    @SerializedName("UsrCustId")
    private String usrCustId;

    @SerializedName("BusiType")
    private String busiType;

    @SerializedName("SmsSeq")
    private String smsSeq;

    @SerializedName("DepoBankSeq")
    private String depoBankSeq;

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

    public String getUsrCustId() {
        return usrCustId;
    }

    public void setUsrCustId(String usrCustId) {
        this.usrCustId = usrCustId;
    }

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    public String getSmsSeq() {
        return smsSeq;
    }

    public void setSmsSeq(String smsSeq) {
        this.smsSeq = smsSeq;
    }

    public String getDepoBankSeq() {
        return depoBankSeq;
    }

    public void setDepoBankSeq(String depoBankSeq) {
        this.depoBankSeq = depoBankSeq;
    }
}
