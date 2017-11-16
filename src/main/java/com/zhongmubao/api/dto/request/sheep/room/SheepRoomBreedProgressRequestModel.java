package com.zhongmubao.api.dto.request.sheep.room;

import com.zhongmubao.api.dto.request.BaseRequest;

/**
 * 我的羊圈 养殖流程
 *
 * @author xy
 */
public class SheepRoomBreedProgressRequestModel extends BaseRequest {
    public SheepRoomBreedProgressRequestModel() {
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    private int projectId;
}
