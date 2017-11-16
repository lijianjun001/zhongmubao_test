package com.zhongmubao.api.components.hf.request;

import java.util.Date;

/**
 * 商户扣款对账请求
 *
 * @author 孙阿龙
 */
public class HfTrfReconciliationRequest extends HfBaseRequest {

    /**
     * 开始时间
     */
    private Date beginDate;

    /**
     * 结束时间
     */
    private Date endDate;

    /**
     * 页码
     */
    private int pageNum;

    /**
     * 每页显示条数
     */
    private int pageSize;

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
