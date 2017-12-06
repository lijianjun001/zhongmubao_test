package com.zhongmubao.api.entity;

import java.util.Date;

public class CustomerSina {

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

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public boolean getIsRealName() {
        return isRealName;
    }

    public void setRealName(boolean realName) {
        isRealName = realName;
    }

    public boolean getIsBindAuth() {
        return isBindAuth;
    }

    public void setBindAuth(boolean bindAuth) {
        isBindAuth = bindAuth;
    }

    public boolean getIsBindCard() {
        return isBindCard;
    }

    public void setBindCard(boolean bindCard) {
        isBindCard = bindCard;
    }

    public boolean getIsSetPayPwd() {
        return isSetPayPwd;
    }

    public void setSetPayPwd(boolean setPayPwd) {
        isSetPayPwd = setPayPwd;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    private int id;
    private  int customerId;
    private String uID;
    private boolean isRealName;
    private boolean isBindAuth;
    private boolean isBindCard;
    private boolean isSetPayPwd;
    private Date created;
    private Date modified;
    private String cardId;
}
