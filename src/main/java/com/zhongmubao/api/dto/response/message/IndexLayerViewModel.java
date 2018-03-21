package com.zhongmubao.api.dto.response.message;

/**
 * 首页弹层消息模型
 *
 * @author 孙阿龙
 */
public class IndexLayerViewModel {

    private CustomerMessageModel customerMessageModel;

    public CustomerMessageModel getCustomerMessageModel() {
        return customerMessageModel;
    }

    public void setCustomerMessageModel(CustomerMessageModel customerMessageModel) {
        this.customerMessageModel = customerMessageModel;
    }
}
