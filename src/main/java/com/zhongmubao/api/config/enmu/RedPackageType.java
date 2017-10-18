package com.zhongmubao.api.config.enmu;

public enum RedPackageType {
    /**
     * 每日分享
     */
    DAY_SHARE {
        @Override
        public String getName() {
            return "02";
        }
    },
    /**
     * 合并卡合并的
     */
    MERGE_CARD {
        @Override
        public String getName() {
            return "07";
        }
    };

    public abstract String getName();
}
