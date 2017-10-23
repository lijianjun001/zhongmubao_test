package com.zhongmubao.api.dto.response.sign;

public class MyGiftCardModel {
    public MyGiftCardModel() {
    }

    public MyGiftCardModel(int delayedCardCount, int mergeCardCount) {
        this.delayedCardCount = delayedCardCount;
        this.mergeCardCount = mergeCardCount;
    }

    private int delayedCardCount;
    private int mergeCardCount;

    public int getDelayedCardCount() {
        return delayedCardCount;
    }

    public void setDelayedCardCount(int delayedCardCount) {
        this.delayedCardCount = delayedCardCount;
    }

    public int getMergeCardCount() {
        return mergeCardCount;
    }

    public void setMergeCardCount(int mergeCardCount) {
        this.mergeCardCount = mergeCardCount;
    }
}
