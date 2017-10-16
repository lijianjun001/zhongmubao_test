package com.zhongmubao.api.entity;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public class ExtActivityRecord {

    public ExtActivityRecord() {

    }

    private int id;

    public ExtActivityRecord(int customerId, int extActivityId, String name, String phone, String remark, Date created, boolean deleted, String info, String state, String auditReason) {
        this.customerId = customerId;
        this.extActivityId = extActivityId;
        this.name = name;
        this.phone = phone;
        this.remark = remark;
        this.created = created;
        this.deleted = deleted;
        this.info = info;
        this.state = state;
        this.auditReason = auditReason;
    }

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

    private int extActivityId;

    public int getExtActivityId() {
        return extActivityId;
    }

    public void setExtActivityId(int extActivityId) {
        this.extActivityId = extActivityId;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    private Date created;

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    private boolean deleted;

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    private String auditReason;

    public String getAuditReason() {
        return auditReason;
    }

    public void setAuditReason(String auditReason) {
        this.auditReason = auditReason;
    }

}
