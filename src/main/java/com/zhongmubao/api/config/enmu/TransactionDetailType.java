package com.zhongmubao.api.config.enmu;

/**
 * 交易明细类型
 * 00 充值 01 赎回 02 提现 03 投资/买羊
 *
 * @author 米立林
 */
public enum TransactionDetailType {
    /**
     * 全部
     */
    ALL {
        @Override
        public String getName() {
            return "";
        }
    },
    /**
     * 充值
     */
    RECHARGE {
        @Override
        public String getName() {
            return "00";
        }
    },
    /**
     * 赎回
     */
    REDEEM {
        @Override
        public String getName() {
            return "01";
        }
    },
    /**
     * 提现
     */
    WITHDRAW {
        @Override
        public String getName() {
            return "02";
        }
    },
    /**
     * 买羊
     */
    BUYSHEEP {
        @Override
        public String getName() {
            return "03";
        }
    },
    /**
     * 赎回红包
     */
    REDEEM_RED {
        @Override
        public String getName() {
            return "04";
        }
    };

    public abstract String getName();
}
