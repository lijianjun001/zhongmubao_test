package com.zhongmubao.api.dto.request.my.center;

import com.zhongmubao.api.dto.request.BaseRequest;

/**
 * 个人中心 列表
 * @author xy
 */
public class PersonalCenterRequestModel extends BaseRequest{
    public PersonalCenterRequestModel() {
    }

    /**
     * 平台
     */
    private String platform;
    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
