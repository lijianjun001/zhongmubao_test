package com.zhongmubao.api.dto.request.system;

import com.zhongmubao.api.dto.request.BaseRequest;

/**
 * 分享信息请求实体
 *
 * @author 米立林
 */
public class ShareInfoRequestModel extends BaseRequest {
    private String name;
    private String param;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}
