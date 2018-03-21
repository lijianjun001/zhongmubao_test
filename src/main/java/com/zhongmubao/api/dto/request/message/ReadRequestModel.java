package com.zhongmubao.api.dto.request.message;

import com.zhongmubao.api.dto.request.BaseRequest;

/**
 * 已读请求
 * @author 孙阿龙
 */
public class ReadRequestModel extends BaseRequest {

    public String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
