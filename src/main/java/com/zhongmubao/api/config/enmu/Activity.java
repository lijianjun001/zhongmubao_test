package com.zhongmubao.api.config.enmu;

public enum Activity {
    /**
     * 神秘礼物
     */
    SECRET_GIFT_ID {
        public int getName() {
            return 19;
        }
    },
    /**
     * 话费卡
     */
    TELEPHONE_CARD_ID {
        public int getName() {
            return 20;
        }
    };

    public abstract int getName();
}
