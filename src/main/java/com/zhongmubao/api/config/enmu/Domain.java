package com.zhongmubao.api.config.enmu;

/**
 * 客户端域名
 * @author xy
 */
public enum Domain {
    /**
     * WEIXIN
     */
    WEIXIN(Platform.WEIXIN.getName(), "http://192.168.31.210:46018/"),
    /**
     * ANDROID
     */
    ANDROID(Platform.ANDROID.getName(), "http://192.168.31.200:8111/"),
    /**
     * IOS
     */
    IOS(Platform.IOS.getName(), "http://192.168.31.200:8111/"),
    /**
     * WAP
     */
    WAP(Platform.WAP.getName(), "http://192.168.31.200:46018/"),
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
