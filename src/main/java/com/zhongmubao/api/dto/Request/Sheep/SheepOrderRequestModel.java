package com.zhongmubao.api.dto.Request.Sheep;

public class SheepOrderRequestModel {

    // 2017-09-22 没有定义无参构造函数，导致没有命中Controller
    // 定义了有参构造函数，又没有用到无参构造函数，可事实就是没命中
    // 这个故事告诉我们：编程规范该有多么重要！！！
    public SheepOrderRequestModel() {
    }

    public SheepOrderRequestModel(int customerId, int pageIndex, String state) {
        this.customerId = customerId;
        this.pageIndex = pageIndex;
        this.state = state;
    }

    private int customerId;
    private int pageIndex;
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
