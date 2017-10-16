package com.zhongmubao.api.entity.ext;

import java.util.Date;

public class SheepOrderInfo {

    private int id;
    private int customerId;
    private String code;
    private int count;
    private double totalAmount;
    private String paymentAmount;
    private String deductibleAmount;
    private String title;
    private String name;
    private Date effectiveTime;
    private Date redemTime;
    private String photo;
    private String shorthand;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getDeductibleAmount() {
        return deductibleAmount;
    }

    public void setDeductibleAmount(String deductibleAmount) {
        this.deductibleAmount = deductibleAmount;
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

    public String getShorthand() {
        return shorthand;
    }

    public void setShorthand(String shorthand) {
        this.shorthand = shorthand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
