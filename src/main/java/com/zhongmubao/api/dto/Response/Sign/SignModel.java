package com.zhongmubao.api.dto.Response.Sign;

public class SignModel {
    public SignModel() {
    }

    public SignModel(int shareDayCount, String redPackagePrice, String info, SignGiftViewModel signGift, boolean todayIsShare, boolean showPop) {
        this.redPackagePrice = redPackagePrice;
        this.info = info;
        this.signGift = signGift;
        this.shareDayCount = shareDayCount;
        this.todayIsShare = todayIsShare;
        this.showPop = showPop;
    }

    private String redPackagePrice;
    private String info;
    private SignGiftViewModel signGift;
    private int shareDayCount;
    private boolean todayIsShare;

    public boolean isShowPop() {
        return showPop;
    }

    public void setShowPop(boolean showPop) {
        this.showPop = showPop;
    }

    private boolean showPop;

    public int getShareDayCount() {
        return shareDayCount;
    }

    public void setShareDayCount(int shareDayCount) {
        this.shareDayCount = shareDayCount;
    }

    public String getRedPackagePrice() {
        return redPackagePrice;
    }

    public void setRedPackagePrice(String redPackagePrice) {
        this.redPackagePrice = redPackagePrice;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public SignGiftViewModel getSignGift() {
        return signGift;
    }

    public void setSignGift(SignGiftViewModel signGift) {
        this.signGift = signGift;
    }

    public boolean isTodayIsShare() {
        return todayIsShare;
    }

    public void setTodayIsShare(boolean todayIsShare) {
        this.todayIsShare = todayIsShare;
    }
}
