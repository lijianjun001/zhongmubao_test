package com.zhongmubao.api.dto.Response.Sheep;

/**
 * 羊耳标
 * @author 米立林
 */
public class PastureSheepErBiaoModel {
    private String orderCode;
    private int begin;
    private int end;
    private String ebString;
    private String photo;

    public PastureSheepErBiaoModel(String orderCode, int begin, int end, String ebString, String photo) {
        this.orderCode = orderCode;
        this.begin = begin;
        this.end = end;
        this.ebString = ebString;
        this.photo = photo;
    }

    public PastureSheepErBiaoModel() {
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public String getEbString() {
        return ebString;
    }

    public void setEbString(String ebString) {
        this.ebString = ebString;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
