package com.zhongmubao.api.components.hf.request;

import java.util.Date;

/**
 * 自动扣款（放款）请求
 *
 * @author 孙阿龙
 */
public class HfLoansRequest extends HfBaseRequest {

    /**
     * 订单号
     */
    private String ordId;

    /**
     * 订单日期
     */
    private Date ordDate;

    /**
     * 出帐客户号
     */
    private String outCustId;

    /**
     * 交易金额
     */
    private Double transAmt;

    /**
     * 扣款手续费
     */
    private String fee;

    /**
     * 标的订单号
     */
    private String subOrdId;

    /**
     * 标的订单时间
     */
    private Date subOrdDate;

    /**
     * 入账账户
     */
    private String inCustId;

    /**
     * 解冻订单号
     */
    private String unFreezeOrdId;

    /**
     * 冻结标识
     */
    private String freezeTrxId;

    /**
     * 后台应答地址
     */
    private String bgRetUrl;

    /**
     * 扩展请求参数
     */
    private String reqExt;

    /**
     * 订单号
     */
    private String proId;


    public String getOrdId() {
        return ordId;
    }

    public void setOrdId(String ordId) {
        this.ordId = ordId;
    }

    public Date getOrdDate() {
        return ordDate;
    }

    public void setOrdDate(Date ordDate) {
        this.ordDate = ordDate;
    }

    public String getOutCustId() {
        return outCustId;
    }

    public void setOutCustId(String outCustId) {
        this.outCustId = outCustId;
    }

    public Double getTransAmt() {
        return transAmt;
    }

    public void setTransAmt(Double transAmt) {
        this.transAmt = transAmt;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getSubOrdId() {
        return subOrdId;
    }

    public void setSubOrdId(String subOrdId) {
        this.subOrdId = subOrdId;
    }

    public Date getSubOrdDate() {
        return subOrdDate;
    }

    public void setSubOrdDate(Date subOrdDate) {
        this.subOrdDate = subOrdDate;
    }

    public String getInCustId() {
        return inCustId;
    }

    public void setInCustId(String inCustId) {
        this.inCustId = inCustId;
    }

    public String getUnFreezeOrdId() {
        return unFreezeOrdId;
    }

    public void setUnFreezeOrdId(String unFreezeOrdId) {
        this.unFreezeOrdId = unFreezeOrdId;
    }

    public String getBgRetUrl() {
        return bgRetUrl;
    }

    public void setBgRetUrl(String bgRetUrl) {
        this.bgRetUrl = bgRetUrl;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getFreezeTrxId() {
        return freezeTrxId;
    }

    public void setFreezeTrxId(String freezeTrxId) {
        this.freezeTrxId = freezeTrxId;
    }

    public String getReqExt() {
        return reqExt;
    }

    public void setReqExt(String reqExt) {
        this.reqExt = reqExt;
    }


}
