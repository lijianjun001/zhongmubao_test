package com.zhongmubao.api.dto.request.system;

import com.zhongmubao.api.dto.request.BaseRequest;

/**
 * 分享信息请求实体
 *
 * @author 米立林
 */
public class ShareInfoRequestModel extends BaseRequest {
    private String toType;
    private String name;

    public String getToType() {
        return toType;
    }

    public void setToType(String toType) {
        this.toType = toType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
