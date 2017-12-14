package com.zhongmubao.api.dto.response.my.transaction;

import com.google.gson.annotations.SerializedName;


/**
 * 充值详情
 *
 * @author 米立林
 */
public class RechargeDetailModel {
    public RechargeDetailModel() {
    }

    public RechargeDetailModel(String transactionAmount, String rechargeMethod, String transactionDate) {
        this.transactionAmount = transactionAmount;
        this.rechargeMethod = rechargeMethod;
        this.transactionDate = transactionDate;
    }

    /**
     * 交易金额
     */
    @SerializedName("TransactionAmount")
    private String transactionAmount;

    /**
     * 充值方式
     */
    @SerializedName("RechargeMethod")
    private String rechargeMethod;

    /**
     * 充值时间
     */
    @SerializedName("TransactionDate")
    private String transactionDate;

    public String getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getRechargeMethod() {
        return rechargeMethod;
    }

    public void setRechargeMethod(String rechargeMethod) {
        this.rechargeMethod = rechargeMethod;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }
}
