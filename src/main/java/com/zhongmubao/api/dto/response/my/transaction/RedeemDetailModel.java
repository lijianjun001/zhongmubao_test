package com.zhongmubao.api.dto.response.my.transaction;


/**
 * 赎回详情
 *
 * @author 米立林
 */
public class RedeemDetailModel {
    /**
     * 交易金额
     */
    private String transactionAmount;

    /**
     * 赎回说明
     */
    private String redeemDiscription;

    /**
     * 羊只说明（羊标标题）
     */
    private String sheepProjectTitle;

    /**
     * 赎回数量
     */
    private int sheepCount;

    /**
     * 赎回方式
     */
    private String redeemMethod;

    /**
     * 操作时间
     */
    private String operationDate;

    /**
     * 订单号
     */
    private String orderNo;

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

    public String getRedeemDiscription() {
        return redeemDiscription;
    }

    public void setRedeemDiscription(String redeemDiscription) {
        this.redeemDiscription = redeemDiscription;
    }

    public String getSheepProjectTitle() {
        return sheepProjectTitle;
    }

    public void setSheepProjectTitle(String sheepProjectTitle) {
        this.sheepProjectTitle = sheepProjectTitle;
    }

    public int getSheepCount() {
        return sheepCount;
    }

    public void setSheepCount(int sheepCount) {
        this.sheepCount = sheepCount;
    }

    public String getRedeemMethod() {
        return redeemMethod;
    }

    public void setRedeemMethod(String redeemMethod) {
        this.redeemMethod = redeemMethod;
    }

    public String getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(String operationDate) {
        this.operationDate = operationDate;
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
