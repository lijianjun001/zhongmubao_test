package com.zhongmubao.api.components.hf.request;

import com.zhongmubao.api.components.hf.request.enmu.HfQueryTransStatType;

import java.util.Date;

/**
 * 交易状态请求
 *
 * @author 孙阿龙
 */
public class HfQueryTransStatRequest extends HfBaseRequest {

    /**
     * 订单编号
     */
    private String OrdId;

    /**
     * 订单时间
     */
    private Date OrdDate;

    /**
     * 交易类型
     */
    private HfQueryTransStatType QueryTransType;

    public String getOrdId() {
        return OrdId;
    }

    public void setOrdId(String ordId) {
        OrdId = ordId;
    }

    public Date getOrdDate() {
        return OrdDate;
    }

    public void setOrdDate(Date ordDate) {
        OrdDate = ordDate;
    }

    public HfQueryTransStatType getQueryTransType() {
        return QueryTransType;
    }

    public void setQueryTransType(HfQueryTransStatType queryTransType) {
        QueryTransType = queryTransType;
    }
}
