package com.zhongmubao.api.config.enmu;

/**
 * 红包状态
 *
 * @author 米立林
 */

public enum RedPackageState {
    /**
     * 未使用
     */
    UNUSED {
        @Override
        public String getName() {
            return "00";
        }
    },
    /**
     * 已使用（已变现）
     */
    USRD {
        @Override
        public String getName() {
            return "01";
        }
    },
    /**
     * 已过期
     */
    EXPIRED {
        @Override
        public String getName() {
            return "02";
        }
    };

    public abstract String getName();
}
