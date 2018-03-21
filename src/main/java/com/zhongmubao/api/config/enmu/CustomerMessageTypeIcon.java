package com.zhongmubao.api.config.enmu;

/**
 * 消息类型
 *
 * @author 孙阿龙
 */
public enum CustomerMessageTypeIcon {
    PROJECT {
        @Override
        public String getValue() {
            return "https://s.emubao.com/weixin/images/news/news-plan.png";
        }

        @Override
        public String getKey() {
            return "00";
        }
    },
    BANKMESSAGE {
        @Override
        public String getValue() {
            return "https://s.emubao.com/weixin/images/news/news-blank.png";
        }

        @Override
        public String getKey() {
            return "01";
        }
    }, PERSONMESSAGE {
        @Override
        public String getValue() {
            return "https://s.emubao.com/weixin/images/news/news-person.png";
        }

        @Override
        public String getKey() {
            return "02";
        }
    }, PAPRPERMESSAGE {
        @Override
        public String getValue() {
            return "https://s.emubao.com/weixin/images/news/news-parper.png";
        }

        @Override
        public String getKey() {
            return "03";
        }
    }, GIFT {
        @Override
        public String getValue() {
            return "https://s.emubao.com/weixin/images/news/news-gift.png";
        }

        @Override
        public String getKey() {
            return "04";
        }
    };

    public abstract String getValue();

    public abstract String getKey();

    public static String formart(String key) {
        if (key.equals(PROJECT.getKey())) {
            return PROJECT.getValue();
        } else if (key.equals(BANKMESSAGE.getKey())) {
            return BANKMESSAGE.getValue();
        } else if (key.equals(PERSONMESSAGE.getKey())) {
            return PERSONMESSAGE.getValue();
        } else if (key.equals(PAPRPERMESSAGE.getKey())) {
            return PAPRPERMESSAGE.getValue();
        } else if (key.equals(GIFT.getKey())) {
            return GIFT.getValue();
        } else {
            return "";
        }
    }
}
