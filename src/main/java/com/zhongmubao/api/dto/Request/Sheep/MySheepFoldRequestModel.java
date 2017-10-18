package com.zhongmubao.api.dto.Request.Sheep;

public class MySheepFoldRequestModel {
    public  MySheepFoldRequestModel(){}

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
    private int pageIndex;

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }
    private String projectType;


}
