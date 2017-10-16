package com.zhongmubao.api.entity;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public class ExtSmashegg {
    
	public ExtSmashegg() {

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

	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	private boolean isUsed;

	public boolean getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

	private int orderId;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	private int libraryCount;

	public int getLibraryCount() {
		return libraryCount;
	}

	public void setLibraryCount(int libraryCount) {
		this.libraryCount = libraryCount;
	}

	private String prizeType;

	public String getPrizeType() {
		return prizeType;
	}

	public void setPrizeType(String prizeType) {
		this.prizeType = prizeType;
	}

	private int smashLibraryCount;

	public int getSmashLibraryCount() {
		return smashLibraryCount;
	}

	public void setSmashLibraryCount(int smashLibraryCount) {
		this.smashLibraryCount = smashLibraryCount;
	}

	private int giftOrderId;

	public int getGiftOrderId() {
		return giftOrderId;
	}

	public void setGiftOrderId(int giftOrderId) {
		this.giftOrderId = giftOrderId;
	}

	private String badEggState;

	public String getBadEggState() {
		return badEggState;
	}

	public void setBadEggState(String badEggState) {
		this.badEggState = badEggState;
	}

	private String remark;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	private Date smashTime;

	public Date getSmashTime() {
		return smashTime;
	}

	public void setSmashTime(Date smashTime) {
		this.smashTime = smashTime;
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
