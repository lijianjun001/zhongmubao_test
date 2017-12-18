package com.zhongmubao.api.dto.response.my.transaction;


/**
 * 交易详情记录视图实体
 *
 * @author 米立林
 */
public class TransactionDetailViewModel {
    public TransactionDetailViewModel() {
    }

    public TransactionDetailViewModel(String billType) {
        this.billType = billType;
    }

    /**
     * 账单类型（充值、提现、买羊、赎回）
     */
    private String billType;

    /**
     * 交易详情json
     */
    private String transactionDetail;

    /**
     * 账户余额
     */
    private String balance;

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getTransactionDetail() {
        return transactionDetail;
    }

    public void setTransactionDetail(String transactionDetail) {
        this.transactionDetail = transactionDetail;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
