package com.zhongmubao.api.components.hf.response;

import com.google.gson.annotations.SerializedName;

/**
 * 发送短信请求结果
 *
 * @author 孙阿龙
 */
public class HfSendSmsCodeResponse extends HfBaseResponse {

    @SerializedName("UsrCustId")
    private String usrCustId;

    @SerializedName("BusiType")
    private String busiType;

    @SerializedName("SmsSeq")
    private String smsSeq;

    @SerializedName("DepoBankSeq")
    private String depoBankSeq;

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
