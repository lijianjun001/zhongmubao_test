package com.zhongmubao.api.dto.Request;


/**
 * 用户仅需要接收CustomerId的业务方法
 */
public class OnlyCustomerIdRequestModel {

    public int getCustomerIdId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    private int customerId;
}
