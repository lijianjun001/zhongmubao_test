package com.zhongmubao.api.entity;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public class Product {
    
	public Product() {

    }

	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private int projectId;

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	private String subTitle;

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	private String desc;

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	private String photo;

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	private String icon;

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	private BigDecimal amount;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	private BigDecimal marketAmount;

	public BigDecimal getMarketAmount() {
		return marketAmount;
	}

	public void setMarketAmount(BigDecimal marketAmount) {
		this.marketAmount = marketAmount;
	}

	private BigDecimal freightAmount;

	public BigDecimal getFreightAmount() {
		return freightAmount;
	}

	public void setFreightAmount(BigDecimal freightAmount) {
		this.freightAmount = freightAmount;
	}

	private int freeFreightCount;

	public int getFreeFreightCount() {
		return freeFreightCount;
	}

	public void setFreeFreightCount(int freeFreightCount) {
		this.freeFreightCount = freeFreightCount;
	}

	private String promotionInfo;

	public String getPromotionInfo() {
		return promotionInfo;
	}

	public void setPromotionInfo(String promotionInfo) {
		this.promotionInfo = promotionInfo;
	}

	private int maxLibrary;

	public int getMaxLibrary() {
		return maxLibrary;
	}

	public void setMaxLibrary(int maxLibrary) {
		this.maxLibrary = maxLibrary;
	}

	private int saleCount;

	public int getSaleCount() {
		return saleCount;
	}

	public void setSaleCount(int saleCount) {
		this.saleCount = saleCount;
	}

	private int stock;

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	private String attrDesc;

	public String getAttrDesc() {
		return attrDesc;
	}

	public void setAttrDesc(String attrDesc) {
		this.attrDesc = attrDesc;
	}

	private String ppv;

	public String getPpv() {
		return ppv;
	}

	public void setPpv(String ppv) {
		this.ppv = ppv;
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

	private Date saleTime;

	public Date getSaleTime() {
		return saleTime;
	}

	public void setSaleTime(Date saleTime) {
		this.saleTime = saleTime;
	}

	private int maxBuyCount;

	public int getMaxBuyCount() {
		return maxBuyCount;
	}

	public void setMaxBuyCount(int maxBuyCount) {
		this.maxBuyCount = maxBuyCount;
	}

}
