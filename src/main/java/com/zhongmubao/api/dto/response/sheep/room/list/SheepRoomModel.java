package com.zhongmubao.api.dto.response.sheep.room.list;


import java.util.List;

/**
 * 我的羊圈 列表
 * @author xy
 * @date 2017/10/24
 */
public class SheepRoomModel {
    public SheepRoomModel() {
    }


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

    public List<SheepRoomItemModel> getList() {
        return list;
    }

    public void setList(List<SheepRoomItemModel> list) {
        this.list = list;
    }

    private int totalPage;
    private int totalSheepCount;
    private List<SheepRoomItemModel> list;
}
