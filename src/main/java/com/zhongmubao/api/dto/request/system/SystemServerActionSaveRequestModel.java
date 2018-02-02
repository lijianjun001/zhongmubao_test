package com.zhongmubao.api.dto.request.system;

import com.zhongmubao.api.dto.request.BaseRequest;

/**
 * SystemServerActionSaveModel请求model
 *
 * @author 孙阿龙
 */
public class SystemServerActionSaveRequestModel extends BaseRequest {

    private String objectId;
    private String name;
    private String parentObjectId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentObjectId() {
        return parentObjectId;
    }

    public void setParentObjectId(String parentObjectId) {
        this.parentObjectId = parentObjectId;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

}
