package com.zhongmubao.api.components.hf.request.enmu;

/**
 * 发送短信模版类型
 *
 * @author 孙阿龙
 */
public enum HfSendSmsTempType {
    /**
     * 原绑定卡
     */
    O {
        @Override
        public String getName() {
            return "O";
        }
    },
    /**
     * 新绑定卡
     */
    N {
        @Override
        public String getName() {
            return "N";
        }
    };

    public abstract String getName();
}
