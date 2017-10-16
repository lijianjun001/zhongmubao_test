package com.zhongmubao.api.entity;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public class CustomerMoneyBoxHistory {
    
	public CustomerMoneyBoxHistory() {

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

	private BigDecimal originalMoeny;

	public BigDecimal getOriginalMoeny() {
		return originalMoeny;
	}

	public void setOriginalMoeny(BigDecimal originalMoeny) {
		this.originalMoeny = originalMoeny;
	}

	private BigDecimal addMoney;

	public BigDecimal getAddMoney() {
		return addMoney;
	}

	public void setAddMoney(BigDecimal addMoney) {
		this.addMoney = addMoney;
	}

	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	private String transaction;

	public String getTransaction() {
		return transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
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
