package com.zhongmubao.api.config.enmu;

/**
 * 分享内容类型
 *
 * @author 米立林
 */

public enum ShareContentType {
    /**
     * 链接
     */
    LINK {
        @Override
        public String getName() {
            return "00";
        }
    },
    /**
     * 图片
     */
    PHOTO {
        @Override
        public String getName() {
            return "01";
        }
    };

    public abstract String getName();
}
