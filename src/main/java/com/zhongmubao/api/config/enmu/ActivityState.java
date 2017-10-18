package com.zhongmubao.api.config.enmu;

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
