package com.zhongmubao.api.components.hf.response;

import com.google.gson.annotations.SerializedName;

/**
 * 自动扣款（放款）相应
 *
 * @author 孙阿龙
 */
public class HfLoansResponse extends HfBaseResponse {

    /**
     * 版本号
     */
    @SerializedName("Version")
    private String version;

    /**
     * 订单Id
     */
    @SerializedName("OrdId")
    private String ordId;

    /**
     * 订单时间
     */
    @SerializedName("OrdDate")
    private String ordDate;

    /**
     * 出账客户号
     */
    @SerializedName("OutCustId")
    private String outCustId;

    /**
     * 出账子账户
     */
    @SerializedName("OutAcctId")
    private String outAcctId;

    /**
     * 交易金额
     */
    @SerializedName("TransAmt")
    private String transAmt;

    /**
     * 手续费
     */
    @SerializedName("Fee")
    private String fee;

    /**
     * 入账客户
     */
    @SerializedName("InCustId")
    private String inCustId;

    /**
     * 入账子账户
     */
    @SerializedName("InAcctId")
    private String inAcctId;

    /**
     * 标的订单号
     */
    @SerializedName("SubOrdId")
    private String subOrdId;

    /**
     * 标订单号的时间
     */
    @SerializedName("SubOrdDate")
    private String subOrdDate;

    /**
     * 是否添加到资金池
     */
    @SerializedName("IsDefault")
    private String isDefault;

    /**
     * 后台回调地址
     */
    @SerializedName("BgRetUrl")
    private String bgRetUrl;

    /**
     * 商户私有域
     */
    @SerializedName("MerPriv")
    private String merPriv;

    /**
     * 是否解冻
     */
    @SerializedName("IsUnFreeze")
    private String isUnFreeze;

    /**
     * 解冻订单号
     */
    @SerializedName("UnFreezeOrdId")
    private String unFreezeOrdId;

    /**
     * 冻结标识
     */
    @SerializedName("FreezeTrxId")
    private String freezeTrxId;

    /**
     *
     */
    @SerializedName("FeeObjFlag")
    private String feeObjFlag;

    /**
     * 返回扩展与
     */
    @SerializedName("RespExt")
    private String respExt;

    /**
     * 校验字符串
     */
    @SerializedName("PlainStr")
    private String plainStr;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getOrdId() {
        return ordId;
    }

    public void setOrdId(String ordId) {
        this.ordId = ordId;
    }

    public String getOrdDate() {
        return ordDate;
    }

    public void setOrdDate(String ordDate) {
        this.ordDate = ordDate;
    }

    public String getOutCustId() {
        return outCustId;
    }

    public void setOutCustId(String outCustId) {
        this.outCustId = outCustId;
    }

    public String getOutAcctId() {
        return outAcctId;
    }

    public void setOutAcctId(String outAcctId) {
        this.outAcctId = outAcctId;
    }

    public String getTransAmt() {
        return transAmt;
    }

    public void setTransAmt(String transAmt) {
        this.transAmt = transAmt;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getInCustId() {
        return inCustId;
    }

    public void setInCustId(String inCustId) {
        this.inCustId = inCustId;
    }

    public String getInAcctId() {
        return inAcctId;
    }

    public void setInAcctId(String inAcctId) {
        this.inAcctId = inAcctId;
    }

    public String getSubOrdId() {
        return subOrdId;
    }

    public void setSubOrdId(String subOrdId) {
        this.subOrdId = subOrdId;
    }

    public String getSubOrdDate() {
        return subOrdDate;
    }

    public void setSubOrdDate(String subOrdDate) {
        this.subOrdDate = subOrdDate;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public String getBgRetUrl() {
        return bgRetUrl;
    }

    public void setBgRetUrl(String bgRetUrl) {
        this.bgRetUrl = bgRetUrl;
    }

    public String getMerPriv() {
        return merPriv;
    }

    public void setMerPriv(String merPriv) {
        this.merPriv = merPriv;
    }

    public String getIsUnFreeze() {
        return isUnFreeze;
    }

    public void setIsUnFreeze(String isUnFreeze) {
        this.isUnFreeze = isUnFreeze;
    }

    public String getUnFreezeOrdId() {
        return unFreezeOrdId;
    }

    public void setUnFreezeOrdId(String unFreezeOrdId) {
        this.unFreezeOrdId = unFreezeOrdId;
    }

    public String getFreezeTrxId() {
        return freezeTrxId;
    }

    public void setFreezeTrxId(String freezeTrxId) {
        this.freezeTrxId = freezeTrxId;
    }

    public String getFeeObjFlag() {
        return feeObjFlag;
    }

    public void setFeeObjFlag(String feeObjFlag) {
        this.feeObjFlag = feeObjFlag;
    }

    public String getRespExt() {
        return respExt;
    }

    public void setRespExt(String respExt) {
        this.respExt = respExt;
    }

    public String getPlainStr() {
        return plainStr;
    }

    public void setPlainStr(String plainStr) {
        this.plainStr = plainStr;
    }

}
