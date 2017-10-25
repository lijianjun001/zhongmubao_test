package com.zhongmubao.api.dto.response.sheep.room;

import com.zhongmubao.api.dto.response.sheep.room.list.SheepRoomModel;

/**
 * 我的羊圈
 * @author xy
 * @data 2017/10/24
 */
public class SheepRoomViewModel {
    public SheepRoomViewModel() {
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

    public SheepRoomModel getSheepRoomModel() {
        return sheepRoomModel;
    }

    public void setSheepRoomModel(SheepRoomModel sheepRoomModel) {
        this.sheepRoomModel = sheepRoomModel;
    }

    private boolean hasOrder;
    private int customerLevel;
    private int totalSheepCount;
    private SheepRoomModel sheepRoomModel;
}
