package com.zhongmubao.api.dto.request.my.transaction;

/**
 * 交易月账单请求参数
 *
 * @author 米立林
 */
public class TransactionMonthlyBillRequestModel {
    /**
     * 月账单日期
     */
    private String billDate;

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }
}
