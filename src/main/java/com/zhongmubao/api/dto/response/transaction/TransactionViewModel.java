package com.zhongmubao.api.dto.response.transaction;

/**
 * 交易明细视图实体
 *
 * @author 米立林
 */
public class TransactionViewModel {
    public TransactionViewModel() {
    }

    public TransactionViewModel(String imageIcon, String title, String transactionDate, String transactionAmount, String transactionNum) {
        this.imageIcon = imageIcon;
        this.title = title;
        this.transactionDate = transactionDate;
        this.transactionAmount = transactionAmount;
        this.transactionNum = transactionNum;
    }

    /**
     * 交易号
     */
    private String transactionNum;

    /**
     * 图标
     */
    private String imageIcon;

    /**
     * 标题
     */
    private String title;

    /**
     * 交易日期
     */
    private String transactionDate;

    /**
     * 交易金额（包含-/+）
     */
    private String transactionAmount;

    public String getTransactionNum() {
        return transactionNum;
    }

    public void setTransactionNum(String transactionNum) {
        this.transactionNum = transactionNum;
    }

    public String getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(String imageIcon) {
        this.imageIcon = imageIcon;
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
