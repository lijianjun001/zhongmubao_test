package com.zhongmubao.api.config.enmu;

/**
 * 购羊周期状态
 */
public enum NotifyCycleState {
    /**
     * 只一次
     */
    JUST_ONCE {
        @Override
        public String getName() {
            return "00";
        }
    },
    /**
     * 每天
     */
    EVERYDAY {
        @Override
        public String getName() {
            return "01";
        }
    },
    /**
     * 每周
     */
    WEEKLY {
        @Override
        public String getName() {
            return "02";
        }
    },
    /**
     * 每月
     */
    MONTHLY {
        @Override
        public String getName() {
            return "03";
        }
    };

    public abstract String getName();
}
