package com.zhongmubao.api.config.enmu;

/**
 * 签到礼物类型
 *
 * @author 孙阿龙
 */
public enum SignGiftType {
    /**
     * 红包
     */
    RED_PACKAGE {
        @Override
        public String getName() {
            return "01";
        }
    },
    /**
     * 神秘礼物
     */
    SECRET_GIFT {
        @Override
        public String getName() {
            return "02";
        }
    },
    /**
     * 延时卡
     */
    DELAYED_CARD {
        @Override
        public String getName() {
            return "03";
        }
    },
    /**
     * 合并卡
     */
    MERGE_CARD {
        @Override
        public String getName() {
            return "04";
        }
    },
    /**
     * 话费充值卡
     */
    TELEPHONE_CARD {
        @Override
        public String getName() {
            return "05";
        }
    };

    public abstract String getName();
}
