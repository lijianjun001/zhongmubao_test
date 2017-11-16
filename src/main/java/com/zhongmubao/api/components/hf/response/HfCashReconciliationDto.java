package com.zhongmubao.api.components.hf.response;

import com.google.gson.annotations.SerializedName;

/**
 * 取现对账结果串
 *
 * @author 米立林
 */
public class HfCashReconciliationDto {
    /**
     * 由商户的系统生成，必须保证唯一，请使用纯数字
     */
    @SerializedName("OrdId")
    private String ordId;
    /**
     * 由汇付生成，商户的唯一性标识
     */
    @SerializedName("MerCustId")
    private String merCustId;
    /**
     * 由汇付生成，用户的唯一性标识
     */
    @SerializedName("UsrCustId")
    private String usrCustId;
    /**
     * 由汇付生成，用户的唯一性标识
     */
    @SerializedName("CardId")
    private String cardId;
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
     * 商户收取用户的服务费
     */
    @SerializedName("ServFee")
    private String servFee;
    /**
     * 商户用来收取服务费的子账户号
     */
    @SerializedName("ServFeeAcctId")
    private String servFeeAcctId;
    /**
     * 手续费金额（取现手续费+垫资手续费）
     */
    @SerializedName("FeeAmt")
    private String feeAmt;
    /**
     * 手续费金额用户
     */
    @SerializedName("FeeCustId")
    private String feeCustId;
    /**
     * 手续费金额账号
     */
    @SerializedName("FeeAcctId")
    private String feeAcctId;

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

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
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

    public String getFeeAmt() {
        return feeAmt;
    }

    public void setFeeAmt(String feeAmt) {
        this.feeAmt = feeAmt;
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
}
