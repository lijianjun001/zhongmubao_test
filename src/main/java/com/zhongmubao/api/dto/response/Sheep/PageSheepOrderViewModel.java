package com.zhongmubao.api.dto.response.Sheep;

public class PageSheepOrderViewModel {

    public PageSheepOrderViewModel(int id, int customerId, String code, int count, String totalAmount, String title, String name, String photo) {
        this.id = id;
        this.customerId = customerId;
        this.code = code;
        this.count = count;
        this.totalAmount = totalAmount;
        this.title = title;
        this.name = name;
        this.photo = photo;
    }

    private int id;
    private int customerId;
    private String code;
    private int count;
    private String totalAmount;
    private String title;
    private String name;
    private String photo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

}
