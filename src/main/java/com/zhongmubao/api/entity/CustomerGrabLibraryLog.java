package com.zhongmubao.api.entity;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public class CustomerGrabLibraryLog {

    public CustomerGrabLibraryLog() {

    }

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int customerId;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    private int shareCustomerId;

    public int getShareCustomerId() {
        return shareCustomerId;
    }

    public void setShareCustomerId(int shareCustomerId) {
        this.shareCustomerId = shareCustomerId;
    }

    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private Date created;

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    private int customerActivityId;

    public int getCustomerActivityId() {
        return customerActivityId;
    }

    public void setCustomerActivityId(int customerActivityId) {
        this.customerActivityId = customerActivityId;
    }

}
