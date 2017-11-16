package com.zhongmubao.api.dto.request.sheep.room;

import com.zhongmubao.api.dto.request.BaseRequest;

/**
 * 我的羊圈
 * @author xy
 * @date 2017/10/24
 */
public class SheepRoomRequestModel extends BaseRequest {
    public SheepRoomRequestModel() {
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
