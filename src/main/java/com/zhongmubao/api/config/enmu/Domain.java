package com.zhongmubao.api.config.enmu;

import com.zhongmubao.api.config.system.ConfigurationFields;

/**
 * 客户端域名
 * @author xy
 */
public enum Domain {
    /**
     * WEIXIN
     */
    WEIXIN(Platform.WEIXIN.getName(), ConfigurationFields.WEI_XIN),
    /**
     * ANDROID
     */
    ANDROID(Platform.ANDROID.getName(), ConfigurationFields.ANDROID),
    /**
     * IOS
     */
    IOS(Platform.IOS.getName(), ConfigurationFields.IOS),
    /**
     * WAP
     */
    WAP(Platform.WAP.getName(), ConfigurationFields.WAP),
    /**
     * 资源文件
     */
    RESOURCES(Platform.RESOURCES.getName(), ConfigurationFields.RESOURCES);

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
