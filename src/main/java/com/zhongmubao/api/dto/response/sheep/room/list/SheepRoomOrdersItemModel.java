package com.zhongmubao.api.dto.response.sheep.room.list;

/**
 * 我的羊圈 订单弹框
 *
 * @author xy
 */
public class SheepRoomOrdersItemModel {
    public SheepRoomOrdersItemModel(){}

    public SheepRoomOrdersItemModel(String orderCode, int count) {
        this.orderCode = orderCode;
        this.count = count;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private String orderCode;
    private int count;
}
