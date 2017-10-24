package com.zhongmubao.api.dto.response.sheep;

public class MySheepFoldRedeemedViewModel {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public int getVenderId() {
        return venderId;
    }

    public void setVenderId(int venderId) {
        this.venderId = venderId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(String effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public String getRedemTime() {
        return redemTime;
    }

    public void setRedemTime(String redemTime) {
        this.redemTime = redemTime;
    }

    public String getDeductibleAmount() {
        return deductibleAmount;
    }

    public void setDeductibleAmount(String deductibleAmount) {
        this.deductibleAmount = deductibleAmount;
    }

    public String getRedemAmount() {
        return redemAmount;
    }

    public void setRedemAmount(String redemAmount) {
        this.redemAmount = redemAmount;
    }

    public String getRedPrice() {
        return redPrice;
    }

    public void setRedPrice(String redPrice) {
        this.redPrice = redPrice;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    private int id;
    private String orderCode;
    private int venderId;
    private String title;
    private String type;
    private int count;
    private String beginTime;
    private String effectiveTime;
    private String redemTime;
    private String deductibleAmount;
    private String redemAmount;
    private String redPrice;
    private String paymentAmount;
}

