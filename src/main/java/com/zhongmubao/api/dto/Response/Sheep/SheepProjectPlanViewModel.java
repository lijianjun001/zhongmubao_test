package com.zhongmubao.api.dto.Response.Sheep;

public class SheepProjectPlanViewModel {

    private int id;
    private String title;
    private String info;
    private String createTime;

    public SheepProjectPlanViewModel() {

    }

    public SheepProjectPlanViewModel(int id, String title, String info, String createTime) {
        this.id = id;
        this.title = title;
        this.info = info;
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
