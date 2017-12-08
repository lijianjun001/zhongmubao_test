package com.zhongmubao.api.dto.request;

/**
 * 请求基类
 *
 * @author 孙阿龙
 */
public class BaseRequest {

    /**
     * 请求版本号
     */
    private String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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
