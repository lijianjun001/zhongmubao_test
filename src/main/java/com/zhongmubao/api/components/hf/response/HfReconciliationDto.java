package com.zhongmubao.api.components.hf.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * 对账结果单
 *
 * @author 米立林
 */
public class HfReconciliationDto {
    /**
     * 由商户的系统生成，必须保证唯一，请使用纯数字
     */
    @SerializedName("OrdId")
    private String ordId;
    /**
     * YYYYMMDD，例如：20130307
     */
    @SerializedName("OrdDate")
    private String ordDate;
    /**
     * 由汇付生成，商户的唯一性标识
     */
    @SerializedName("MerCustId")
    private String merCustId;
    /**
     * 投资人客户号，由汇付生成，用户的唯一性标识
     */
    @SerializedName("InvestCustId")
    private String investCustId;
    /**
     * 借款人客户号，由汇付生成，用户的唯一性标识
     */
    @SerializedName("BorrCustId")
    private String borrCustId;
    /**
     * 泛指交易金额，如充值、支付、取现、冻结和解冻金额（金额格式必须是###.00）比如 2.00，2.01
     */
    @SerializedName("TransAmt")
    private String transAmt;
    /**
     * 汇付交易状态
     * I--初始
     * P--部分成功
     */
    @SerializedName("TransStat")
    private String transStat;
    /**
     * YYYYMMDD 格式
     */
    @SerializedName("PnrDate")
    private String pnrDate;
    /**
     * 汇付交易流水
     */
    @SerializedName("PnrSeqId")
    private String pnrSeqId;
    /**
     * 分账客户号
     */
    @SerializedName("DivDetails")
    private ArrayList<String> divDetails;
    /**
     * 分账账户号
     */
    @SerializedName("DivAcctId")
    private String divAcctId;
    /**
     * 分账金额
     */
    @SerializedName("DivAmt")
    private String divAmt;
    /**
     * 还款本金
     */
    @SerializedName("PrincipalAmt")
    private String principalAmt;
    /**
     * 还款利息
     */
    @SerializedName("InterestAmt")
    private String interestAmt;

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

    public String getMerCustId() {
        return merCustId;
    }

    public void setMerCustId(String merCustId) {
        this.merCustId = merCustId;
    }

    public String getInvestCustId() {
        return investCustId;
    }

    public void setInvestCustId(String investCustId) {
        this.investCustId = investCustId;
    }

    public String getBorrCustId() {
        return borrCustId;
    }

    public void setBorrCustId(String borrCustId) {
        this.borrCustId = borrCustId;
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

    public ArrayList<String> getDivDetails() {
        return divDetails;
    }

    public void setDivDetails(ArrayList<String> divDetails) {
        this.divDetails = divDetails;
    }

    public String getDivAcctId() {
        return divAcctId;
    }

    public void setDivAcctId(String divAcctId) {
        this.divAcctId = divAcctId;
    }

    public String getDivAmt() {
        return divAmt;
    }

    public void setDivAmt(String divAmt) {
        this.divAmt = divAmt;
    }

    public String getPrincipalAmt() {
        return principalAmt;
    }

    public void setPrincipalAmt(String principalAmt) {
        this.principalAmt = principalAmt;
    }

    public String getInterestAmt() {
        return interestAmt;
    }

    public void setInterestAmt(String interestAmt) {
        this.interestAmt = interestAmt;
    }
}
