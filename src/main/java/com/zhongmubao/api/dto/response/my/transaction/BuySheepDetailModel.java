package com.zhongmubao.api.dto.response.my.transaction;

import com.google.gson.annotations.SerializedName;


/**
 * 购羊详情
 *
 * @author 米立林
 */
public class BuySheepDetailModel {
    public BuySheepDetailModel() {
    }

    public BuySheepDetailModel(String pastureImage, String pastureName, String transactionAmount, String transactionMethod, String sheepProjectTitle, String sheepCount, String buySheepDate, String orderNo) {
        this.pastureImage = pastureImage;
        this.pastureName = pastureName;
        this.transactionAmount = transactionAmount;
        this.transactionMethod = transactionMethod;
        this.sheepProjectTitle = sheepProjectTitle;
        this.sheepCount = sheepCount;
        this.buySheepDate = buySheepDate;
        this.orderNo = orderNo;
    }

    /**
     * 牧场图标
     */
    @SerializedName("PastureImage")
    private String pastureImage;

    /**
     * 牧场名字
     */
    @SerializedName("PastureName")
    private String pastureName;

    /**
     * 交易金额
     */
    @SerializedName("TransactionAmount")
    private String transactionAmount;

    /**
     * 付款方式
     */
    @SerializedName("TransactionMethod")
    private String transactionMethod;

    /**
     * 羊只说明（羊标标题）
     */
    @SerializedName("SheepProjectTitle")
    private String sheepProjectTitle;

    /**
     * 购羊数量
     */
    @SerializedName("SheepCount")
    private String sheepCount;

    /**
     * 购羊时间
     */
    @SerializedName("BuySheepDate")
    private String buySheepDate;

    /**
     * 订单号
     */
    @SerializedName("OrderNo")
    private String orderNo;

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
}
