package com.zhongmubao.api.dto.response.my.transaction;

/**
 * 购标信息
 *
 * @author 米立林
 */
public class SheepProjectInfoModel {

    public SheepProjectInfoModel() {
    }

    public SheepProjectInfoModel(String amount, String title, String period, String count) {
        this.amount = amount;
        this.title = title;
        this.period = period;
        this.count = count;
    }

    /**
     * 购羊金额
     */
    private String amount;

    /**
     * 标标题
     */
    private String title;

    /**
     * 养殖周期
     */
    private String period;

    /**
     * 购羊数量
     */
    private String count;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
