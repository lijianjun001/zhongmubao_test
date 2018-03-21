package com.zhongmubao.api.dto.request.message;

import com.zhongmubao.api.dto.request.BaseRequest;

/**
 * 已读请求
 */
public class ReadRequestModel extends BaseRequest {

    public String objectId;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
}
