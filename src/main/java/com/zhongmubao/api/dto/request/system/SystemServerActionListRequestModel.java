package com.zhongmubao.api.dto.request.system;

/**
 * SystemServerActionListRequestModel
 *
 * @author 孙阿龙
 */
public class SystemServerActionListRequestModel {
    public String getParentObjectId() {
        return parentObjectId;
    }

    public void setParentObjectId(String parentObjectId) {
        this.parentObjectId = parentObjectId;
    }

    private String parentObjectId;
}
