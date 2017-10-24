package com.zhongmubao.api.dto.response.sign;

/**
 * 话费卡
 * @author 米立林
 */
public class SignGiftCharge {
    private String price;
    private String phone;

    public SignGiftCharge(String price, String phone) {
        this.price = price;
        this.phone = phone;
    }

    public SignGiftCharge() {
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
