package com.zhongmubao.api.config.enmu;

/**
 * 小程序红包状态
 *
 * @author 米立林
 */

public enum RedEnvelopeStatus {
    /**
     * 进行中
     */
    UNDERWAY {
        @Override
        public String getName() {
            return "00";
        }
    },
    /**
     * 成功
     */
    SUCCESS {
        @Override
        public String getName() {
            return "01";
        }
    },
    /**
     * 失败
     */
    FAILED {
        @Override
        public String getName() {
            return "02";
        }
    },
    /**
     * 不能参与
     */
    NOT_JOIN {
        @Override
        public String getName() {
            return "03";
        }
    },
    /**
     * 红包已打开
     */
    OPENED {
        @Override
        public String getName() {
            return "04";
        }
    };

    public abstract String getName();
}
