package com.zhongmubao.api.dto.response.my.transaction;

import com.google.gson.annotations.SerializedName;

/**
 * 赎回红包补贴详情
 * @author 米立林
 */
public class RedeemRedDetailModel {
    public RedeemRedDetailModel() {
    }

    public RedeemRedDetailModel(String transactionAmount, String discription, String operationDate) {
        this.transactionAmount = transactionAmount;
        this.discription = discription;
        this.operationDate = operationDate;
    }

    /**
     * 补贴金额
     */
    @SerializedName("TransactionAmount")
    private String transactionAmount;

    /**
     * 说明
     */
    @SerializedName("Discription")
    private String discription;

    /**
     * 操作日期
     */
    @SerializedName("OperationDate")
    private String operationDate;

    public String getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(String operationDate) {
        this.operationDate = operationDate;
    }
}
