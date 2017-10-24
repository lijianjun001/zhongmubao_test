package com.zhongmubao.api.config.enmu;

/**
 * 活动状态
 *
 * @author 孙阿龙
 */
public enum ActivityState {
    /**
     * 已审核
     */
    Audited {
        @Override
        public String getName() {
            return "11";
        }
    };

    public abstract String getName();
}
