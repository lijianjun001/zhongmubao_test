package com.zhongmubao.api.dto.response.Sheep;

/**
 * 牧场详情视图对象
 */
public class SheepVendorViewModel {
    public SheepVendorViewModel(){

    }

    public SheepVendorViewModel(int id, String title, double price, int period, String incomeConversionMethod, String produceIntroduction, String payMethod, String effectiveTime, String slaughterTime, String redemTime, String name, String licenseNo, String enterpriseAddress, String businessScope, String pastureIntroduction, String pics) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.period = period;
        this.incomeConversionMethod = incomeConversionMethod;
        this.produceIntroduction = produceIntroduction;
        this.payMethod = payMethod;
        this.effectiveTime = effectiveTime;
        this.slaughterTime = slaughterTime;
        this.redemTime = redemTime;
        this.name = name;
        this.licenseNo = licenseNo;
        this.enterpriseAddress = enterpriseAddress;
        this.businessScope = businessScope;
        this.pastureIntroduction = pastureIntroduction;
        this.pics = pics;
    }

    private int id;
    private String title;
    private double price;
    private int period;
    /**
     * 收益兑换方式
     */
    private String incomeConversionMethod;
    /**
     * 产品简介
     */
    private String produceIntroduction;
    /**
     * 支付方式
     */
    private String payMethod;
    /**
     *  入栏时间
     */
    private String effectiveTime;
    /**
     * 出栏时间
     */
    private String slaughterTime;
    /**
     *  赎回时间
     */
    private String redemTime;
    private String name;
    /**
     * 执照号
     */
    private String licenseNo;
    /**
     * 企业地址
     */
    private String enterpriseAddress;
    /**
     * 营业范围
     */
    private String businessScope;
    /**
     * 牧场简介
     */
    private String pastureIntroduction;
    /**
     * 牧场照片html
     */
    private String pics;

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPics() {
        return pics;
    }

    public void setPics(String pics) {
        this.pics = pics;
    }

    public String getIncomeConversionMethod() {
        return incomeConversionMethod;
    }

    public void setIncomeConversionMethod(String incomeConversionMethod) {
        this.incomeConversionMethod = incomeConversionMethod;
    }

    public String getProduceIntroduction() {
        return produceIntroduction;
    }

    public void setProduceIntroduction(String produceIntroduction) {
        this.produceIntroduction = produceIntroduction;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getSlaughterTime() {
        return slaughterTime;
    }

    public void setSlaughterTime(String slaughterTime) {
        this.slaughterTime = slaughterTime;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public String getEnterpriseAddress() {
        return enterpriseAddress;
    }

    public void setEnterpriseAddress(String enterpriseAddress) {
        this.enterpriseAddress = enterpriseAddress;
    }

    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }

    public String getPastureIntroduction() {
        return pastureIntroduction;
    }

    public void setPastureIntroduction(String pastureIntroduction) {
        this.pastureIntroduction = pastureIntroduction;
    }
}

