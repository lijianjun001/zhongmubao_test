package com.zhongmubao.api.dto.response.mysheeproom.mysheeproomlist;

public class MySheepRoomItemModel {
    public  MySheepRoomItemModel(){}

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getVenderId() {
        return venderId;
    }

    public void setVenderId(int venderId) {
        this.venderId = venderId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(String effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public String getRedemTime() {
        return redemTime;
    }

    public void setRedemTime(String redemTime) {
        this.redemTime = redemTime;
    }

    public boolean isShowBottom() {
        return showBottom;
    }

    public void setShowBottom(boolean showBottom) {
        this.showBottom = showBottom;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDayType() {
        return dayType;
    }

    public void setDayType(String dayType) {
        this.dayType = dayType;
    }

    public int getDayTypeInt() {
        return dayTypeInt;
    }

    public void setDayTypeInt(int dayTypeInt) {
        this.dayTypeInt = dayTypeInt;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public int getLeftDays() {
        return leftDays;
    }

    public void setLeftDays(int leftDays) {
        this.leftDays = leftDays;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    private int projectId;
    private int venderId;
    private String title;
    private String type;
    private int count;
    private String beginTime;
    private String effectiveTime;
    private String redemTime;
    private boolean showBottom;
    private String state;
    private String dayType;
    private int dayTypeInt;
    private String vendor;
    private int leftDays;
    public int period;
}
