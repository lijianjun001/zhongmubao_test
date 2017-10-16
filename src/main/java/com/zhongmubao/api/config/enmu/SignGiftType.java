package com.zhongmubao.api.config.enmu;

public enum SignGiftType {
    /**
     * 红包
     */
    RED_PACKAGE {
        public String getName() {
            return "01";
        }
    },
    /**
     * 神秘礼物
     */
    SECRET_GIFT {
        public String getName() {
            return "02";
        }
    },
    /**
     * 延时卡
     */
    DELAYED_CARD {
        public String getName() {
            return "03";
        }
    },
    /**
     * 合并卡
     */
    MERGE_CARD {
        public String getName() {
            return "04";
        }
    },
    /**
     * 话费卡
     */
    FARE_CARD {
        public String getName() {
            return "05";
        }
    };

    public abstract String getName();
}
