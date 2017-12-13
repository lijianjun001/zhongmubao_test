package com.zhongmubao.api.dto.request.my.transaction;

import com.zhongmubao.api.config.enmu.TransactionDetailType;

/**
 * 交易明细请求参数
 *
 * @author 米立林
 */
public class TransactionRequestModel {
    /**
     * 页索引
     */
    private int pageIndex;

    /**
     * 月账单日期
     */
    private String billDate;

    /**
     * 账单类型（充值、提现、买羊、赎回）
     */
    private TransactionDetailType billType;

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

    public TransactionDetailType getBillType() {
        return billType;
    }

    public void setBillType(TransactionDetailType billType) {
        this.billType = billType;
    }
}
