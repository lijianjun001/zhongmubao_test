package com.zhongmubao.api.entity;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public class SheepProject {

    public SheepProject() {

    }

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int vendorId;

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    private int sheepTypeId;

    public int getSheepTypeId() {
        return sheepTypeId;
    }

    public void setSheepTypeId(int sheepTypeId) {
        this.sheepTypeId = sheepTypeId;
    }

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    private BigDecimal rate;

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    private int period;

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    private int purchaseCount;

    public int getPurchaseCount() {
        return purchaseCount;
    }

    public void setPurchaseCount(int purchaseCount) {
        this.purchaseCount = purchaseCount;
    }

    private int libraryCount;

    public int getLibraryCount() {
        return libraryCount;
    }

    public void setLibraryCount(int libraryCount) {
        this.libraryCount = libraryCount;
    }

    private int deductibleCount;

    public int getDeductibleCount() {
        return deductibleCount;
    }

    public void setDeductibleCount(int deductibleCount) {
        this.deductibleCount = deductibleCount;
    }

    private Date beginTime;

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    private Date endTime;

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    private Date effectiveTime;

    public Date getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    private Date redemTime;

    public Date getRedemTime() {
        return redemTime;
    }

    public void setRedemTime(Date redemTime) {
        this.redemTime = redemTime;
    }

    private BigDecimal newMemberPrice;

    public BigDecimal getNewMemberPrice() {
        return newMemberPrice;
    }

    public void setNewMemberPrice(BigDecimal newMemberPrice) {
        this.newMemberPrice = newMemberPrice;
    }

    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    private int sort;

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
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

    private int customerActivityId;

    public int getCustomerActivityId() {
        return customerActivityId;
    }

    public void setCustomerActivityId(int customerActivityId) {
        this.customerActivityId = customerActivityId;
    }

    private boolean isAlreadyRemittance;

    public boolean getIsAlreadyRemittance() {
        return isAlreadyRemittance;
    }

    public void setIsAlreadyRemittance(boolean isAlreadyRemittance) {
        this.isAlreadyRemittance = isAlreadyRemittance;
    }

    private boolean isAlreadyReturn;

    public boolean getIsAlreadyReturn() {
        return isAlreadyReturn;
    }

    public void setIsAlreadyReturn(boolean isAlreadyReturn) {
        this.isAlreadyReturn = isAlreadyReturn;
    }

}
