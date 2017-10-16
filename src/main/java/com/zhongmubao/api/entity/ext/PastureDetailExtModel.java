package com.zhongmubao.api.entity.ext;

import java.util.Date;

/**
 * 牧场详情扩展Model
 *
 * @author 米立林
 */
public class PastureDetailExtModel {

    private int id;
    private String title;
    private double price;
    private int period;
    /**
     * 入栏时间
     */
    private Date effectiveTime;
    /**
     * 赎回时间
     */
    private Date redemTime;
    private String name;
    private String attrs;
    private String pics;

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttrs() {
        return attrs;
    }

    public void setAttrs(String attrs) {
        this.attrs = attrs;
    }

    public String getPics() {
        return pics;
    }

    public void setPics(String pics) {
        this.pics = pics;
    }
}
