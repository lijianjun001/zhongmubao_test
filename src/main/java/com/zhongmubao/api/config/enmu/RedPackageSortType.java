package com.zhongmubao.api.config.enmu;

/**
 * 排序方式
 * @author 米立林
 */

public enum RedPackageSortType {
    /**
     * 按金额排序
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
