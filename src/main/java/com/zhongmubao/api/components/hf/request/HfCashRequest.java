package com.zhongmubao.api.components.hf.request;

/**
 * 取现请求
 *
 * @author 孙阿龙
 */
public class HfCashRequest {

    private String ordId;

    private String usrCustId;

    private String transAmt;

    private String servFee;

    private String servFeeAcctId;

    private String openAcctId;

    private String remark;

    public String getOrdId() {
        return ordId;
    }

    public void setOrdId(String ordId) {
        this.ordId = ordId;
    }

    public String getUsrCustId() {
        return usrCustId;
    }

    public void setUsrCustId(String usrCustId) {
        this.usrCustId = usrCustId;
    }

    public String getTransAmt() {
        return transAmt;
    }

    public void setTransAmt(String transAmt) {
        this.transAmt = transAmt;
    }

    public String getServFee() {
        return servFee;
    }

    public void setServFee(String servFee) {
        this.servFee = servFee;
    }

    public String getServFeeAcctId() {
        return servFeeAcctId;
    }

    public void setServFeeAcctId(String servFeeAcctId) {
        this.servFeeAcctId = servFeeAcctId;
    }

    public String getOpenAcctId() {
        return openAcctId;
    }

    public void setOpenAcctId(String openAcctId) {
        this.openAcctId = openAcctId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
