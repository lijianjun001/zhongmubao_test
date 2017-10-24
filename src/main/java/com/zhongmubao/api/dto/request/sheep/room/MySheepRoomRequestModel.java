package com.zhongmubao.api.dto.request.sheep.room;

public class MySheepRoomRequestModel {
    public MySheepRoomRequestModel() {
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    private int pageIndex;

    private String projectType;
}
