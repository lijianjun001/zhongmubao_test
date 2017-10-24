package com.zhongmubao.api.entity;

import java.util.Date;

/**
 * 客户实体
 *
 * @author 孙阿龙
 */
public class Customer {

	public Customer() {

	}

	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String account;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String sign;

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	private String nickName;

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String phone;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private String openId;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	private String cardType;

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	private String cardNo;

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	private String photo;

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	private int referenceId;

	public int getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(int referenceId) {
		this.referenceId = referenceId;
	}

	private boolean isGrantLibrary;

	public boolean getIsGrantLibrary() {
		return isGrantLibrary;
	}

	public void setIsGrantLibrary(boolean isGrantLibrary) {
		this.isGrantLibrary = isGrantLibrary;
	}

	private int count;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	private String platform;

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	private String registerIP;

	public String getRegisterIP() {
		return registerIP;
	}

	public void setRegisterIP(String registerIP) {
		this.registerIP = registerIP;
	}

	private String registerAddredss;

	public String getRegisterAddredss() {
		return registerAddredss;
	}

	public void setRegisterAddredss(String registerAddredss) {
		this.registerAddredss = registerAddredss;
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

	private String redeemPassword;

	public String getRedeemPassword() {
		return redeemPassword;
	}

	public void setRedeemPassword(String redeemPassword) {
		this.redeemPassword = redeemPassword;
	}

	private boolean enabledFingerprint;

	public boolean getEnabledFingerprint() {
		return enabledFingerprint;
	}

	public void setEnabledFingerprint(boolean enabledFingerprint) {
		this.enabledFingerprint = enabledFingerprint;
	}

	private int hadaCount;

	public int getHadaCount() {
		return hadaCount;
	}

	public void setHadaCount(int hadaCount) {
		this.hadaCount = hadaCount;
	}

	private boolean isAutoRedeem;

	public boolean getIsAutoRedeem() {
		return isAutoRedeem;
	}

	public void setIsAutoRedeem(boolean isAutoRedeem) {
		this.isAutoRedeem = isAutoRedeem;
	}

	private boolean isSetPassword;

	public boolean getIsSetPassword() {
		return isSetPassword;
	}

	public void setIsSetPassword(boolean isSetPassword) {
		this.isSetPassword = isSetPassword;
	}

}
