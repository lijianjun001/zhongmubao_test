package com.zhongmubao.api.components.hf.response;

import com.google.gson.annotations.SerializedName;

/**
 * 子账号详情
 *
 * @author 孙阿龙
 */
public class HfQueryAcctDetail {

    @SerializedName("AcctType")
    private String acctType;

    @SerializedName("SubAcctId")
    private String subAcctId;

    @SerializedName("AvlBal")
    private String avlBal;

    @SerializedName("AcctBal")
    private String acctBal;

    @SerializedName("FrzBal")
    private String frzBal;

    @SerializedName("UsrName")
    private String usrName;

    @SerializedName("UsrMp")
    private String usrMp;

    @SerializedName("CertType")
    private String certType;

    @SerializedName("CertId")
    private String certId;

    @SerializedName("BusId")
    private String busId;

    @SerializedName("TaxId")
    private String taxId;

    @SerializedName("OpenDate")
    private String openDate;

    @SerializedName("OpenTime")
    private String openTime;

    @SerializedName("AcctPayType")
    private String acctPayType;

    @SerializedName("Stat")
    private String stat;

    @SerializedName("AcctAlias")
    private String acctAlias;

    @SerializedName("Caccount")
    private String caccount;

    public String getAcctType() {
        return acctType;
    }

    public void setAcctType(String acctType) {
        this.acctType = acctType;
    }

    public String getSubAcctId() {
        return subAcctId;
    }

    public void setSubAcctId(String subAcctId) {
        this.subAcctId = subAcctId;
    }

    public String getAvlBal() {
        return avlBal;
    }

    public void setAvlBal(String avlBal) {
        this.avlBal = avlBal;
    }

    public String getAcctBal() {
        return acctBal;
    }

    public void setAcctBal(String acctBal) {
        this.acctBal = acctBal;
    }

    public String getFrzBal() {
        return frzBal;
    }

    public void setFrzBal(String frzBal) {
        this.frzBal = frzBal;
    }

    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

    public String getUsrMp() {
        return usrMp;
    }

    public void setUsrMp(String usrMp) {
        this.usrMp = usrMp;
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType;
    }

    public String getCertId() {
        return certId;
    }

    public void setCertId(String certId) {
        this.certId = certId;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getAcctPayType() {
        return acctPayType;
    }

    public void setAcctPayType(String acctPayType) {
        this.acctPayType = acctPayType;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getAcctAlias() {
        return acctAlias;
    }

    public void setAcctAlias(String acctAlias) {
        this.acctAlias = acctAlias;
    }

    public String getCaccount() {
        return caccount;
    }

    public void setCaccount(String caccount) {
        this.caccount = caccount;
    }
}
