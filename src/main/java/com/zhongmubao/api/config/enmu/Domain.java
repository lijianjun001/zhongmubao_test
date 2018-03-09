package com.zhongmubao.api.config.enmu;

/**
 * 客户端域名
 * @author xy
 */
public enum Domain {
    /**
     * WEIXIN
     */
    WEIXIN(Platform.WEIXIN.getName(), "http://m.emubao.com/"),
    /**
     * ANDROID
     */
    ANDROID(Platform.ANDROID.getName(), "https://android2.emubao.com/"),
    /**
     * IOS
     */
    IOS(Platform.IOS.getName(), "https://ios2.emubao.com"),
    /**
     * WAP
     */
    WAP(Platform.WAP.getName(), "https://wap.emubao.com/"),
    /**
     * 资源文件
     */
    RESOURCES(Platform.RESOURCES.getName(), "https://s.emubao.com/");

    private String platform;
    private String domain;

    Domain(String platform, String domain) {
        this.platform = platform;
        this.domain = domain;
    }

    public String getPlatform() {
        return this.platform;
    }
    public String getDomain() {
        return this.domain;
    }
}
