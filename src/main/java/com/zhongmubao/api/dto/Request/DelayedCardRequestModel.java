package com.zhongmubao.api.dto.Request;

public class DelayedCardRequestModel {
    public DelayedCardRequestModel() {
    }

    public DelayedCardRequestModel(int packageId) {
        this.packageId = packageId;
    }

    private int packageId;

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }
}
