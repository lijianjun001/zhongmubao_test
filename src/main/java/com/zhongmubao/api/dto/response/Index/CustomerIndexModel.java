package com.zhongmubao.api.dto.response.Index;

public class CustomerIndexModel {

    public CustomerIndexModel() {
    }

    public CustomerIndexModel(int id, int sheepCount, boolean realName, boolean redeemPwd,boolean todayIsShare) {
        this.id = id;
        this.sheepCount = sheepCount;
        this.realName = realName;
        this.redeemPwd = redeemPwd;
        this.todayIsShare = todayIsShare;
    }

    private int id;
    private int sheepCount;
    private boolean realName;
    private boolean redeemPwd;
    private boolean todayIsShare;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSheepCount() {
        return sheepCount;
    }

    public void setSheepCount(int sheepCount) {
        this.sheepCount = sheepCount;
    }

    public boolean isRealName() {
        return realName;
    }

    public void setRealName(boolean realName) {
        this.realName = realName;
    }

    public boolean isRedeemPwd() {
        return redeemPwd;
    }

    public void setRedeemPwd(boolean redeemPwd) {
        this.redeemPwd = redeemPwd;
    }

    public boolean isTodayIsShare() {
        return todayIsShare;
    }

    public void setTodayIsShare(boolean todayIsShare) {
        this.todayIsShare = todayIsShare;
    }
}
