package com.zhongmubao.api.dto.response.sheep.room;

import com.zhongmubao.api.dto.response.sheep.room.list.SheepRoomBreedProgressItemModel;

import java.util.List;

/**
 * 我的羊圈 养殖进度
 *
 * @author xy
 */
public class SheepRoomBreedProgressViewModel {
    public SheepRoomBreedProgressViewModel() {
    }

    public List<SheepRoomBreedProgressItemModel> getList() {
        return list;
    }

    public void setList(List<SheepRoomBreedProgressItemModel> list) {
        this.list = list;
    }

    public List<SheepRoomBreedProgressItemModel> list;

}
