package com.zhongmubao.api.dto.Response.Sheep;


public class SheepOrderDetailModel {

    public SheepOrderDetailModel() {
    }

    public SheepOrderDetailModel(int id, int projectId, int customerId, String code, int count, String totalAmount, String deductibleAmount, String paymentAmount, String state, String redeemAmount, String created, String paymentTime, int redeemDays, String redeemTime, String redPackageAmount, String totalIncome) {
        this.id = id;
        this.projectId = projectId;
        this.customerId = customerId;
        this.code = code;
        this.count = count;
        this.totalAmount = totalAmount;
        this.deductibleAmount = deductibleAmount;
        this.paymentAmount = paymentAmount;
        this.state = state;
        this.redeemAmount = redeemAmount;
        this.created = created;
        this.paymentTime = paymentTime;
        this.redeemDays = redeemDays;
        this.redeemTime = redeemTime;
        this.redPackageAmount = redPackageAmount;
        this.totalIncome = totalIncome;
    }

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int projectId;

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    private int customerId;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * 订单编号
     */
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 数量
     */
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    /**
     * 订单总额
     */
    private String totalAmount;

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * 减免金额（肉串抵用）
     */
    private String deductibleAmount;

    public String getDeductibleAmount() {
        return deductibleAmount;
    }

    public void setDeductibleAmount(String deductibleAmount) {
        this.deductibleAmount = deductibleAmount;
    }

    /**
     * 实付金额
     */
    private String paymentAmount;

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    /**
     * 赎回金额
     */
    private String redeemAmount;

    public String getRedeemAmount() {
        return redeemAmount;
    }

    public void setRedeemAmount(String redeemAmount) {
        this.redeemAmount = redeemAmount;
    }

    /**
     * 下单时间
     */
    private String created;

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    /**
     * 付款时间
     */
    private String paymentTime;

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    /**
     * 可赎回剩余天数
     */
    private int redeemDays;

    public int getRedeemDays() {
        return redeemDays;
    }

    public void setRedeemDays(int redeemDays) {
        redeemDays = redeemDays;
    }

    /**
     * 可赎回日期
     */
    private String redeemTime;

    public String getRedeemTime() {
        return redeemTime;
    }

    public void setRedeemTime(String redeemTime) {
        this.redeemTime = redeemTime;
    }

    /**
     * 红包收益
     */
    private String redPackageAmount;

    public String getRedPackageAmount() {
        return redPackageAmount;
    }

    public void setRedPackageAmount(String redPackageAmount) {
        this.redPackageAmount = redPackageAmount;
    }

    /**
     * 收益总额
     */
    private String totalIncome;

    public String getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(String totalIncome) {
        this.totalIncome = totalIncome;
    }
}
