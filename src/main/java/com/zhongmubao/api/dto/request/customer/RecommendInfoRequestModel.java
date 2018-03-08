package com.zhongmubao.api.dto.request.customer;

import com.zhongmubao.api.dto.request.BaseRequest;

/**
 * 客户信息请求实体
 *
 * @author 米立林
 */
public class RecommendInfoRequestModel  extends BaseRequest {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
