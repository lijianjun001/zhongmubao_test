package com.zhongmubao.api.dto.Response.customer;

/**
 * 在栏羊只收益
 * @author 米立林
 */
public class InBarSheepIncomeViewModel {
    /**
     * 羊标标题
     */
    private String projectTitle;

    /**
     * 羊只数量
     */
    private int count;

    /**
     * 入栏-赎回时间
     */
    private String dateInterval;

    /**
     * 本金
     */
    private String benJin;

    /**
     * 预计收益
     */
    private String enIncome;

    public InBarSheepIncomeViewModel(String projectTitle, int count, String dateInterval, String benJin, String enIncome) {
        this.projectTitle = projectTitle;
        this.count = count;
        this.dateInterval = dateInterval;
        this.benJin = benJin;
        this.enIncome = enIncome;
    }

    public InBarSheepIncomeViewModel() {
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDateInterval() {
        return dateInterval;
    }

    public void setDateInterval(String dateInterval) {
        this.dateInterval = dateInterval;
    }

    public String getBenJin() {
        return benJin;
    }

    public void setBenJin(String benJin) {
        this.benJin = benJin;
    }

    public String getEnIncome() {
        return enIncome;
    }

    public void setEnIncome(String enIncome) {
        this.enIncome = enIncome;
    }
}
