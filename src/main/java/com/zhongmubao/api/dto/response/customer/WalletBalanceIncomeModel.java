package com.zhongmubao.api.dto.response.customer;

import java.util.List;

/**
 * 钱包余额收益
 * @author 米立林
 */
public class WalletBalanceIncomeModel {
    /**
     * 钱包余额
     */
    private String balance;
    /**
     * 昨日收益
     */
    private String leftDay;
    /**
     * 累计总收益
     */
    private String totalIncome;

    /**
     * 七日年化收益率
     */
    private List<SevenDaysIncome> list;

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getLeftDay() {
        return leftDay;
    }

    public void setLeftDay(String leftDay) {
        this.leftDay = leftDay;
    }

    public String getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(String totalIncome) {
        this.totalIncome = totalIncome;
    }

    public List<SevenDaysIncome> getList() {
        return list;
    }

    public void setList(List<SevenDaysIncome> list) {
        this.list = list;
    }
}
