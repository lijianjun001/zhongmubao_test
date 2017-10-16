package com.zhongmubao.api.entity;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public class SheepVendorMoneyHistory {
    
	public SheepVendorMoneyHistory() {

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

	private BigDecimal money;

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	private String transaction;

	public String getTransaction() {
		return transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

	private String sinaInfo;

	public String getSinaInfo() {
		return sinaInfo;
	}

	public void setSinaInfo(String sinaInfo) {
		this.sinaInfo = sinaInfo;
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

	private int sysUserId;

	public int getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(int sysUserId) {
		this.sysUserId = sysUserId;
	}

	private String state;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
