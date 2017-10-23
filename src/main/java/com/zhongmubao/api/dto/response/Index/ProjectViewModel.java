package com.zhongmubao.api.dto.response.Index;

import java.math.BigDecimal;

public class ProjectViewModel {
    public ProjectViewModel(){}
    public ProjectViewModel(int id, String title, BigDecimal price, String rate, String rateImg, String effectiveTime, String beginTime, int period, String state, long countdown, String type, String shorthand, String daoJiShiXianShiLeiXing, String daoJiShiStr,int purchaseCount) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.rate = rate;
        this.rateImg = rateImg;
        this.effectiveTime = effectiveTime;
        this.beginTime = beginTime;
        this.period = period;
        this.state = state;
        this.countdown = countdown;
        this.type = type;
        this.shorthand = shorthand;
        this.daoJiShiXianShiLeiXing = daoJiShiXianShiLeiXing;
        this.daoJiShiStr = daoJiShiStr;
        this.purchaseCount = purchaseCount;
    }


    private int id;
    private String title;
    private BigDecimal price;
    private String rate;
    private String rateImg;
    private String effectiveTime;
    private String beginTime;
    private int period;
    private String state;
    private long countdown;
    private String type;
    private String shorthand;
    private String daoJiShiXianShiLeiXing;
    private String daoJiShiStr;

    private int purchaseCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getRateImg() {
        return rateImg;
    }

    public void setRateImg(String rateImg) {
        this.rateImg = rateImg;
    }

    public String getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(String effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getCountdown() {
        return countdown;
    }

    public void setCountdown(long countdown) {
        this.countdown = countdown;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getShorthand() {
        return shorthand;
    }

    public void setShorthand(String shorthand) {
        this.shorthand = shorthand;
    }

    public String getDaoJiShiXianShiLeiXing() {
        return daoJiShiXianShiLeiXing;
    }

    public void setDaoJiShiXianShiLeiXing(String daoJiShiXianShiLeiXing) {
        this.daoJiShiXianShiLeiXing = daoJiShiXianShiLeiXing;
    }

    public String getDaoJiShiStr() {
        return daoJiShiStr;
    }

    public void setDaoJiShiStr(String daoJiShiStr) {
        this.daoJiShiStr = daoJiShiStr;
    }

    public int getPurchaseCount() {
        return purchaseCount;
    }

    public void setPurchaseCount(int purchaseCount) {
        this.purchaseCount = purchaseCount;
    }
}
