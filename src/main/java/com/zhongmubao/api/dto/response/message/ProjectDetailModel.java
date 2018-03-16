package com.zhongmubao.api.dto.response.message;

import com.google.gson.annotations.SerializedName;

/**
 * @author 米立林
 */
public class ProjectDetailModel {
    /**
     * 星期
     */
    @SerializedName("Day")
    private String day;

    /**
     * 牧场名字
     */
    @SerializedName("Pasture")
    private String pasture;

    /**
     * 只数
     */
    @SerializedName("Amount")
    private String amount;

    /**
     * 预期年化
     */
    @SerializedName("Profit")
    private String profit;

    /**
     * 周期
     */
    @SerializedName("Period")
    private String period;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getPasture() {
        return pasture;
    }

    public void setPasture(String pasture) {
        this.pasture = pasture;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
