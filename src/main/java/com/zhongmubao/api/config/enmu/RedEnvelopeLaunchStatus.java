package com.zhongmubao.api.config.enmu;

/***
 * 分享红包发起状态
 * @author 孙阿龙
 */
public enum RedEnvelopeLaunchStatus {
    /**
     * 不能参与
     */
    NOIN {
        @Override
        public String getName() {
            return "00";
        }
    },
    /**
     * 进行中
     */
    ING {
        @Override
        public String getName() {
            return "01";
        }
    };

    public abstract String getName();
}
