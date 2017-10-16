package com.zhongmubao.api.entity;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public class SheepOrderRedeemLog {
    
	public SheepOrderRedeemLog() {

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

	private int projectId;

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	private BigDecimal price;

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	private String sinaInfo;

	public String getSinaInfo() {
		return sinaInfo;
	}

	public void setSinaInfo(String sinaInfo) {
		this.sinaInfo = sinaInfo;
	}

	private Date createTime;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	private String outTradeNo;

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	private String notifyTradeStatus;

	public String getNotifyTradeStatus() {
		return notifyTradeStatus;
	}

	public void setNotifyTradeStatus(String notifyTradeStatus) {
		this.notifyTradeStatus = notifyTradeStatus;
	}

}
