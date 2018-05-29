package com.zhongmubao.api.config.system;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author 孙阿龙
 * 读取配置字段
 */
@Configuration
public class ConfigurationFields {

    public static final byte OK_STATUS = 1;

    /**
     * AppKey
     */
    @Value("#{pValue['sys.zhongmubao.appKey']}")
    private String appKey;

    public static String APP_KEY;

    /**
     * AppKey
     */
    @Value("#{pValue['sys.zhongmubao.domain.weiXin']}")
    private String weiXin;
    public static String WEI_XIN;

    /**
     * 安卓域名
     */
    @Value("#{pValue['sys.zhongmubao.domain.android']}")
    private String android;
    public static String ANDROID;

    /**
     * ios域名
     */
    @Value("#{pValue['sys.zhongmubao.domain.ios']}")
    private String ios;
    public static String IOS;

    /**
     * Wap域名
     */
    @Value("#{pValue['sys.zhongmubao.domain.wap']}")
    private String wap;
    public static String WAP;

    /**
     * 资源域名
     */
    @Value("#{pValue['sys.zhongmubao.domain.resources']}")
    private String resources;
    public static String RESOURCES;


    /**
     * 中转网站域名
     */
    @Value("#{pValue['sys.zhongmubao.domain.middle']}")
    private String middle;
    public static String MIDDLE;

    @PostConstruct
    public void init() {

        APP_KEY = this.appKey;
        WEI_XIN = this.weiXin;
        ANDROID = this.android;
        IOS = this.ios;
        WAP = this.wap;
        RESOURCES = this.resources;
        MIDDLE = this.middle;
    }
}
