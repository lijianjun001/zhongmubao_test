package com.zhongmubao.api.dto.response.sheep;

import com.zhongmubao.api.dto.response.customer.AutoRedeemModel;

/**
 * 可赎回
 *
 * @author 米立林
 */
public class SheepRedeemableViewModel {
    private int projectId;
    private int count;
    private String price;
    private String redPrice;
    private String redeemTime;
    private String title;
    /**
     * 羊标类型
     */
    private String type;
    /**
     * 用户自动赎回信息
     */
    private AutoRedeemModel customerInfo;

    public SheepRedeemableViewModel(int projectId, int count, String price, String redPrice, String redeemTime, String title, String type, AutoRedeemModel customerInfo) {
        this.projectId = projectId;
        this.count = count;
        this.price = price;
        this.redPrice = redPrice;
        this.redeemTime = redeemTime;
        this.title = title;
        this.type = type;
        this.customerInfo = customerInfo;
    }

    public SheepRedeemableViewModel() {
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRedPrice() {
        return redPrice;
    }

    public void setRedPrice(String redPrice) {
        this.redPrice = redPrice;
    }

    public String getRedeemTime() {
        return redeemTime;
    }

    public void setRedeemTime(String redeemTime) {
        this.redeemTime = redeemTime;
    }

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

    public AutoRedeemModel getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(AutoRedeemModel customerInfo) {
        this.customerInfo = customerInfo;
    }
}
