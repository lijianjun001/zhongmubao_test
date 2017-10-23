package com.zhongmubao.api.dto.response.Sheep;

/**
 * 我的羊圈牧场主养羊列表
 */
public class SheepOrderInfoViewModel {
    private String title;
    /**
     * 入栏时间
     */
    private String effectiveTime;
    /**
     * 可赎回时间
     */
    private String redeemTime;
    /**
     * 数量
     */
    private int count;
    /**
     * 可赎回剩余天数
     */
    private int redeemDays;
    /**
     * 羊只类型名称
     */
    private String shorthand;

    /**
     * 当前状态
     */
    private String dayType;

    /**
     * 当前状态图片
     */
    private int dayTypeInt;

    public SheepOrderInfoViewModel(String title, String effectiveTime, String redeemTime, int count, int redeemDays, String shorthand, String dayType, int dayTypeInt) {
        this.title = title;
        this.effectiveTime = effectiveTime;
        this.redeemTime = redeemTime;
        this.count = count;
        this.redeemDays = redeemDays;
        this.shorthand = shorthand;
        this.dayType = dayType;
        this.dayTypeInt = dayTypeInt;
    }

    public SheepOrderInfoViewModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(String effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public String getRedeemTime() {
        return redeemTime;
    }

    public void setRedeemTime(String redeemTime) {
        this.redeemTime = redeemTime;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getRedeemDays() {
        return redeemDays;
    }

    public void setRedeemDays(int redeemDays) {
        this.redeemDays = redeemDays;
    }

    public String getShorthand() {
        return shorthand;
    }

    public void setShorthand(String shorthand) {
        this.shorthand = shorthand;
    }

    public String getDayType() {
        return dayType;
    }

    public void setDayType(String dayType) {
        this.dayType = dayType;
    }

    public int getDayTypeInt() {
        return dayTypeInt;
    }

    public void setDayTypeInt(int dayTypeInt) {
        this.dayTypeInt = dayTypeInt;
    }
}
