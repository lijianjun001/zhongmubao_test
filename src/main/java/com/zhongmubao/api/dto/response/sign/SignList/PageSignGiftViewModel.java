package com.zhongmubao.api.dto.response.sign.SignList;

public class PageSignGiftViewModel {
    public PageSignGiftViewModel() {
    }

    public PageSignGiftViewModel(String id, String title, int count, String status, String type, String catated) {
        this.id = id;
        this.title = title;
        this.count = count;
        this.status = status;
        this.type = type;
        this.catated = catated;
    }

    private String id;
    private String title;
    private int count;
    private String status;
    private String type;
    private String catated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCatated() {
        return catated;
    }

    public void setCatated(String catated) {
        this.catated = catated;
    }
}
