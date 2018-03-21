package com.zhongmubao.api.config.enmu;

/**
 * 客户消息标签
 *
 * @author 米立林
 */
public enum CustomerMessageTips {
    TOP {
        @Override
        public String getName() {
            return "置顶";
        }

        @Override
        public String getColor() {
            return "#FE7A40";
        }

        @Override
        public int getKey() {
            return 1;
        }
    },
    NEW {
        @Override
        public String getName() {
            return "新";
        }

        @Override
        public String getColor() {
            return "#FFC200";
        }

        @Override
        public int getKey() {
            return 2;
        }
    }, SURPRISE {
        @Override
        public String getName() {
            return "有惊喜";
        }

        @Override
        public String getColor() {
            return "#2DC090";
        }

        @Override
        public int getKey() {
            return 3;
        }
    }, UPDATE {
        @Override
        public String getName() {
            return "有更新";
        }

        @Override
        public String getColor() {
            return "#5F9DFA";
        }

        @Override
        public int getKey() {
            return 4;
        }
    }, HISTORY {
        @Override
        public String getName() {
            return "历史";
        }

        @Override
        public String getColor() {
            return "#C7C4C4";
        }

        @Override
        public int getKey() {
            return 5;
        }
    }, CURRENT_WEEK {
        @Override
        public String getName() {
            return "本周";
        }

        @Override
        public String getColor() {
            return "#FFC200";
        }

        @Override
        public int getKey() {
            return 6;
        }
    }, HOME_PAGE {
        @Override
        public String getName() {
            return "首页";
        }

        @Override
        public String getColor() {
            return "";
        }

        @Override
        public int getKey() {
            return 7;
        }
    }, DEFAULT {
        @Override
        public String getName() {
            return "默认";
        }

        @Override
        public String getColor() {
            return "";
        }

        @Override
        public int getKey() {
            return 99;
        }
    };

    public abstract int getKey();

    public abstract String getName();

    public abstract String getColor();

    /**
     * 获取标签名
     *
     * @param key key值
     * @return String
     */
    public static String formartName(String key) {
        if (key.equals(TOP.getKey())) {
            return TOP.getName();
        } else if (key.equals(NEW.getKey())) {
            return NEW.getName();
        } else if (key.equals(SURPRISE.getKey())) {
            return SURPRISE.getName();
        } else if (key.equals(UPDATE.getKey())) {
            return UPDATE.getName();
        } else if (key.equals(HISTORY.getKey())) {
            return HISTORY.getName();
        } else if (key.equals(CURRENT_WEEK.getKey())) {
            return CURRENT_WEEK.getName();
        } else if (key.equals(HOME_PAGE.getKey())) {
            return HOME_PAGE.getName();
        } else if (key.equals(DEFAULT.getKey())) {
            return DEFAULT.getName();
        } else {
            return "";
        }
    }

    /**
     * 获取标签背景颜色
     *
     * @param key key值
     * @return String
     */
    public static String formartColor(String key) {
        if (key.equals(TOP.getKey())) {
            return TOP.getColor();
        } else if (key.equals(NEW.getKey())) {
            return NEW.getColor();
        } else if (key.equals(SURPRISE.getKey())) {
            return SURPRISE.getColor();
        } else if (key.equals(UPDATE.getKey())) {
            return UPDATE.getColor();
        } else if (key.equals(HISTORY.getKey())) {
            return HISTORY.getColor();
        } else if (key.equals(CURRENT_WEEK.getKey())) {
            return CURRENT_WEEK.getColor();
        } else if (key.equals(HOME_PAGE.getKey())) {
            return HOME_PAGE.getColor();
        } else if (key.equals(DEFAULT.getKey())) {
            return DEFAULT.getColor();
        } else {
            return "";
        }
    }
}
