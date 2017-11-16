package com.zhongmubao.api.components.hf.response;

import com.google.gson.annotations.SerializedName;

/**
 * 充值对账结果串
 * @author 米立林
 */
public class HfSaveReconciliationDto {
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
     * 订单日期
     */
    @SerializedName("OrdDate")
    private String ordDate;
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
     * 网关的细分业务类型
     * B2C--B2C 网银支付 B2B--B2B 网银支付
     */
    @SerializedName("GateBusiId")
    private String gateBusiId;
    /**
     * 开户银行代号
     */
    @SerializedName("OpenBankId")
    private String openBankId;
    /**
     * 开户银行账号
     */
    @SerializedName("OpenAcctId")
    private String openAcctId;
    /**
     * 手续费金额（取现手续费+垫资手续费）
     */
    @SerializedName("FeeAmt")
    private String feeAmt;
    /**
     * 手续费扣款客户号
     */
    @SerializedName("FeeCustId")
    private String feeCustId;
    /**
     * 手续费扣款子账户号
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

    public String getOrdDate() {
        return ordDate;
    }

    public void setOrdDate(String ordDate) {
        this.ordDate = ordDate;
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

    public String getGateBusiId() {
        return gateBusiId;
    }

    public void setGateBusiId(String gateBusiId) {
        this.gateBusiId = gateBusiId;
    }

    public String getOpenBankId() {
        return openBankId;
    }

    public void setOpenBankId(String openBankId) {
        this.openBankId = openBankId;
    }

    public String getOpenAcctId() {
        return openAcctId;
    }

    public void setOpenAcctId(String openAcctId) {
        this.openAcctId = openAcctId;
    }

    public String getFeeAmt() {
        return feeAmt;
    }

    public void setFeeAmt(String feeAmt) {
        this.feeAmt = feeAmt;
    }

    public String getFeeCustId() {
        return feeCustId;
    }

    public void setFeeCustId(String feeCustId) {
        this.feeCustId = feeCustId;
    }

    public String getFeeAcctId() {
        return feeAcctId;
    }

    public void setFeeAcctId(String feeAcctId) {
        this.feeAcctId = feeAcctId;
    }
}
