package com.zhongmubao.api.dto.response.my.transaction;

/**
 * 交易明细视图实体
 *
 * @author 米立林
 */
public class TransactionViewModel {
    public TransactionViewModel() {
    }

    public TransactionViewModel(String transactionType, String title, String transactionAmount, String id) {
        this.transactionType = transactionType;
        this.title = title;
        this.transactionAmount = transactionAmount;
        this.id = id;
    }

    /**
     * 交易号
     */
    private String id;

    /**
     * 交易类型
     * 00 充值 01 赎回 02 提现 03 投资/买羊
     */
    private String transactionType;

    /**
     * 标题
     */
    private String title;

    /**
     * 交易日期
     */
    private String transactionDate;

    /**
     * 交易金额
     */
    private String transactionAmount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
}
