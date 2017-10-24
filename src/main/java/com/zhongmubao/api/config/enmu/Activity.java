package com.zhongmubao.api.config.enmu;

/**
 * 活动
 *
 * @author 孙阿龙
 */
public enum Activity {
    /**
     * 神秘礼物
     */
    SECRET_GIFT_ID {
        @Override
        public int getName() {
            return 19;
        }
    },
    /**
     * 话费卡
     */
    TELEPHONE_CARD_ID {
        @Override
        public int getName() {
            return 20;
        }
    };

    public abstract int getName();
}
