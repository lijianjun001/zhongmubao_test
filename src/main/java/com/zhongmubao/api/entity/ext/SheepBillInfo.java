package com.zhongmubao.api.entity.ext;

/**
 * 购羊账单信息
 *
 * @author 米立林
 */
public class SheepBillInfo {
    /**
     * 标题
     */
    private String title;

    /**
     * 羊标类型
     */
    private String type;

    /**
     * 周期
     */
    private int period;

    /**
     * 购羊总金额
     */
    private double totalAmount;

    /**
     * 购羊总数量
     */
    private int totalCount;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
