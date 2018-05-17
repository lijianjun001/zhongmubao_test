package com.zhongmubao.api.dto.response.mp;

/**
 * 我的首页
 *
 * @author 米立林
 */
public class IndexViewModel {
    private String totalAsset;
    private String totalIncome;
    private String monthIncome;

    public String getTotalAsset() {
        return totalAsset;
    }

    public void setTotalAsset(String totalAsset) {
        this.totalAsset = totalAsset;
    }

    public String getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(String totalIncome) {
        this.totalIncome = totalIncome;
    }

    public String getMonthIncome() {
        return monthIncome;
    }

    public void setMonthIncome(String monthIncome) {
        this.monthIncome = monthIncome;
    }
}
