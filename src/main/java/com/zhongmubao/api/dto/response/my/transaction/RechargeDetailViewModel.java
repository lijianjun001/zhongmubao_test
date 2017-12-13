package com.zhongmubao.api.dto.response.my.transaction;

/**
 * 充值详情
 * @author 米立林
 */
public class RechargeDetailViewModel {
    public RechargeDetailViewModel() {
    }

    public RechargeDetailViewModel(String transactionAmount, String rechargeMethod, String transactionDate, String balance) {
        this.transactionAmount = transactionAmount;
        this.rechargeMethod = rechargeMethod;
        this.transactionDate = transactionDate;
        this.balance = balance;
    }

    /**
     * 交易金额
     */
    private String transactionAmount;

    /**
     * 充值方式
     */
    private String rechargeMethod;

    /**
     * 充值时间
     */
    private String transactionDate;

    /**
     * 账户余额
     */
    private String balance;

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
