package com.zhongmubao.api.dto.response.sheep.room.list;

import java.util.List;

/**
 * 我的羊圈 订单弹框
 *
 * @author xy
 */
public class SheepRoomOrdersModel {
    public SheepRoomOrdersModel() {
    }

    public List<SheepRoomOrdersItemModel> getList() {
        return list;
    }

    public void setList(List<SheepRoomOrdersItemModel> list) {
        this.list = list;
    }

    private List<SheepRoomOrdersItemModel> list;
}
