package com.zhongmubao.api.dto.request.sheep.room;

import com.zhongmubao.api.dto.request.BaseRequest;

/**
 * 我的羊圈 赎回弹框
 *
 * @author xy
 */
public class SheepRoomRedeemableRequestModel extends BaseRequest {
    public SheepRoomRedeemableRequestModel() {
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    private int projectId;
}
