package com.zhongmubao.api.config.enmu;

/**
 * 购羊通知提醒状态
 * @author 米立林
 */
public enum NotifyRemindState {
    /**
     * 打开
     */
    ON {
        @Override
        public String getName() {
            return "00";
        }
    },
    /**
     * 关闭
     */
    OFF {
        @Override
        public String getName() {
            return "01";
        }
    };

    public abstract String getName();
}
