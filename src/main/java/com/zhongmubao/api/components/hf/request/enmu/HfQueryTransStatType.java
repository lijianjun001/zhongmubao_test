package com.zhongmubao.api.components.hf.request.enmu;

/**
 * 交易状态类型
 *
 * @author 孙阿龙
 */
public enum HfQueryTransStatType {
    /**
     * 放款交易查询
     */
    LOANS {
        @Override
        public String getName() {
            return "LOANS";
        }
    },
    /**
     * 还款交易查询
     */
    REPAYMENT {
        @Override
        public String getName() {
            return "REPAYMENT";
        }
    },
    /**
     * 投标交易查询
     */
    TENDER {
        @Override
        public String getName() {
            return "TENDER";
        }
    },
    /**
     * 取现交易查询
     */
    CASH {
        @Override
        public String getName() {
            return "CASH";
        }
    },
    /**
     * 冻结解冻交易查询
     */
    FREEZE {
        @Override
        public String getName() {
            return "FREEZE";
        }
    };

    public abstract String getName();
}
