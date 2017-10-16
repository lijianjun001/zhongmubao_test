package com.zhongmubao.api.entity;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public class SheepOrder {

    public SheepOrder() {

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

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private BigDecimal totalAmount;

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    private BigDecimal deductibleAmount;

    public BigDecimal getDeductibleAmount() {
        return deductibleAmount;
    }

    public void setDeductibleAmount(BigDecimal deductibleAmount) {
        this.deductibleAmount = deductibleAmount;
    }

    private int deductibleCount;

    public int getDeductibleCount() {
        return deductibleCount;
    }

    public void setDeductibleCount(int deductibleCount) {
        this.deductibleCount = deductibleCount;
    }

    private BigDecimal advanceAmount;

    public BigDecimal getAdvanceAmount() {
        return advanceAmount;
    }

    public void setAdvanceAmount(BigDecimal advanceAmount) {
        this.advanceAmount = advanceAmount;
    }

    private BigDecimal payableAmount;

    public BigDecimal getPayableAmount() {
        return payableAmount;
    }

    public void setPayableAmount(BigDecimal payableAmount) {
        this.payableAmount = payableAmount;
    }

    private BigDecimal paymentAmount;

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    private String paymentTN;

    public String getPaymentTN() {
        return paymentTN;
    }

    public void setPaymentTN(String paymentTN) {
        this.paymentTN = paymentTN;
    }

    private String paymentType;

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    private String paymentPlatform;

    public String getPaymentPlatform() {
        return paymentPlatform;
    }

    public void setPaymentPlatform(String paymentPlatform) {
        this.paymentPlatform = paymentPlatform;
    }

    private String orderPlatform;

    public String getOrderPlatform() {
        return orderPlatform;
    }

    public void setOrderPlatform(String orderPlatform) {
        this.orderPlatform = orderPlatform;
    }

    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    private Date expired;

    public Date getExpired() {
        return expired;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }

    private BigDecimal redemAmount;

    public BigDecimal getRedemAmount() {
        return redemAmount;
    }

    public void setRedemAmount(BigDecimal redemAmount) {
        this.redemAmount = redemAmount;
    }

    private String redemName;

    public String getRedemName() {
        return redemName;
    }

    public void setRedemName(String redemName) {
        this.redemName = redemName;
    }

    private String redemBank;

    public String getRedemBank() {
        return redemBank;
    }

    public void setRedemBank(String redemBank) {
        this.redemBank = redemBank;
    }

    private String redemBankAccount;

    public String getRedemBankAccount() {
        return redemBankAccount;
    }

    public void setRedemBankAccount(String redemBankAccount) {
        this.redemBankAccount = redemBankAccount;
    }

    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    private boolean frozened;

    public boolean getFrozened() {
        return frozened;
    }

    public void setFrozened(boolean frozened) {
        this.frozened = frozened;
    }

    private boolean deleted;

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    private Date created;

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    private Date modified;

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    private boolean isBouns;

    public boolean getIsBouns() {
        return isBouns;
    }

    public void setIsBouns(boolean isBouns) {
        this.isBouns = isBouns;
    }

    private Date paymentTime;

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    private boolean isSendLibrary;

    public boolean getIsSendLibrary() {
        return isSendLibrary;
    }

    public void setIsSendLibrary(boolean isSendLibrary) {
        this.isSendLibrary = isSendLibrary;
    }

    private String sign;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

}
