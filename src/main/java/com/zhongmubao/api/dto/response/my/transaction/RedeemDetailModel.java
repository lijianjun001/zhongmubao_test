package com.zhongmubao.api.dto.response.my.transaction;


import com.google.gson.annotations.SerializedName;


/**
 * 赎回详情
 *
 * @author 米立林
 */
public class RedeemDetailModel {
    /**
     * 交易金额
     */
    @SerializedName("TransactionAmount")
    private String transactionAmount;

    /**
     * 赎回说明
     */
    @SerializedName("RedeemDiscription")
    private String redeemDiscription;

    /**
     * 羊只说明（羊标标题）
     */
    @SerializedName("SheepProjectTitle")
    private String sheepProjectTitle;

    /**
     * 赎回数量
     */
    @SerializedName("SheepCount")
    private String sheepCount;

    /**
     * 赎回方式
     */
    @SerializedName("RedeemMethod")
    private String redeemMethod;

    /**
     * 操作时间
     */
    @SerializedName("OperationDate")
    private String operationDate;

    /**
     * 订单号
     */
    @SerializedName("OrderNo")
    private String orderNo;

    /**
     * 账户余额
     */
    @SerializedName("Balance")
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

    public String getSheepCount() {
        return sheepCount;
    }

    public void setSheepCount(String sheepCount) {
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
