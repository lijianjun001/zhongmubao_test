package com.zhongmubao.api.config.enmu;

/**
 * 客户消息类型
 *
 * @author 米立林
 */

public enum CustomerMessageType {
    /**
     * 开标计划
     */
    OPEN_PROJECT {
        @Override
        public String getName() {
            return "00";
        }
    },
    /**
     * 系统消息
     */
    SYSTEM_MESSAGE {
        @Override
        public String getName() {
            return "01";
        }
    },
    /**
     * 个人消息
     */
    PERSON_MESSAGE {
        @Override
        public String getName() {
            return "02";
        }
    };

    public abstract String getName();
}
