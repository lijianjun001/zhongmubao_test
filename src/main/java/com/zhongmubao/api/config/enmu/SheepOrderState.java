package com.zhongmubao.api.config.enmu;

public enum  SheepOrderState {
    /**
     * 00 未付款
     */
    NON_PAYMENT {
        @Override
        public String getName() {
            return "00";
        }
    },
    /**
     * 01 已付款
     */
    PAYMENTED {
        @Override
        public String getName() {
            return "01";
        }
    },
    /**
     * 02已入栏
     */
    HAS_INTOBAR {
        @Override
        public String getName() {
            return "02";
        }
    },
    /**
     * 03可赎回
     */
    REDEEMABLE {
        @Override
        public String getName() {
            return "03";
        }
    },
    /**
     * 04赎回中
     */
    REDEEMPTION {
        @Override
        public String getName() {
            return "04";
        }
    },
    /**
     * 05已赎回
     */
    REDEEMPTED {
        @Override
        public String getName() {
            return "04";
        }
    },
    /**
     * 06已取消
     */
    CANCELED {
        @Override
        public String getName() {
            return "04";
        }
    };

    public abstract String getName();
}
