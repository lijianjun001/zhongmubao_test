package com.zhongmubao.api.dto.Response.Sign;

public class MyGiftCardModel {
    public MyGiftCardModel() {
    }

    public MyGiftCardModel(int delayedCardCount, int megreCardCount) {
        DelayedCardCount = delayedCardCount;
        MegreCardCount = megreCardCount;
    }

    private int DelayedCardCount;
    private int MegreCardCount;

    public int getDelayedCardCount() {
        return DelayedCardCount;
    }

    public void setDelayedCardCount(int delayedCardCount) {
        DelayedCardCount = delayedCardCount;
    }

    public int getMegreCardCount() {
        return MegreCardCount;
    }

    public void setMegreCardCount(int megreCardCount) {
        MegreCardCount = megreCardCount;
    }
}
