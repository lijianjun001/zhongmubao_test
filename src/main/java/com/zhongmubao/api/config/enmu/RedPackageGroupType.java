package com.zhongmubao.api.config.enmu;

/**
 * 红包分组类型
 *
 * @author 米立林
 */
public enum RedPackageGroupType {
    /**
     * 零钱红包
     */
    OTHER {
        @Override
        public String getName() {
            return "00";
        }
    },
    /**
     * 2元红包
     */
    TWO {
        @Override
        public String getName() {
            return "01";
        }
    },
    /**
     * 5元红包
     */
    FIVE {
        @Override
        public String getName() {
            return "02";
        }
    },
    /**
     * 8元红包
     */
    EIGHT {
        @Override
        public String getName() {
            return "03";
        }
    };

    public abstract String getName();
}
