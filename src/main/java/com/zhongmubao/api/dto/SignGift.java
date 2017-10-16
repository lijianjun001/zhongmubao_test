package com.zhongmubao.api.dto;

import com.zhongmubao.api.config.enmu.SignGiftType;

public class SignGift {
    public SignGift(int id, String title, int count, SignGiftType type) {
        this.id = id;
        this.title = title;
        this.count = count;
        this.type = type;
    }

    private int id;
    private String title;
    private int count;
    private SignGiftType type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public SignGiftType getType() {
        return type;
    }

    public void setType(SignGiftType type) {
        this.type = type;
    }

}
