package com.zhongmubao.api.dto.response.sheep.room.list;

import java.util.List;

/**
 * 我的羊圈 养殖流程
 *
 * @author xy
 */
public class SheepRoomBreedProgressItemModel {
    public SheepRoomBreedProgressItemModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    private int id;
    /**
     * 选中 状态 1 选中 2 进行中 3 未进行
     */
    private int progress;
    private String text;
    private String img;

}
