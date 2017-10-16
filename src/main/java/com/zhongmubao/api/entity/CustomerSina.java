package com.zhongmubao.api.entity;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public class CustomerSina {

    public CustomerSina() {

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

    private String uID;

    public String getUID() {
        return uID;
    }

    public void setUID(String uID) {
        this.uID = uID;
    }

    private boolean isRealName;

    public boolean getIsRealName() {
        return isRealName;
    }

    public void setIsRealName(boolean isRealName) {
        this.isRealName = isRealName;
    }

    private boolean isBindAuth;

    public boolean getIsBindAuth() {
        return isBindAuth;
    }

    public void setIsBindAuth(boolean isBindAuth) {
        this.isBindAuth = isBindAuth;
    }

    private boolean isBindCard;

    public boolean getIsBindCard() {
        return isBindCard;
    }

    public void setIsBindCard(boolean isBindCard) {
        this.isBindCard = isBindCard;
    }

    private boolean isSetPayPwd;

    public boolean getIsSetPayPwd() {
        return isSetPayPwd;
    }

    public void setIsSetPayPwd(boolean isSetPayPwd) {
        this.isSetPayPwd = isSetPayPwd;
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

    private int cardId;

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

}
