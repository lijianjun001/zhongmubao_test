package com.zhongmubao.api.entity.ext;

import java.util.Date;

public class SheepOrderEarnings {

    private int id;
    private int customerId;
    private int count;
    private String paymentAmount;
    private String deductibleAmount;
    private String title;
    private Date effectiveTime;
    private String redPackageAmount;
    private Date redemTime;

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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPayableAmount() {
        return paymentAmount;
    }

    public void setPayableAmount(String payableAmount) {
        paymentAmount = payableAmount;
    }

    public String getDeductibleAmount() {
        return deductibleAmount;
    }

    public void setDeductibleAmount(String deductibleAmount) {
        this.deductibleAmount = deductibleAmount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public String getRedPackageAmount() {
        return redPackageAmount;
    }

    public void setRedPackageAmount(String redPackageAmount) {
        this.redPackageAmount = redPackageAmount;
    }

    public Date getRedemTime() {
        return redemTime;
    }

    public void setRedemTime(Date redemTime) {
        this.redemTime = redemTime;
    }
}
