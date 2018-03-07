package com.zhongmubao.api.config.enmu;

/**
 * 短信验证码类型
 *
 * @author 米立林 2017-09-30
 */
public enum SmsType {
    /**
     * 验证
     */
    VERIFICATION {
        @Override
        public String getName() {
            return "00";
        }
    },
    /**
     * 通知
     */
    NOTICE {
        @Override
        public String getName() {
            return "01";
        }
    };

    public abstract String getName();
}
