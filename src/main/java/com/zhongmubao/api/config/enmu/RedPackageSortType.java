package com.zhongmubao.api.config.enmu;

public enum RedPackageSortType {
    /**
     * 按时间排序
     */
    Price {
        @Override
        public String getName() {
            return "Price";
        }
    },
    /**
     * 按到期时间排序
     */
    ExpTime {
        @Override
        public String getName() {
            return "ExpTime";
        }
    };

    public abstract String getName();
}
