package com.zhongmubao.api.entity;

import java.util.Date;

/**
 * 红包实体
 *
 * @author 孙阿龙
 */
public class ExtRedPackage {

    public ExtRedPackage() {

    }

    private int id;

    public ExtRedPackage(int customerId, String type, int sheepOrderId, boolean isLock, double price, boolean isUsed, Date usedTime, int sheepDay, Date expTime, Date modified, Date created, int minSheepCount, int isNew) {
        this.customerId = customerId;
        this.type = type;
        this.sheepOrderId = sheepOrderId;
        this.isLock = isLock;
        this.price = price;
        this.isUsed = isUsed;
        this.usedTime = usedTime;
        this.sheepDay = sheepDay;
        this.expTime = expTime;
        this.modified = modified;
        this.created = created;
        this.minSheepCount = minSheepCount;
        this.isNew = isNew;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int customerId;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private int sheepOrderId;

    public int getSheepOrderId() {
        return sheepOrderId;
    }

    public void setSheepOrderId(int sheepOrderId) {
        this.sheepOrderId = sheepOrderId;
    }

    private boolean isLock;

    public boolean getIsLock() {
        return isLock;
    }

    public void setIsLock(boolean isLock) {
        this.isLock = isLock;
    }

    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private boolean isUsed;

    public boolean getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }

    private Date usedTime;

    public Date getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(Date usedTime) {
        this.usedTime = usedTime;
    }

    private int sheepDay;

    public int getSheepDay() {
        return sheepDay;
    }

    public void setSheepDay(int sheepDay) {
        this.sheepDay = sheepDay;
    }

    private Date expTime;

    public Date getExpTime() {
        return expTime;
    }

    public void setExpTime(Date expTime) {
        this.expTime = expTime;
    }

    private Date modified;

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    private Date created;

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    private int minSheepCount;

    public int getMinSheepCount() {
        return minSheepCount;
    }

    public void setMinSheepCount(int minSheepCount) {
        this.minSheepCount = minSheepCount;
    }

    /**
     * 是否为新红包
     */
    private int isNew;

    public boolean isLock() {
        return isLock;
    }

    public void setLock(boolean lock) {
        isLock = lock;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public int getIsNew() {
        return isNew;
    }

    public void setIsNew(int isNew) {
        this.isNew = isNew;
    }
}
