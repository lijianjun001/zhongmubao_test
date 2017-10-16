package com.zhongmubao.api.dto.Request;

public class PhoneFareRequestModel {
    public String getPhone() {
        return phone;
    }

    public int getPrice() {
        return price;
    }

    public String getGiftId() {
        return giftId;
    }

    public void setGiftId(String giftId) {
        this.giftId = giftId;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    private String giftId;
    private String phone;
    private int price;
}
