package com.zhongmubao.api.entity.ext;


import java.util.Date;

/**
 * 买羊订单详情
 */
public class SheepOrderDetailInfo {

    private int id;
    private int projectId;
    private int customerId;
    /**
     * 订单编号
     */
    private String code;
    /**
     * 数量
     */
    private int count;
    /**
     * 订单总额
     */
    private String totalAmount;
    /**
     * 减免金额（肉串抵用）
     */
    private String deductibleAmount;
    /**
     * 实付金额
     */
    private String paymentAmount;
    /**
     * 状态 00 未付款 01 已付款 02养殖中 03 可赎回 04赎回中 05 已赎回 06 已取消
     */
    private String state;
    /**
     * 赎回金额
     */
    private String redemAmount;
    /**
     * 红包收益
     */
    private String redPackageAmount;
    /**
     * 下单时间
     */
    private Date created;
    /**
     * 付款时间
     */
    private Date paymentTime;
    /**
     * 可赎回日期
     */
    private Date redeemTime;

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

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDeductibleAmount() {
        return deductibleAmount;
    }

    public void setDeductibleAmount(String deductibleAmount) {
        this.deductibleAmount = deductibleAmount;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRedemAmount() {
        return redemAmount;
    }

    public void setRedemAmount(String redeemAmount) {
        this.redemAmount = redeemAmount;
    }

    public String getRedPackageAmount() {
        return redPackageAmount;
    }

    public void setRedPackageAmount(String redPackageAmount) {
        this.redPackageAmount = redPackageAmount;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Date getRedeemTime() {
        return redeemTime;
    }

    public void setRedeemTime(Date redeemTime) {
        this.redeemTime = redeemTime;
    }
}
