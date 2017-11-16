package com.zhongmubao.api.components.hf.response;

import com.google.gson.annotations.SerializedName;

/**
 * 商户扣款对账结果HfTrfReconciliationDto
 *
 * @author 孙阿龙
 */
public class HfTrfReconciliationDto {

    @SerializedName("OrdId")
    private String ordId;

    @SerializedName("MerCustId")
    private String merCustId;

    @SerializedName("UsrCustId")
    private String usrCustId;

    @SerializedName("TransAmt")
    private String transAmt;

    @SerializedName("TransStat")
    private String transStat;

    @SerializedName("PnrDate")
    private String pnrDate;

    @SerializedName("PnrSeqId")
    private String pnrSeqId;

    public String getOrdId() {
        return ordId;
    }

    public void setOrdId(String ordId) {
        this.ordId = ordId;
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

    public String getTransAmt() {
        return transAmt;
    }

    public void setTransAmt(String transAmt) {
        this.transAmt = transAmt;
    }

    public String getTransStat() {
        return transStat;
    }

    public void setTransStat(String transStat) {
        this.transStat = transStat;
    }

    public String getPnrDate() {
        return pnrDate;
    }

    public void setPnrDate(String pnrDate) {
        this.pnrDate = pnrDate;
    }

    public String getPnrSeqId() {
        return pnrSeqId;
    }

    public void setPnrSeqId(String pnrSeqId) {
        this.pnrSeqId = pnrSeqId;
    }

}
