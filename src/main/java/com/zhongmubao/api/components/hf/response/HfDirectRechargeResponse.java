package com.zhongmubao.api.components.hf.response;

import com.google.gson.annotations.SerializedName;

/**
 * 充值响应
 *
 * @author 孙阿龙
 */
public class HfDirectRechargeResponse extends HfBaseResponse {

    @SerializedName("SecRespCode")
    private String secRespCode;

    @SerializedName("SecRespDesc")
    private String secRespDesc;

    @SerializedName("UsrCustId")
    private String usrCustId;

    @SerializedName("OrdId")
    private String ordId;

    @SerializedName("OrdDate")
    private String ordDate;

    @SerializedName("TrxId")
    private String trxId;

    @SerializedName("DepoBankSeq")
    private String depoBankSeq;

    @SerializedName("GateBusiId")
    private String gateBusiId;

    @SerializedName("BankId")
    private String bankId;

    @SerializedName("TransAmt")
    private String transAmt;

    @SerializedName("ChkValue")
    private String chkValue;

    @SerializedName("FeeAcctId")
    private String feeAcctId;

    @SerializedName("FeeCustId")
    private String feeCustId;

    @SerializedName("RetUrl")
    private String retUrl;

    @SerializedName("BgRetUrl")
    private String bgRetUrl;

    @SerializedName("FeeAmt")
    private String feeAmt;

    public String getSecRespCode() {
        return secRespCode;
    }

    public void setSecRespCode(String secRespCode) {
        this.secRespCode = secRespCode;
    }

    public String getSecRespDesc() {
        return secRespDesc;
    }

    public void setSecRespDesc(String secRespDesc) {
        this.secRespDesc = secRespDesc;
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

    public String getOrdDate() {
        return ordDate;
    }

    public void setOrdDate(String ordDate) {
        this.ordDate = ordDate;
    }

    public String getTrxId() {
        return trxId;
    }

    public void setTrxId(String trxId) {
        this.trxId = trxId;
    }

    public String getDepoBankSeq() {
        return depoBankSeq;
    }

    public void setDepoBankSeq(String depoBankSeq) {
        this.depoBankSeq = depoBankSeq;
    }

    public String getGateBusiId() {
        return gateBusiId;
    }

    public void setGateBusiId(String gateBusiId) {
        this.gateBusiId = gateBusiId;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getTransAmt() {
        return transAmt;
    }

    public void setTransAmt(String transAmt) {
        this.transAmt = transAmt;
    }

    @Override
    public String getChkValue() {
        return chkValue;
    }

    @Override
    public void setChkValue(String chkValue) {
        this.chkValue = chkValue;
    }

    public String getFeeAcctId() {
        return feeAcctId;
    }

    public void setFeeAcctId(String feeAcctId) {
        this.feeAcctId = feeAcctId;
    }

    public String getFeeCustId() {
        return feeCustId;
    }

    public void setFeeCustId(String feeCustId) {
        this.feeCustId = feeCustId;
    }

    public String getRetUrl() {
        return retUrl;
    }

    public void setRetUrl(String retUrl) {
        this.retUrl = retUrl;
    }

    public String getBgRetUrl() {
        return bgRetUrl;
    }

    public void setBgRetUrl(String bgRetUrl) {
        this.bgRetUrl = bgRetUrl;
    }

    public String getFeeAmt() {
        return feeAmt;
    }

    public void setFeeAmt(String feeAmt) {
        this.feeAmt = feeAmt;
    }
}
