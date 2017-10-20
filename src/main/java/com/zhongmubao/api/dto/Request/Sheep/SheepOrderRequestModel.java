package com.zhongmubao.api.dto.Request.Sheep;

/**
 * 买羊订单
 * @author 米立林
 */
public class SheepOrderRequestModel {

    public SheepOrderRequestModel() {
    }

    public SheepOrderRequestModel(int customerId, int pageIndex, String state) {
        this.customerId = customerId;
        this.pageIndex = pageIndex;
        this.state = state;
    }

    private int customerId;
    private int pageIndex;
    /**
     * 订单状态
     * 00 未付款 01已付款 02已入栏 03可赎回 04赎回中 05已赎回 06已取消
     */
    private String state;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
