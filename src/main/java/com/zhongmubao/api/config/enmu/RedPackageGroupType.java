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
        public double getName() {
            return 0;
        }
    },
    /**
     * 2元红包
     */
    TWO {
        @Override
        public double getName() {
            return 2;
        }
    },
    /**
     * 5元红包
     */
    FIVE {
        @Override
        public double getName() {
            return 5;
        }
    },
    /**
     * 8元红包
     */
    EIGHT {
        @Override
        public double getName() {
            return 8;
        }
    },
    /**
     * 20元红包
     */
    TWENTY {
        @Override
        public double getName() {
            return 20;
        }
    };

    public abstract double getName();
}
