package com.zhongmubao.api.dto.response.customer;

/**
 * 用户自动赎回
 * @author 米立林
 */
public class AutoRedeemModel {
    private boolean hasRedeemPassword;
    private boolean isAutoRedeem;

    public AutoRedeemModel(boolean hasRedeemPassword, boolean isAutoRedeem) {
        this.hasRedeemPassword = hasRedeemPassword;
        this.isAutoRedeem = isAutoRedeem;
    }

    public AutoRedeemModel() {
    }

    public boolean isHasRedeemPassword() {
        return hasRedeemPassword;
    }

    public void setHasRedeemPassword(boolean hasRedeemPassword) {
        this.hasRedeemPassword = hasRedeemPassword;
    }

    public boolean isAutoRedeem() {
        return isAutoRedeem;
    }

    public void setAutoRedem(boolean autoRedem) {
        isAutoRedeem = autoRedem;
    }
}
