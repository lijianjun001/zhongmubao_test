package com.zhongmubao.api.dto.response.sign;

/**
 * 分享信息
 * @author xy
 */
public class SignShareInfoModel {

    public SignShareInfoModel() {
    }

    public boolean getTodayIsShare() {
        return todayIsShare;
    }

    public void setTodayIsShare(boolean todayIsShare) {
        this.todayIsShare = todayIsShare;
    }

    private boolean todayIsShare;

    public int getMonthShareCount() {
        return monthShareCount;
    }

    public void setMonthShareCount(int monthShareCount) {
        this.monthShareCount = monthShareCount;
    }

    private int monthShareCount;
}
