package com.zhongmubao.api.config;

public class SmsTemplate {

    /**
     * 重置密码模板
     */
    public static String resetPasswordTemplate = "验证码:{0},有效期为30分钟，为保证您的账户安全，请勿将此验证短信转发他人.";
    /**
     * 重置赎回密码模板
     */
    public static String resetRedeemPasswordTemplate = "泄露验证码有资金被盗风险!赎回密码验证码: {0}，打死都不能告诉别人哦!";
}
