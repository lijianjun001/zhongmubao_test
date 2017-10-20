package com.zhongmubao.api.dto.Response.customer;

/**
 * 七日年化收益率
 */
public class SevenDaysIncome {
    /**
     * 日期
     */
    private String xDays;

    /**
     * 收益率
     */
    private String yincome;

    public SevenDaysIncome(String xDays, String yincome) {
        this.xDays = xDays;
        this.yincome = yincome;
    }

    public SevenDaysIncome() {
    }

    public String getxDays() {
        return xDays;
    }

    public void setxDays(String xDays) {
        this.xDays = xDays;
    }

    public String getYincome() {
        return yincome;
    }

    public void setYincome(String yincome) {
        this.yincome = yincome;
    }
}
