package com.zhongmubao.api.entity;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public class CustomerGift {
    
	public CustomerGift() {

    }

	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private int libraryCount;

	public int getLibraryCount() {
		return libraryCount;
	}

	public void setLibraryCount(int libraryCount) {
		this.libraryCount = libraryCount;
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String icon;

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	private String photo;

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	private String ppv;

	public String getPpv() {
		return ppv;
	}

	public void setPpv(String ppv) {
		this.ppv = ppv;
	}

	private String decription;

	public String getDecription() {
		return decription;
	}

	public void setDecription(String decription) {
		this.decription = decription;
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

	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	private BigDecimal price;

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	private int stock;

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	private String condition;

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	private int window;

	public int getWindow() {
		return window;
	}

	public void setWindow(int window) {
		this.window = window;
	}

	private String size;

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	private boolean panicBuying;

	public boolean getPanicBuying() {
		return panicBuying;
	}

	public void setPanicBuying(boolean panicBuying) {
		this.panicBuying = panicBuying;
	}

	private Date panicTime;

	public Date getPanicTime() {
		return panicTime;
	}

	public void setPanicTime(Date panicTime) {
		this.panicTime = panicTime;
	}

	private String panicPhoto;

	public String getPanicPhoto() {
		return panicPhoto;
	}

	public void setPanicPhoto(String panicPhoto) {
		this.panicPhoto = panicPhoto;
	}

	private String sketch;

	public String getSketch() {
		return sketch;
	}

	public void setSketch(String sketch) {
		this.sketch = sketch;
	}

	private boolean isQRCode;

	public boolean getIsQRCode() {
		return isQRCode;
	}

	public void setIsQRCode(boolean isQRCode) {
		this.isQRCode = isQRCode;
	}

	private String photos;

	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}

	private int sort;

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

}
