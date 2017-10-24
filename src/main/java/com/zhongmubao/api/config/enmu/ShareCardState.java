package com.zhongmubao.api.config.enmu;

/**
 * 卡状态
 *
 * @author 孙阿龙
 */
public enum ShareCardState {
    /**
     * 未领取
     */
    UNCLAIMED {
        @Override
        public String getName() {
            return "00";
        }
    },
    /**
     * 已领取
     */
    RECEIVED {
        @Override
        public String getName() {
            return "01";
        }
    };

    public abstract String getName();
}
