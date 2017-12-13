package com.zhongmubao.api.dto.response.my.transaction;

/**
 * 提现详情
 * @author 米立林
 */
public class WithdrawDetailViewModel {
    public WithdrawDetailViewModel() {
    }

    public WithdrawDetailViewModel(String backImage, String bankName, String transactionAmount, String rechargeMethod, String transactionDate, String balance) {
        this.backImage = backImage;
        this.bankName = bankName;
        this.transactionAmount = transactionAmount;
        this.rechargeMethod = rechargeMethod;
        this.transactionDate = transactionDate;
        this.balance = balance;
    }

    /**
     * 银行图标
     */
    private String backImage;

    /**
     * 银行名称
     */
    private String bankName;

    /**
     * 交易金额
     */
    private String transactionAmount;

    /**
     * 提现说明
     */
    private String rechargeMethod;

    /**
     * 提现时间
     */
    private String transactionDate;

    /**
     * 账户余额
     */
    private String balance;

    public String getBackImage() {
        return backImage;
    }

    public void setBackImage(String backImage) {
        this.backImage = backImage;
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
