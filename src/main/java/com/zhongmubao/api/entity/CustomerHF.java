package com.zhongmubao.api.entity;

import java.util.Date;

public class CustomerHF {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getUsrCustId() {
        return usrCustId;
    }

    public void setUsrCustId(String userCustId) {
        this.usrCustId = userCustId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return Modified;
    }

    public void setModified(Date modified) {
        Modified = modified;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean getIsBandCard() {
        return isBandCard;
    }

    public void setBandCard(boolean bandCard) {
        isBandCard = bandCard;
    }

    public boolean getIsBosAcct() {
        return isBosAcct;
    }

    public void setBosAcct(boolean bosAcct) {
        isBosAcct = bosAcct;
    }

    private int id;
    private int customerId;
    private String usrCustId;
    private String cardId;
    private String bankId;
    private String phone;
    private String cardType;
    private String cardNo;
    private String name;
    private Date created;
    private Date Modified;
    private boolean deleted;
    private boolean isBandCard;
    private boolean isBosAcct;
}
