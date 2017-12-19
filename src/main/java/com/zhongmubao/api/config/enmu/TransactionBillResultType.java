package com.zhongmubao.api.config.enmu;

/**
 * 交易月账单结果类型
 *
 * @author 米立林 2017-12-18
 */
public enum TransactionBillResultType {
    /**
     * 当月没有交易
     */
    NO_TRANSACTION {
        @Override
        public String getName() {
            return "00";
        }
    },
    /**
     * 没有购羊，但有收益 or 充值提现
     */
    NO_BUY_SHEEP {
        @Override
        public String getName() {
            return "01";
        }
    },
    /**
     * 正常统计数据
     */
    NORMAL {
        @Override
        public String getName() {
            return "02";
        }
    };

    public abstract String getName();
}
