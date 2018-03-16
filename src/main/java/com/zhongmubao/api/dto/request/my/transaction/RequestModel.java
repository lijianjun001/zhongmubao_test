package com.zhongmubao.api.dto.request.my.transaction;


/**
 * 交易明细请求参数
 *
 * @author 米立林
 */
public class RequestModel {
    /**
     * 页索引
     */
    private int pageIndex;

    /**
     * 月账单日期
     */
    private String billDate;

    /**
     * 账单类型
     */
    private String billType;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }
}
