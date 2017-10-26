package com.zhongmubao.api.dto.response.sheep.room;

import com.zhongmubao.api.dto.response.sheep.room.list.SheepRoomOrdersItemModel;
import com.zhongmubao.api.dto.response.sheep.room.list.SheepRoomOrdersModel;

import java.util.List;

/**
 * 我的羊圈 订单弹框
 *
 * @author xy
 */
public class SheepRoomOrdersViewModel {
    public SheepRoomOrdersViewModel() {
    }

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

    public List<SheepRoomOrdersItemModel> getList() {
        return list;
    }

    public void setList(List<SheepRoomOrdersItemModel> list) {
        this.list = list;
    }

    private String title;
    private int totalCount;
    private List<SheepRoomOrdersItemModel> list;
}
