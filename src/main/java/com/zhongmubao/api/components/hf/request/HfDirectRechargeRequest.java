package com.zhongmubao.api.components.hf.request;

/**
 * 充值请求参数
 *
 * @author 孙阿龙
 */
public class HfDirectRechargeRequest {

    private String merCustId;

    private String usrCustId;

    private String ordId;

    private Double transAmt;

    private String smsSeq;

    private String smsCode;

    private String chkValue;

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

    public String getOrdId() {
        return ordId;
    }

    public void setOrdId(String ordId) {
        this.ordId = ordId;
    }

    public Double getTransAmt() {
        return transAmt;
    }

    public void setTransAmt(Double transAmt) {
        this.transAmt = transAmt;
    }

    public String getChkValue() {
        return chkValue;
    }

    public void setChkValue(String chkValue) {
        this.chkValue = chkValue;
    }

    public String getSmsSeq() {
        return smsSeq;
    }

    public void setSmsSeq(String smsSeq) {
        this.smsSeq = smsSeq;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

}
