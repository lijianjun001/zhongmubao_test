package com.zhongmubao.api.config.enmu;

/**
 * 养殖流程类型
 * @author 米立林 2017-10-16
 */
public enum SheepStageType {
    /**
     * 羊只
     */
    SHEEP {
        @Override
        public int getName() {
            return 1;
        }
    },
    /**
     * 商铺
     */
    SHOP {
        @Override
        public int getName() {
            return 2;
        }
    };

    public abstract int getName();
}
