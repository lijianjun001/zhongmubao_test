package com.zhongmubao.api.dto.response.system;

/**
 * 小程序红包邀请
 *
 * @author Yekai
 */
public class RedEnvelopeCustomer {
    private int customerId;
    private String name;
    private String photo;
    private String price;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
