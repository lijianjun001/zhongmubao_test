package com.zhongmubao.api.components.hf.response;

import com.google.gson.annotations.SerializedName;

/**
 * 交易状态查询结果
 *
 * @author 孙阿龙
 */
public class HfQueryTransStatResponse extends HfBaseResponse {

    /**
     * 订单号
     */
    @SerializedName("OrdId")
    private String ordId;

    /**
     * 订单时间
     */
    @SerializedName("OrdDate")
    private String ordDate;

    /**
     * 交易类型
     */
    @SerializedName("QueryTransType")
    private String queryTransType;

    /**
     * 交易状态
     */
    @SerializedName("TransStat")
    private String transStat;

    /**
     * 交易金额
     */
    @SerializedName("TransAmt")
    private String transAmt;

    /**
     * 冻结标识
     */
    @SerializedName("TrxId")
    private String trxId;

    /**
     * 验签名字符串
     */
    @SerializedName("PlainStr")
    private String plainStr;

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

    public String getQueryTransType() {
        return queryTransType;
    }

    public void setQueryTransType(String queryTransType) {
        this.queryTransType = queryTransType;
    }

    public String getTransStat() {
        return transStat;
    }

    public void setTransStat(String transStat) {
        this.transStat = transStat;
    }

    public String getTransAmt() {
        return transAmt;
    }

    public void setTransAmt(String transAmt) {
        this.transAmt = transAmt;
    }

    public String getTrxId() {
        return trxId;
    }

    public void setTrxId(String trxId) {
        this.trxId = trxId;
    }

    public String getPlainStr() {
        return plainStr;
    }

    public void setPlainStr(String plainStr) {
        this.plainStr = plainStr;
    }

}
