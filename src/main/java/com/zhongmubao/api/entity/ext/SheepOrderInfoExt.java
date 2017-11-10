package com.zhongmubao.api.entity.ext;

import java.util.Date;

/**
 * 购羊订单信息扩展类
 * @author 米立林
 */
public class SheepOrderInfoExt {

    private int id;
    private int projectId;
    private int customerId;
    private String code;
    private int count;
    private double price;
    private double rate;
    private int period;
    private int vendorId;
    private double totalAmount;
    private String paymentAmount;
    private String deductibleAmount;
    private String title;
    private String name;
    private Date effectiveTime;
    private Date redeemTime;
    private String photo;
    private String shorthand;
    private String type;
    private String sheepOrderState;
    private String sheepProjectState;
    private double redPackageAmount;
    private Date beginTime;
    private Date endTime;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
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

    public Date getRedeemTime() {
        return redeemTime;
    }

    public void setRedeemTime(Date redeemTime) {
        this.redeemTime = redeemTime;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSheepOrderState() {
        return sheepOrderState;
    }

    public void setSheepOrderState(String sheepOrderState) {
        this.sheepOrderState = sheepOrderState;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
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

    public double getRedPackageAmount() {
        return redPackageAmount;
    }

    public void setRedPackageAmount(double redPackageAmount) {
        this.redPackageAmount = redPackageAmount;
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

    public String getSheepProjectState() {
        return sheepProjectState;
    }

    public void setSheepProjectState(String sheepProjectState) {
        this.sheepProjectState = sheepProjectState;
    }
}
