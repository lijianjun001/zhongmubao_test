package com.zhongmubao.api.dto.response.mysheeproom;

import com.zhongmubao.api.dto.response.mysheeproom.mysheeproomlist.MySheepRoomModel;

/**
 * 我的羊圈
 * @author xy
 * @data 2017/10/24
 */
public class MySheepRoomViewModel {
    public MySheepRoomViewModel() {
    }


    public boolean isHasOrder() {
        return hasOrder;
    }

    public void setHasOrder(boolean hasOrder) {
        this.hasOrder = hasOrder;
    }

    public int getCustomerLevel() {
        return customerLevel;
    }

    public void setCustomerLevel(int customerLevel) {
        this.customerLevel = customerLevel;
    }

    public int getTotalSheepCount() {
        return totalSheepCount;
    }

    public void setTotalSheepCount(int totalSheepCount) {
        this.totalSheepCount = totalSheepCount;
    }

    public MySheepRoomModel getMySheepRoomModel() {
        return mySheepRoomModel;
    }

    public void setMySheepRoomModel(MySheepRoomModel mySheepRoomModel) {
        this.mySheepRoomModel = mySheepRoomModel;
    }

    private boolean hasOrder;
    private int customerLevel;
    private int totalSheepCount;
    private MySheepRoomModel mySheepRoomModel;
}
