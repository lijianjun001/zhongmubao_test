package com.zhongmubao.api.dto.request.my.transaction;

/**
 * 交易详情请求参数
 *
 * @author 米立林
 */
public class TransactionDetailRequestModel {
    /**
     * 交易号
     */
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
