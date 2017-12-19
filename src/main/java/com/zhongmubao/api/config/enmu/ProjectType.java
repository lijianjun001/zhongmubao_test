package com.zhongmubao.api.config.enmu;

/**
 * 标类型
 *
 * @author 孙阿龙
 */
public enum ProjectType {
    /**
     * 普通()羊
     */
    NORMAL {
        @Override
        public String getName() {
            return "00";
        }
    },
    /**
     * 工厂(商铺)
     */
    SLAUGHTER {
        @Override
        public String getName() {
            return "03";
        }
    },
    /**
     * 新手120天(新手羊)
     */
    NEW_PEOPLE_120 {
        @Override
        public String getName() {
            return "06";
        }
    },
    /**
     * 新手7天(新手商铺)
     */
    NEW_PEOPLE_7 {
        @Override
        public String getName() {
            return "04";
        }
    };

    public abstract String getName();
}
