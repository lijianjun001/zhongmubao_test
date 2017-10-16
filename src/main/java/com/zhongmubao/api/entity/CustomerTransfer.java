package com.zhongmubao.api.entity;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public class CustomerTransfer {

    public CustomerTransfer() {

    }

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int oldCustomerId;

    public int getOldCustomerId() {
        return oldCustomerId;
    }

    public void setOldCustomerId(int oldCustomerId) {
        this.oldCustomerId = oldCustomerId;
    }

    private int newCustomerId;

    public int getNewCustomerId() {
        return newCustomerId;
    }

    public void setNewCustomerId(int newCustomerId) {
        this.newCustomerId = newCustomerId;
    }

    private String cardName;

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    private String cardNo;

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    private String cardNoPath;

    public String getCardNoPath() {
        return cardNoPath;
    }

    public void setCardNoPath(String cardNoPath) {
        this.cardNoPath = cardNoPath;
    }

    private int systemUserId;

    public int getSystemUserId() {
        return systemUserId;
    }

    public void setSystemUserId(int systemUserId) {
        this.systemUserId = systemUserId;
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

}
