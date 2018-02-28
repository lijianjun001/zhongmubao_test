package com.zhongmubao.api.config.enmu;

/**
 * 分享成功类型
 *
 * @author 米立林
 */
public enum ShareSuccessType {
    /**
     * 文字提示
     */
    TEXT {
        @Override
        public String getName() {
            return "00";
        }
    },
    /**
     * 链接跳转
     */
    REDIRECT {
        @Override
        public String getName() {
            return "01";
        }
    },
    /**
     * 自由处理
     */
    FREEDOM {
        @Override
        public String getName() {
            return "02";
        }
    };

    public abstract String getName();
}
