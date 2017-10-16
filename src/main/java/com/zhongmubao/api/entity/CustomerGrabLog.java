package com.zhongmubao.api.entity;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public class CustomerGrabLog {

    public CustomerGrabLog() {

    }

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private int customerId;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    private boolean isGrab;

    public boolean getIsGrab() {
        return isGrab;
    }

    public void setIsGrab(boolean isGrab) {
        this.isGrab = isGrab;
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

}
