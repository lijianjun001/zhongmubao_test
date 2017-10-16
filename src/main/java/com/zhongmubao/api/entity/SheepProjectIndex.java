package com.zhongmubao.api.entity;

public class SheepProjectIndex extends  SheepProject {

    private int noPayCount;
    private int paidCount;

    public int getPaidCount() {
        return paidCount;
    }

    public void setPaidCount(int paidCount) {
        this.paidCount = paidCount;
    }

    public int getNoPayCount() {
        return noPayCount;
    }

    public void setNoPayCount(int noPayCount) {
        this.noPayCount = noPayCount;
    }

}
