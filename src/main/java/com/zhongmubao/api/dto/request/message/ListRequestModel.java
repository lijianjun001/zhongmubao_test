package com.zhongmubao.api.dto.request.message;

import com.zhongmubao.api.dto.request.BaseRequest;

/**
 * 消息中心请求
 *
 * @author 米立林
 */
public class ListRequestModel extends BaseRequest {
    /**
     * 页码
     */
    private int pageIndex;
    /**
     * 消息类型（开标信息、系统消息、个人消息）
     */
    private String type;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
