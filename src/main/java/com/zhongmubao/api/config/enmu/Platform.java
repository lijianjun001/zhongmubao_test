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
        public String getName() {
            return "00";
        }
    },
    /**
     * 安卓
     */
    ANDROID {
        public String getName() {
            return "01";
        }
    },
    /**
     * 苹果
     */
    IOS {
        public String getName() {
            return "02";
        }
    },
    /**
     * WAP
     */
    WAP {
        public String getName() {
            return "03";
        }
    };

    public abstract String getName();
}
