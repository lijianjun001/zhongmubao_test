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
    },
    /**
     * 已参与
     */
    JOINED {
        @Override
        public String getName() {
            return "05";
        }
    },
    /**
     * 参与成功
     */
    JOINSUCCESS {
        @Override
        public String getName() {
            return "06";
        }
    },
    /**
     * 等待打开
     */
    WAITOPENED {
        @Override
        public String getName() {
            return "07";
        }
    },
    /**
     * 老用户不能参与瓜分活动，只能发起
     */
    OLDCUSTOMERNOTJOIN {
        @Override
        public String getName() {
            return "08";
        }
    };

    public abstract String getName();
}
