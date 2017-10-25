package com.zhongmubao.api.dto.response.sheep.room;

import com.zhongmubao.api.dto.response.sheep.room.list.SheepRoomOrdersModel;

/**
 * 我的羊圈 订单弹框
 *
 * @author xy
 */
public class SheepRoomOrdersViewModel {

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public SheepRoomOrdersModel getSheepRoomOrdersModel() {
        return sheepRoomOrdersModel;
    }

    public void setSheepRoomOrdersModel(SheepRoomOrdersModel sheepRoomOrdersModel) {
        this.sheepRoomOrdersModel = sheepRoomOrdersModel;
    }

    private String title;
    private int totalCount;
    private SheepRoomOrdersModel sheepRoomOrdersModel;
}
