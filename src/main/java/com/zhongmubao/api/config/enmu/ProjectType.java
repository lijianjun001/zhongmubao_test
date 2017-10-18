package com.zhongmubao.api.config.enmu;

public enum ProjectType {
    /**
     * 普通
     */
    NORMAL {
        @Override
        public String getName() {
            return "00";
        }
    },
    /**
     * 工厂
     */
    SLAUGHTER {
        @Override
        public String getName() {
            return "03";
        }
    },
    /**
     * 新手120天
     */
    NEW_PEOPLE_120 {
        @Override
        public String getName() {
            return "06";
        }
    },
    /**
     * 新手7天
     */
    NEW_PEOPLE_7 {
        @Override
        public String getName() {
            return "04";
        }
    };

    public abstract String getName();
}
