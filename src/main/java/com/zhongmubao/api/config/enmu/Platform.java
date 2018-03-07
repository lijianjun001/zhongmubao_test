package com.zhongmubao.api.config.enmu;

/**
 * 平台类型
 * 00-微信，01-Android，02-	IOS，03- WAP
 * @author 米立林 2017-10-17
 */
public enum Platform {
    /**
     * 微信
     */
    WEIXIN {
        @Override
        public String getName() {
            return "00";
        }
    },
    /**
     * 安卓
     */
    ANDROID {
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
     * WAP
     */
    WAP {
        @Override
        public String getName() {
            return "03";
        }
    },
    RESOURCES {
        @Override
        public String getName() {
            return "04";
        }
    };

    public abstract String getName();
}
