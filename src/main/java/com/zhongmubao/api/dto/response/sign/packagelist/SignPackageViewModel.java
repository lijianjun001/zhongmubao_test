package com.zhongmubao.api.dto.response.sign.packagelist;

/**
 * @author 孙阿龙
 */
public class SignPackageViewModel {
    public SignPackageViewModel() {
    }

    public SignPackageViewModel(int id, String price, String expTime,int surplusDay) {
        this.id = id;
        this.price = price;
        this.expTime = expTime;
        this.surplusDay = surplusDay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    private int id;
    private String price;
    private String expTime;
    private int surplusDay;

    public int getSurplusDay() {
        return surplusDay;
    }

    public void setSurplusDay(int surplusDay) {
        this.surplusDay = surplusDay;
    }
}
