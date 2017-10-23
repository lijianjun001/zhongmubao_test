package com.zhongmubao.api.dto.request.customer;

public class AutoRedeemRequestModel {

    private int isAutoRedeem;
    private String redeemPassword;

    public String getRedeemPassword() {
        return redeemPassword;
    }

    public void setRedeemPassword(String redeemPassword) {
        this.redeemPassword = redeemPassword;
    }

    public int getIsAutoRedeem() {
        return isAutoRedeem;
    }

    public void setIsAutoRedeem(int isAutoRedeem) {
        this.isAutoRedeem = isAutoRedeem;
    }
}
