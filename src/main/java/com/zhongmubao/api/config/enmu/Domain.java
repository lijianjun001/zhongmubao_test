package com.zhongmubao.api.config.enmu;


public enum Domain {
    WEIXIN(Platform.WEIXIN.getName(), "http://192.168.31.210:46018"),
    ANDROID(Platform.ANDROID.getName(), "http://192.168.31.200:8111"),
    IOS(Platform.IOS.getName(), "http://192.168.31.200:8111"),
    WAP(Platform.WAP.getName(), "http://192.168.31.200:46018")
    ;
    private String platform;
    private String domain;

    private Domain(String platform, String domain) {
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
