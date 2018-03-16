package com.zhongmubao.api.dto.response.my.transaction;

import java.util.ArrayList;

/**
 * 交易明细 -- 月账单
 *
 * @author 米立林
 */
public class MonthlyBillViewModel {

    public MonthlyBillViewModel() {
    }

    public MonthlyBillViewModel(String yearMonth, String totalAmount, ArrayList<SheepProjectInfoModel> list, String totalIncome, String sheepIncome, String redPacketIncome, String totalRecharge, String totalWithdraw) {
        this.yearMonth = yearMonth;
        this.totalAmount = totalAmount;
        this.list = list;
        this.totalIncome = totalIncome;
        this.sheepIncome = sheepIncome;
        this.redPacketIncome = redPacketIncome;
        this.totalRecharge = totalRecharge;
        this.totalWithdraw = totalWithdraw;
    }

    /**
     * 结果缺醒类型
     * 00 没有交易，01 没有购羊 02 正常统计
     */
    private String resultType;
    /**
     * 账单年月
     */
    private String yearMonth;
    /**
     * 总金额
     */
    private String totalAmount;

    /**
     * 购羊/商铺详情
     */
    private ArrayList<SheepProjectInfoModel> list;

    /**
     * 总收益
     */
    private String totalIncome;

    /**
     * 羊只收益
     */
    private String sheepIncome;

    /**
     * 红包收益
     */
    private String redPacketIncome;

    /**
     * 总充值
     */
    private String totalRecharge;

    /**
     * 总提现
     */
    private String totalWithdraw;

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public ArrayList<SheepProjectInfoModel> getList() {
        return list;
    }

    public void setList(ArrayList<SheepProjectInfoModel> list) {
        this.list = list;
    }

    public String getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(String totalIncome) {
        this.totalIncome = totalIncome;
    }

    public String getSheepIncome() {
        return sheepIncome;
    }

    public void setSheepIncome(String sheepIncome) {
        this.sheepIncome = sheepIncome;
    }

    public String getRedPacketIncome() {
        return redPacketIncome;
    }

    public void setRedPacketIncome(String redPacketIncome) {
        this.redPacketIncome = redPacketIncome;
    }

    public String getTotalRecharge() {
        return totalRecharge;
    }

    public void setTotalRecharge(String totalRecharge) {
        this.totalRecharge = totalRecharge;
    }

    public String getTotalWithdraw() {
        return totalWithdraw;
    }

    public void setTotalWithdraw(String totalWithdraw) {
        this.totalWithdraw = totalWithdraw;
    }
}
