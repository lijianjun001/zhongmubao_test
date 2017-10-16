package com.zhongmubao.api.config.enmu;

public enum SystemPushType {
    /**
     * 短信
     */
    SMS {
        public String getName() {
            return "01";
        }
    },
    /**
     * 微信
     */
    WEIXIN {
        public String getName() {
            return "04";
        }
    };

    public abstract String getName();
}