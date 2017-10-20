package com.zhongmubao.api.entity.ext;

import java.util.Date;

public class MySheepFoldRedeemedItem {

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public int getOrderSheepCount() {
        return orderSheepCount;
    }

    public void setOrderSheepCount(int orderSheepCount) {
        this.orderSheepCount = orderSheepCount;
    }

    public double getDeductibleAmount() {
        return deductibleAmount;
    }

    public void setDeductibleAmount(double deductibleAmount) {
        this.deductibleAmount = deductibleAmount;
    }

    public double getRedemAmount() {
        return redemAmount;
    }

    public void setRedemAmount(double redemAmount) {
        this.redemAmount = redemAmount;
    }

    public double getRedPrice() {
        return redPrice;
    }

    public void setRedPrice(double redPrice) {
        this.redPrice = redPrice;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public Date getRedemTime() {
        return redemTime;
    }

    public void setRedemTime(Date redemTime) {
        this.redemTime = redemTime;
    }

    public String orderCode;
    public int orderId;
    public String orderState;
    public int orderSheepCount;
    public double deductibleAmount;
    public double redemAmount;
    public double redPrice;
    public double paymentAmount;
    public int id;
    public int vendorId;
    public String type;
    public String title;
    public double price;
    public double rate;
    public int period;
    public Date beginTime;
    public Date endTime;
    public Date effectiveTime;
    public Date redemTime;


}
