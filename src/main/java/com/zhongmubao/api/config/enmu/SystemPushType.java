package com.zhongmubao.api.config.enmu;

/**
 * 系统消息推送类型定义
 *
 * @author 米立林 2017-10-13
 */
public enum SystemPushType {
    /**
     * 短信
     */
    SMS {
        @Override
        public String getName() {
            return "01";
        }
    },
    /**
     * 苹果
     */
    IOS {
        @Override
        public String getName() {
            return "02";
        }
    },
    /**
     * 安卓
     */
    ANDROID {
        @Override
        public String getName() {
            return "03";
        }
    },
    /**
     * 微信
     */
    WEIXIN {
        @Override
        public String getName() {
            return "04";
        }
    };

    public abstract String getName();
}