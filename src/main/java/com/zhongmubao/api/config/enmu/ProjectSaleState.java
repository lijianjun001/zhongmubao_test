package com.zhongmubao.api.config.enmu;

public enum ProjectSaleState {
    /**
     * 抢羊
     */
    BUY_SHEEP {
        public String getName() {
            return "00";
        }
    },
    /**
     * 预售
     */
    PRE_SHEEP {
        public String getName() {
            return "01";
        }
    },
    /**
     * 捡漏
     */
    LEAK_SHEEP {
        public String getName() {
            return "02";
        }
    },
    /**
     * 售罄
     */
    SOLD_SHEEP {
        public String getName() {
            return "03";
        }
    };

    public abstract String getName();
}