package com.zhongmubao.api.entity.ext;

import java.util.Date;

/**
 * 我的羊圈 列表
 * @author xy
 */
public class MySheepRoomSheepOrderAndProject {
    public MySheepRoomSheepOrderAndProject(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getOrderSheepCount() {
        return orderSheepCount;
    }

    public void setOrderSheepCount(int orderSheepCount) {
        this.orderSheepCount = orderSheepCount;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public Date getRedemTime() {
        return redemTime;
    }

    public void setRedemTime(Date redemTime) {
        this.redemTime = redemTime;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    private int id;
    private int venderId;
    private String title;
    private String type;
    private int orderSheepCount;
    private Date beginTime;
    private Date effectiveTime;
    private Date redemTime;
    private String orderState;
    private int period;
}
