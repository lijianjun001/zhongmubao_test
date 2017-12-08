package com.zhongmubao.api.entity.ext;

import java.util.Date;

/**
 * 红包分组
 * @author 米立林
 */
public class ExtRedPackageGroup {

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getNewCount() {
        return newCount;
    }

    public void setNewCount(int newCount) {
        this.newCount = newCount;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getExpTime() {
        return expTime;
    }

    public void setExpTime(Date expTime) {
        this.expTime = expTime;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    private double price;
    private int totalCount;
    private int newCount;
    private int totalPrice;
    private Date expTime;
    private String Type;
}
