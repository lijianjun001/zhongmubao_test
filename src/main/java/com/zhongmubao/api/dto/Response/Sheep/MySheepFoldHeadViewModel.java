package com.zhongmubao.api.dto.Response.Sheep;

public class MySheepFoldHeadViewModel {
    public int getSheepTotalCount() {
        return sheepTotalCount;
    }

    public void setSheepTotalCount(int sheepTotalCount) {
        this.sheepTotalCount = sheepTotalCount;
    }

    private int sheepTotalCount;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    private int level;

    public boolean isNewOrders() {
        return isNewOrders;
    }

    public void setNewOrders(boolean newOrders) {
        isNewOrders = newOrders;
    }

    private boolean isNewOrders;
}
