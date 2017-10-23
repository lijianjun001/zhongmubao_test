package com.zhongmubao.api.dto.request.sheep;

/**
 * 买羊订单
 * @author 米立林
 */
public class SheepOrderRequestModel {

    public SheepOrderRequestModel() {
    }

    public SheepOrderRequestModel(int pageIndex, String state) {
        this.pageIndex = pageIndex;
        this.state = state;
    }

    private int pageIndex;
    /**
     * 订单状态
     * 00 未付款 01已付款 02已入栏 03可赎回 04赎回中 05已赎回 06已取消
     */
    private String state;

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
