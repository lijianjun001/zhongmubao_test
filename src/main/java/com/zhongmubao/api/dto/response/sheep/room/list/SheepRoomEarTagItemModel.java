package com.zhongmubao.api.dto.response.sheep.room.list;

/**
 * 我的羊圈 耳标
 *
 * @author xy
 */
public class SheepRoomEarTagItemModel {
    public SheepRoomEarTagItemModel() {
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    private String orderCode;
    private String begin;
    private String end;
    private String photo;
}
