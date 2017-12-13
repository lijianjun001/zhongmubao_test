package com.zhongmubao.api.dto.request.transaction;

/**
 * 交易详情请求参数
 *
 * @author 米立林
 */
public class TransactionDetailRequestModel {
    /**
     * 交易号
     */
    private String transactionNum;

    public String getTransactionNum() {
        return transactionNum;
    }

    public void setTransactionNum(String transactionNum) {
        this.transactionNum = transactionNum;
    }
}
