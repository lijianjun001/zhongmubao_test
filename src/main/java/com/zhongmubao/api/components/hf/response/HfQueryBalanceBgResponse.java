package com.zhongmubao.api.components.hf.response;

import com.google.gson.annotations.SerializedName;

/**
 * 余额查询接口返回参数
 *
 * @author 孙阿龙
 */
public class HfQueryBalanceBgResponse extends HfBaseResponse {

    /**
     * 版本
     */
    @SerializedName("Version")
    private String version;

    /**
     * 可用余额
     */
    @SerializedName("AvlBal")
    private Double avlBal;

    /**
     * 账户余额
     */
    @SerializedName("AcctBal")
    private Double acctBal;

    /**
     * 冻结余额
     */
    @SerializedName("FrzBal")
    private Double frzBal;

    /**
     * 商户号
     */
    @SerializedName("MerCustId")
    private String merCustId;

    /**
     * 客户号
     */
    @SerializedName("UsrCustId")
    private String usrCustId;

    /**
     * 签名
     */
    @SerializedName("PlainStr")
    private String plainStr;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Double getAvlBal() {
        return avlBal;
    }

    public void setAvlBal(Double avlBal) {
        this.avlBal = avlBal;
    }

    public Double getAcctBal() {
        return acctBal;
    }

    public void setAcctBal(Double acctBal) {
        this.acctBal = acctBal;
    }

    public Double getFrzBal() {
        return frzBal;
    }

    public void setFrzBal(Double frzBal) {
        this.frzBal = frzBal;
    }

    public String getMerCustId() {
        return merCustId;
    }

    public void setMerCustId(String merCustId) {
        this.merCustId = merCustId;
    }

    public String getUsrCustId() {
        return usrCustId;
    }

    public void setUsrCustId(String usrCustId) {
        this.usrCustId = usrCustId;
    }

    public String getPlainStr() {
        return plainStr;
    }

    public void setPlainStr(String plainStr) {
        this.plainStr = plainStr;
    }
}
