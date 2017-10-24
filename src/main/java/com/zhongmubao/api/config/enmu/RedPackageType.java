package com.zhongmubao.api.config.enmu;

/**
 * 红包类型
 *
 * @author 孙阿龙
 */
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
