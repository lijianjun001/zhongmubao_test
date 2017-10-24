package com.zhongmubao.api.dto.response.mysheeproom.mysheeproomlist;

import java.util.List;

public class MySheepRoomListModel {
    public MySheepRoomListModel(){}


    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalSheepCount() {
        return totalSheepCount;
    }

    public void setTotalSheepCount(int totalSheepCount) {
        this.totalSheepCount = totalSheepCount;
    }

    public List<MySheepRoomItemModel> getList() {
        return list;
    }

    public void setList(List<MySheepRoomItemModel> list) {
        this.list = list;
    }

    private int totalPage;
    private int totalSheepCount;
    private List<MySheepRoomItemModel> list;
}
