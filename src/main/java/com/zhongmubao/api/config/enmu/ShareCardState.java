package com.zhongmubao.api.config.enmu;

public enum ShareCardState {
    /**
     * 未领取
     */
    UNCLAIMED {
        public String getName() {
            return "00";
        }
    },
    /**
     * 已领取
     */
    RECEIVED {
        public String getName() {
            return "01";
        }
    };

    public abstract String getName();
}
