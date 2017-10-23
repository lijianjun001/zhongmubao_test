package com.zhongmubao.api.dto.response.ext;

public class PageExtRedPackageViewModel {


    public PageExtRedPackageViewModel(String price, String expTime) {

        this.price = price;
        this.expTime = expTime;
    }

    private String price;
    private String expTime;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getExpTime() {
        return expTime;
    }

    public void setExpTime(String expTime) {
        this.expTime = expTime;
    }
}
