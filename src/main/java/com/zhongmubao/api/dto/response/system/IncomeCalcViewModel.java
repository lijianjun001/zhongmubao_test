package com.zhongmubao.api.dto.response.system;

/**
 * 收益计算
 *
 * @author 米立林
 */
public class IncomeCalcViewModel {
    public IncomeCalcViewModel() {
        this.money = "0.00";
        this.zmbIncome = "0.00";
        this.bankIncome = "0.00";
        this.zfbIncome = "0.00";
    }

    private String money;
    private String zmbIncome;
    private String bankIncome;
    private String zfbIncome;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getZmbIncome() {
        return zmbIncome;
    }

    public void setZmbIncome(String zmbIncome) {
        this.zmbIncome = zmbIncome;
    }

    public String getBankIncome() {
        return bankIncome;
    }

    public void setBankIncome(String bankIncome) {
        this.bankIncome = bankIncome;
    }

    public String getZfbIncome() {
        return zfbIncome;
    }

    public void setZfbIncome(String zfbIncome) {
        this.zfbIncome = zfbIncome;
    }
}
