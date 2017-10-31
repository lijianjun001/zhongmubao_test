package com.zhongmubao.api.dto.response.sheep.room;

import com.zhongmubao.api.dto.response.sheep.room.list.SheepRoomCustomerModel;

/**
 * 我的羊圈 赎回弹框
 *
 * @author xy
 */
public class SheepRoomRedeemableViewModel {
    public SheepRoomRedeemableViewModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getRedeemTime() {
        return redeemTime;
    }

    public void setRedeemTime(String redeemTime) {
        this.redeemTime = redeemTime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRedPrice() {
        return redPrice;
    }

    public void setRedPrice(String redPrice) {
        this.redPrice = redPrice;
    }

    public boolean isVoluntaryRedeemption() {
        return voluntaryRedeemption;
    }

    public void setVoluntaryRedeemption(boolean voluntaryRedeemption) {
        this.voluntaryRedeemption = voluntaryRedeemption;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public SheepRoomCustomerModel getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(SheepRoomCustomerModel customerInfo) {
        this.customerInfo = customerInfo;
    }

    private String title;
    private int count;
    private String redeemTime;
    private String price;
    private String redPrice;
    private boolean voluntaryRedeemption;
    private String type;
    private SheepRoomCustomerModel customerInfo;
}
