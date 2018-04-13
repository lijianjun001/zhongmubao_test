package com.zhongmubao.api.config.enmu;

/**
 * 红包类型
 *
 * @author 孙阿龙
 */
public enum RedPackageType {
    /**
     * 新人红包
     */
    NEW_PEOPLE {
        @Override
        public String getName() {
            return "00";
        }
    },
    /**
     * 春节红包
     */
    OLD_PEOPLE {
        @Override
        public String getName() {
            return "01";
        }
    },
    /**
     * 每日分享
     */
    DAY_SHARE {
        @Override
        public String getName() {
            return "02";
        }
    },
    /**
     * 分享红包
     */
    SHARE {
        @Override
        public String getName() {
            return "03";
        }
    },
    /**
     * 买羊
     */
    BUY_SHEEP {
        @Override
        public String getName() {
            return "04";
        }
    },
    /**
     * 推荐
     */
    RECOMMEND {
        @Override
        public String getName() {
            return "05";
        }
    },
    /**
     * 注册
     */
    REGISTER {
        @Override
        public String getName() {
            return "06";
        }
    },
    /**
     * 合并卡合并的
     */
    MERGE_CARD {
        @Override
        public String getName() {
            return "07";
        }
    },
    /**
     * 赠送
     */
    GIVE {
        @Override
        public String getName() {
            return "08";
        }
    },
    /**
     * 生日红包
     */
    BIRTHDAY {
        @Override
        public String getName() {
            return "09";
        }
    },
    /**
     * 推荐好友累计购羊50只
     */
    BUY_SHEEP_50 {
        @Override
        public String getName() {
            return "10";
        }
    },
    /**
     * 成功推荐3位好友并买羊
     */
    RECOMMEND_3 {
        @Override
        public String getName() {
            return "11";
        }
    },
    /**
     * 2017羊肉节活动
     */
    MUTTON_SECTION {
        @Override
        public String getName() {
            return "12";
        }
    },
    /**
     * 微信公众号提供赠送
     */
    WE_CHAT_OFFERING{
        @Override
        public String getName() {
            return "13";
        }
    };

    public abstract String getName();
}
