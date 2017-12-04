package com.zhongmubao.api.dto.request.sheep.room;

import com.zhongmubao.api.dto.request.BaseRequest;

/**
 * 我的羊圈 订单弹框
 *
 * @author xy
 */
public class SheepRoomOrdersRequestModel extends BaseRequest {
    public SheepRoomOrdersRequestModel() {
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    private int projectId;
}
