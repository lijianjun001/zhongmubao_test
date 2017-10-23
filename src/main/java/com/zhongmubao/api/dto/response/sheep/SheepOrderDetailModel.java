package com.zhongmubao.api.dto.response.sheep;


/**
 * 买羊订单详情ViewModel
 */
public class SheepOrderDetailModel {

    public SheepOrderDetailModel() {
    }

    public SheepOrderDetailModel(int id, int projectId, int customerId, String title, String code, int count, String totalAmount, String deductibleAmount, String paymentAmount, String state, String redeemAmount, String created, String paymentTime, int redeemDays, String redeemTime, String sheepIncome, String redPackageAmount, String totalIncome) {
        this.id = id;
        this.projectId = projectId;
        this.customerId = customerId;
        this.code = code;
        this.count = count;
        this.title = title;
        this.totalAmount = totalAmount;
        this.deductibleAmount = deductibleAmount;
        this.paymentAmount = paymentAmount;
        this.state = state;
        this.redeemAmount = redeemAmount;
        this.created = created;
        this.paymentTime = paymentTime;
        this.redeemDays = redeemDays;
        this.redeemTime = redeemTime;
        this.sheepIncome = sheepIncome;
        this.redPackageAmount = redPackageAmount;
        this.totalIncome = totalIncome;
    }

    private int id;
    private int projectId;
    private int customerId;
    private String title;
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
     * 赎回金额
     */
    private String redeemAmount;
    /**
     * 预期羊只收益
     */
    private String sheepIncome;
    /**
     * 红包收益
     */
    private String redPackageAmount;
    /**
     * 收益总额
     */
    private String totalIncome;
    /**
     * 下单时间
     */
    private String created;
    /**
     * 付款时间
     */
    private String paymentTime;
    /**
     * 可赎回剩余天数
     */
    private int redeemDays;
    /**
     * 可赎回日期
     */
    private String redeemTime;


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

    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRedeemAmount() {
        return redeemAmount;
    }

    public void setRedeemAmount(String redeemAmount) {
        this.redeemAmount = redeemAmount;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public int getRedeemDays() {
        return redeemDays;
    }

    public void setRedeemDays(int redeemDays) {
        redeemDays = redeemDays;
    }

    public String getRedeemTime() {
        return redeemTime;
    }

    public void setRedeemTime(String redeemTime) {
        this.redeemTime = redeemTime;
    }

    public String getRedPackageAmount() {
        return redPackageAmount;
    }

    public void setRedPackageAmount(String redPackageAmount) {
        this.redPackageAmount = redPackageAmount;
    }

    public String getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(String totalIncome) {
        this.totalIncome = totalIncome;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSheepIncome() {
        return sheepIncome;
    }

    public void setSheepIncome(String sheepIncome) {
        this.sheepIncome = sheepIncome;
    }
}
