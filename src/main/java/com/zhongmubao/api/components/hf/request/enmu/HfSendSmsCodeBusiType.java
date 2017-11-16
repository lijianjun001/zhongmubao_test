package com.zhongmubao.api.components.hf.request.enmu;

/**
 * 短信验证码业务类型枚举
 *
 * @author 孙阿龙
 */
public enum HfSendSmsCodeBusiType {
    /**
     * 开户
     */
    USER_REGISTET {
        @Override
        public String getName() {
            return "user_register";
        }
    },
    /**
     * 充值
     */
    RECHARGE {
        @Override
        public String getName() {
            return "recharge";
        }
    },
    /**
     * 换卡
     */
    REBIND {
        @Override
        public String getName() {
            return "rebind";
        }
    };

    public abstract String getName();
}
