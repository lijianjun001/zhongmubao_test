package com.zhongmubao.api.entity;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public class SheepBonus {

    public SheepBonus() {

    }

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int senderId;

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    private int sendOrderId;

    public int getSendOrderId() {
        return sendOrderId;
    }

    public void setSendOrderId(int sendOrderId) {
        this.sendOrderId = sendOrderId;
    }

    private String sendOrderNo;

    public String getSendOrderNo() {
        return sendOrderNo;
    }

    public void setSendOrderNo(String sendOrderNo) {
        this.sendOrderNo = sendOrderNo;
    }

    private String receiver;

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    private String receiverName;

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    private int receiveOrderId;

    public int getReceiveOrderId() {
        return receiveOrderId;
    }

    public void setReceiveOrderId(int receiveOrderId) {
        this.receiveOrderId = receiveOrderId;
    }

    private String receiveOrderNo;

    public String getReceiveOrderNo() {
        return receiveOrderNo;
    }

    public void setReceiveOrderNo(String receiveOrderNo) {
        this.receiveOrderNo = receiveOrderNo;
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

    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

}
