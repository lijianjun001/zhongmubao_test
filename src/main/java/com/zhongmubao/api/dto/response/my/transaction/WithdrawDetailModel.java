package com.zhongmubao.api.dto.response.my.transaction;

import com.google.gson.annotations.SerializedName;


/**
 * 提现详情
 *
 * @author 米立林
 */
public class WithdrawDetailModel {
    public WithdrawDetailModel() {
    }

    public WithdrawDetailModel(String bankImage, String bankName, String transactionAmount, String rechargeMethod, String transactionDate, String balance) {
        this.bankImage = bankImage;
        this.bankName = bankName;
        this.transactionAmount = transactionAmount;
        this.rechargeMethod = rechargeMethod;
        this.transactionDate = transactionDate;
        this.balance = balance;
    }

    /**
     * 银行图标
     */
    @SerializedName("BankImage")
    private String bankImage;

    /**
     * 银行名称
     */
    @SerializedName("BankName")
    private String bankName;

    /**
     * 交易金额
     */
    @SerializedName("TransactionAmount")
    private String transactionAmount;

    /**
     * 提现说明
     */
    @SerializedName("RechargeMethod")
    private String rechargeMethod;

    /**
     * 提现时间
     */
    @SerializedName("TransactionDate")
    private String transactionDate;

    /**
     * 账户余额
     */
    @SerializedName("Balance")
    private String balance;

    public String getBackImage() {
        return bankImage;
    }

    public void setBackImage(String bankImage) {
        this.bankImage = bankImage;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

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

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
