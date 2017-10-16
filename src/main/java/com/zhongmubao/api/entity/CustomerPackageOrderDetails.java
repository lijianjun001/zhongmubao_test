package com.zhongmubao.api.entity;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public class CustomerPackageOrderDetails {

    public CustomerPackageOrderDetails() {

    }

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int packageOrderId;

    public int getPackageOrderId() {
        return packageOrderId;
    }

    public void setPackageOrderId(int packageOrderId) {
        this.packageOrderId = packageOrderId;
    }

    private int customerGiftId;

    public int getCustomerGiftId() {
        return customerGiftId;
    }

    public void setCustomerGiftId(int customerGiftId) {
        this.customerGiftId = customerGiftId;
    }

    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private BigDecimal amount;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
