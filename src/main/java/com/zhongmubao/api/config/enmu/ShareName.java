package com.zhongmubao.api.config.enmu;

/**
 * 分享名称
 * @author 米立林
 */

public enum ShareName {
    /**
     * 贺卡
     */
    TOYEAR {
        @Override
        public String getName() {
            return "toyear";
        }
    },
    /**
     * 2017年底图片制作活动
     */
    YEAREND2017 {
        @Override
        public String getName() {
            return "yearend2017";
        }
    },
    /**
     * 每日分享
     */
    SHAREDAY {
        @Override
        public String getName() {
            return "shareday";
        }
    };

    public abstract String getName();
}
