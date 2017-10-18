package com.zhongmubao.api.config.enmu;

/**
 * 短信验证码类型
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
    },
    /**
     * 赎回密码
     */
    REDEEM_PWD {
        @Override
        public String getName() {
            return "02";
        }
    },
    /**
     * 送手把肉提醒
     */
    SEND_SHOUBAROU_NOTICE {
        @Override
        public String getName() {
            return "03";
        }
    };

    public abstract String getName();
}
