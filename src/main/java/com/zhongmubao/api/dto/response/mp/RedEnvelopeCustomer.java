package com.zhongmubao.api.dto.response.mp;

/**
 * 小程序红包邀请
 *
 * @author 米立林
 */
public class RedEnvelopeCustomer {
    private String name;
    private String photo;
    private String price;

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
