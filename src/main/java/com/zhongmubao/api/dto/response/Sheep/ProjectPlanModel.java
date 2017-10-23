package com.zhongmubao.api.dto.response.Sheep;

public class ProjectPlanModel {
    public  ProjectPlanModel(String title,String info){
        this.title = title;
        this.info = info;
    }

    public String getTitle() {
        return title;
    }

    public String getInfo() {
        return info;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String title;

    public String info;
}
