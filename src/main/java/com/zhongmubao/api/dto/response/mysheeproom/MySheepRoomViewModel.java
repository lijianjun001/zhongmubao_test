package com.zhongmubao.api.dto.response.mysheeproom;

import com.zhongmubao.api.dto.response.mysheeproom.mysheeproomlist.MySheepRoomListModel;

public class MySheepRoomViewModel {
    public MySheepRoomViewModel(){}


    public boolean isNewOrders() {
        return isNewOrders;
    }

    public void setNewOrders(boolean newOrders) {
        isNewOrders = newOrders;
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

    public MySheepRoomListModel getListing() {
        return listing;
    }

    public void setListing(MySheepRoomListModel listing) {
        this.listing = listing;
    }

    private boolean isNewOrders;
    private int customerLevel;
    private int totalSheepCount;
    private MySheepRoomListModel listing;
}
