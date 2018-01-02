package com.zhongmubao.api.dto.response.sign;

/**
 * 今日是否分享
 * @author xy
 */
public class SignTodayIsShareModel {

    public SignTodayIsShareModel() {
    }

    public boolean isTodayIsShare() {
        return todayIsShare;
    }

    public void setTodayIsShare(boolean todayIsShare) {
        this.todayIsShare = todayIsShare;
    }

    private boolean todayIsShare;

}
