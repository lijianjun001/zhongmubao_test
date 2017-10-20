package com.zhongmubao.api.config.enmu;

/**
 * 羊标类型周期
 * @author 米立林
 */
public enum SheepProjectPeriod {
    /**
     * 120天周期
     */
    PERIOD_120 {
        @Override
        public int getName() {
            return 120;
        }
    },
    /**
     * 240天周期
     */
    PERIOD_240 {
        @Override
        public int getName() {
            return 240;
        }
    };

    public abstract int getName();
}
