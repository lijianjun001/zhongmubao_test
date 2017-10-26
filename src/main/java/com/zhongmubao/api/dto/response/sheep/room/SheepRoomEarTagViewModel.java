package com.zhongmubao.api.dto.response.sheep.room;

import com.zhongmubao.api.dto.response.sheep.room.list.SheepRoomEarTagItemModel;

import java.util.List;

/**
 * 我的羊圈 耳标弹框
 *
 * @author xy
 */
public class SheepRoomEarTagViewModel {
    public SheepRoomEarTagViewModel() {
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public List<SheepRoomEarTagItemModel> getList() {
        return list;
    }

    public void setList(List<SheepRoomEarTagItemModel> list) {
        this.list = list;
    }

    private String videoUrl;
    private List<SheepRoomEarTagItemModel> list;
}
