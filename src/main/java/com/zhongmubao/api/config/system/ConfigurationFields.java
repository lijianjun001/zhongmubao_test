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


    @PostConstruct
    public void init() {
        APP_KEY = this.appKey;
    }
}
