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

    /**
     * pfx密码
     */
    @Value("#{pValue['sys.zhongmubao.hf.cfca_mpfx_file_pwd']}")
    private String cfca_mpfx_file_pwd;
    public static String CFCA_MPFX_FILE_PWD;

    @Value("#{pValue['sys.zhongmubao.hf.root']}")
    private String root;
    public static String ROOT;

    @Value("#{pValue['sys.zhongmubao.hf.cfca_mpfx_file_name']}")
    private String cfca_mpfx_file_name;
    public static String CFCA_MPFX_FILE_NAME;

    @Value("#{pValue['sys.zhongmubao.hf.cfca_irust_cer_name']}")
    private String cfca_irust_cer_name;
    public static String CFCA_TRUST_CER_NAME;

    @Value("#{pValue['sys.zhongmubao.hf.cfca_mer_id']}")
    private String cfca_mer_id;
    public static String CFCA_MER_ID;

    @Value("#{pValue['sys.zhongmubao.hf.bos_cfca_mer_id']}")
    private String bos_cfca_mer_id;
    public static String BOS_CFCA_MER_ID;

    @PostConstruct
    public void init() {
        MIDDLE = this.middle;
        APP_KEY = this.appKey;
        WEI_XIN = this.weiXin;
        ANDROID = this.android;
        IOS = this.ios;
        WAP = this.wap;
        RESOURCES = this.resources;
        CFCA_MPFX_FILE_PWD = this.cfca_mpfx_file_pwd;
        ROOT = this.root;
        CFCA_MPFX_FILE_NAME = this.cfca_mpfx_file_name;
        CFCA_TRUST_CER_NAME = this.cfca_irust_cer_name;
        CFCA_MER_ID = this.cfca_mer_id;
        BOS_CFCA_MER_ID = this.bos_cfca_mer_id;
    }
}
