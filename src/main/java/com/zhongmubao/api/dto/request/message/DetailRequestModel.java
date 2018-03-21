package com.zhongmubao.api.dto.request.message;

import com.zhongmubao.api.dto.request.BaseRequest;

/**
 * 消息详情请求
 *
 * @author 米立林
 */
public class DetailRequestModel extends BaseRequest {
    /**
     * 消息主键
     */
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
