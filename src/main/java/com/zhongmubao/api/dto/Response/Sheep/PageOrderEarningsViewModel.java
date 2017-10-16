package com.zhongmubao.api.dto.Response.Sheep;

public class PageOrderEarningsViewModel {

    private int id;
    private int customerId;
    /**
     * 标期名字
     */
    private String title;
    /**
     * 羊只数量
     */
    private int count;
    /**
     * 实付金额（本金）
     */
    private String paymentAmount;
    /**
     * 肉串抵扣金额
     */
    private String deductibleAmount;
    /**
     * 红包增收
     */
    private String redPackageAmount;
    /**
     * 收益金额
     */
    private String earningsAmount;
    /**
     * 入栏时间
     */
    private String effectiveTime;
    /**
     * 出栏时间
     */
    private String outFenceTime;

    public PageOrderEarningsViewModel() {
    }

    public PageOrderEarningsViewModel(int id, int customerId, String title, int count, String paymentAmount, String deductibleAmount, String redPackageAmount, String earningsAmount, String effectiveTime, String outFenceTime) {
        this.id = id;
        this.customerId = customerId;
        this.title = title;
        this.count = count;
        this.paymentAmount = paymentAmount;
        this.deductibleAmount = deductibleAmount;
        this.redPackageAmount = redPackageAmount;
        this.earningsAmount = earningsAmount;
        this.effectiveTime = effectiveTime;
        this.outFenceTime = outFenceTime;
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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

    public String getRedPackageAmount() {
        return redPackageAmount;
    }

    public void setRedPackageAmount(String redPackageAmount) {
        this.redPackageAmount = redPackageAmount;
    }

    public String getEarningsAmount() {
        return earningsAmount;
    }

    public void setEarningsAmount(String earningsAmount) {
        this.earningsAmount = earningsAmount;
    }

    public String getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(String effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public String getOutFenceTime() {
        return outFenceTime;
    }

    public void setOutFenceTime(String outFenceTime) {
        this.outFenceTime = outFenceTime;
    }
}
