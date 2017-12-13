package com.zhongmubao.api.dto.response.my.transaction;

/**
 * 购羊详情
 * @author 米立林
 */
public class BuySheepDetailModel {
    public BuySheepDetailModel() {
    }

    public BuySheepDetailModel(String pastureImage, String pastureName, String transactionAmount, String transactionMethod, String sheepProjectTitle, String sheepCount, String buySheepDate, String orderNo, String balance) {
        this.pastureImage = pastureImage;
        this.pastureName = pastureName;
        this.transactionAmount = transactionAmount;
        this.transactionMethod = transactionMethod;
        this.sheepProjectTitle = sheepProjectTitle;
        this.sheepCount = sheepCount;
        this.buySheepDate = buySheepDate;
        this.orderNo = orderNo;
        this.balance = balance;
    }

    /**
     * 牧场图标
     */
    private String pastureImage;

    /**
     * 牧场名字
     */
    private String pastureName;

    /**
     * 交易金额
     */
    private String transactionAmount;

    /**
     * 付款方式
     */
    private String transactionMethod;

    /**
     * 羊只说明（羊标标题）
     */
    private String sheepProjectTitle;

    /**
     * 购羊数量
     */
    private String sheepCount;

    /**
     * 购羊时间
     */
    private String buySheepDate;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 账户余额
     */
    private String balance;

    public String getPastureImage() {
        return pastureImage;
    }

    public void setPastureImage(String pastureImage) {
        this.pastureImage = pastureImage;
    }

    public String getPastureName() {
        return pastureName;
    }

    public void setPastureName(String pastureName) {
        this.pastureName = pastureName;
    }

    public String getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionMethod() {
        return transactionMethod;
    }

    public void setTransactionMethod(String transactionMethod) {
        this.transactionMethod = transactionMethod;
    }

    public String getSheepProjectTitle() {
        return sheepProjectTitle;
    }

    public void setSheepProjectTitle(String sheepProjectTitle) {
        this.sheepProjectTitle = sheepProjectTitle;
    }

    public String getSheepCount() {
        return sheepCount;
    }

    public void setSheepCount(String sheepCount) {
        this.sheepCount = sheepCount;
    }

    public String getBuySheepDate() {
        return buySheepDate;
    }

    public void setBuySheepDate(String buySheepDate) {
        this.buySheepDate = buySheepDate;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
