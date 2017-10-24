package com.zhongmubao.api.dto.response.sheep;

import java.util.List;

public class MySheepRoomListViewModel {

    public MySheepRoomHeadViewModel getHead() {
        return head;
    }

    public void setHead(MySheepRoomHeadViewModel head) {
        this.head = head;
    }

    private MySheepRoomHeadViewModel head;

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    private int totalPage;

    public List<MySheepFoldViewModel> getList() {
        return list;
    }

    public void setList(List<MySheepFoldViewModel> list) {
        this.list = list;
    }

    private List<MySheepFoldViewModel> list;

    public int getSheepCount() {
        return sheepCount;
    }

    public void setSheepCount(int sheepCount) {
        this.sheepCount = sheepCount;
    }

    private int sheepCount;

    public int getShopCount() {
        return shopCount;
    }

    public void setShopCount(int shopCount) {
        this.shopCount = shopCount;
    }

    private int shopCount;

}
