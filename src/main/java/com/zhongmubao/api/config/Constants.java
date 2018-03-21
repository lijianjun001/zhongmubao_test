package com.zhongmubao.api.config;

import com.zhongmubao.api.config.enmu.RedPackageType;
import com.zhongmubao.api.config.enmu.SignGiftType;
import com.zhongmubao.api.config.entity.SignGift;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 常量
 *
 * @author 孙阿龙
 */
public class Constants {
    /**
     * 客户默认头像
     */
    public static final String CATTLEMAN = "牧场主";

    /**
     * 客户默认头像
     */
    public static final String DEFAULT_PHOTO = "http://www.emubao.com/images/emubao-logo.png";

    /**
     * 上传地址
     */
    public static final String UPLOAD_URL = "https://s.emubao.com/upload/";

    /**
     * 密码加密URL
     * 正式环境：http://10.44.141.61:8016
     * 测试环境：http://192.168.31.200:8007
     */
    public static final String PASSWORD_ENCRYPT_URL = "http://10.44.141.61:8016/api/main/Encrypt";

    /**
     * CRYPTLIBENCRYPT_URL
     */
    public static final String CRYPTLIBENCRYPT_URL = "http://10.44.141.61:8016/api/main/CryptLibEncrypt";

    /**
     * 分享获取ImgUrl
     */
    public static final String SHARE_IMG_URL = "http://10.44.141.61:8016/api/main/SignImg";

    /**
     * 每页显示条数
     */
    public static final int PAGE_SIZE = 20;

    /**
     * 存储当前登录用户id的字段名
     */
    public static final String CURRENT_USER_ID = "CURRENT_USER_ID";

    /**
     * token有效期（小时）
     */
    public static final int TOKEN_EXPIRES_HOUR = 72;

    /**
     * 存放Authorization的header字段
     */
    public static final String AUTHORIZATION = "Authorization";

    /**
     * 存放PLATFORM的header字段
     */
    public static final String PLATFORM = "Platform";

    /**
     * 已支付
     */
    public static final String PAID_STATE = "01";

    /**
     * 未支付
     */
    public static final String NO_PAY_STATE = "00";

    //region CacheKey
    /**
     * SheepVendorList的Key
     */
    public static final String CACHE_SHEEP_VENDOR_LIST_KEY = "CACHE_SHEEP_VENDOR_LIST_KEY";

    /**
     * Cache新手标是否显示
     */
    public static final String CACHE_NEW_PEOPLE_PROJECT_IS_SHOW_KEY = "CACHE_NEW_PEOPLE_PROJECT_IS_SHOW_KEY";

    /**
     * Cache新手标是否显示
     */
    public static final String CACHE_CUSTOMER_IS_SHARE_KEY = "CACHE_CUSTOMER_IS_SHARE_KEY";

    /**
     * Cache每日分享随机数
     */
    public static final String CACHE_SHARE_RANDOM_KEY = "ShareRandom";

    /**
     * Cache 双十一送过话费
     */
    public static final String SINGLESDAY = "SinglesDay";
    /**
     * Cache 个人中心列表
     */
    public static final String PERSONALCENTER = "PersonalCenter";
    /**
     * Cache 实名显示
     */
    public static final String REALNAMETYPE = "RealNameType";
    /**
     * Cache省市区
     */
    public static final String CACHE_SYSTEM_DISTRICT_KEY = "SystemDistrict";

    /**
     * 客户等级表名
     */
    public static final String CACHE_CUSTOMER_LEVEL_TABLE = "CustomerLevel";
    /**
     * RedisCache客户等级
     */
    public static final String CACHE_CUSTOMER_LEVEL_KEY = "CustomerLevel_RedisCache";

    /**
     * Cache牧场监控
     */
    public static final String CACHE_MONITOR_KEY = "SystemMonitor";

    /**
     * Cache购羊提醒类型
     */
    public static final String CACHE_REMIND_TYPE_KEY = "remindNotifyType";

    /**
     * Cache购羊提醒周期
     */
    public static final String CACHE_REMIND_CYCLE_KEY = "remindNotifyCycle";

    //endregion

    /**
     * 在栏羊只
     */
    public static final List<String> SHEEP_IN_THE_BAR_STATE = Arrays.asList("01", "02", "03", "04");
    /**
     * 赎回羊只
     */
    public static final List<String> REDEMING = Collections.singletonList("05");

    /**
     * 在栏羊只赎回中羊只和已赎回羊只
     */
    public static final List<String> SHEEP_IN_THE_BAR_STATE_AND_REDEMING_ANDREDEMED = Arrays.asList("01", "02", "03", "04", "05");

    /**
     * 新手活动Id
     */
    public static final int ACTIVITY_ID_XIN_SHOU = 12;

    /**
     * 每日分享红包过期时间（天）
     */
    public static final int DAY_SHARE_REDPACKAGE_EXP_DAY = 60;

    /**
     * 每月最多分享天数
     */
    public static final int MONTH_MAX_SHARE = 31;

    /***
     * 延时卡延时的天数
     */
    public static final int DELAYED_CARD_DAY = 7;

    /**
     * 签到礼物
     */
    public static final List<SignGift> SIGN_GIFT_LIST = Arrays.asList(
            new SignGift(0, "红包", 1, SignGiftType.RED_PACKAGE),
            new SignGift(1, "神秘礼品", 1, SignGiftType.SECRET_GIFT),
            new SignGift(2, "延时卡", 1, SignGiftType.DELAYED_CARD),
            new SignGift(3, "延时卡", 2, SignGiftType.DELAYED_CARD),
            new SignGift(5, "合并卡", 1, SignGiftType.MERGE_CARD),
            new SignGift(6, "合并卡", 2, SignGiftType.MERGE_CARD),
            new SignGift(8, "话费卡", 1, SignGiftType.TELEPHONE_CARD),
            new SignGift(9, "话费卡", 2, SignGiftType.TELEPHONE_CARD),
            new SignGift(8, "话费卡", 1, SignGiftType.TELEPHONE_CARD),
            new SignGift(9, "话费卡", 2, SignGiftType.TELEPHONE_CARD),
            new SignGift(8, "话费卡", 1, SignGiftType.TELEPHONE_CARD),
            new SignGift(9, "话费卡", 2, SignGiftType.TELEPHONE_CARD)
    );

    /**
     * 120天新手标过期时间
     */
    public static final int EXP_120_NEW_PROJECT = 30;

    /**
     * 7天新手标过期时间
     */
    public static final int EXP_7_NEW_PROJECT = 30;

    //region redis 锁
    /**
     * 签到lock锁
     */
    public static final String LOCK_SIGN_KEY = "LOCK_SIGN_";
    //endregion

    /**
     * 日期格式
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 日期格式
     */
    public static final String DATE_FORMAT_CHINA = "yyyy年MM月dd日";

    /**
     * 日期时间格式
     */
    public static final String DATETIME_FORMAT_CHINA = "yyyy年MM月dd日 HH:mm:ss";

    /**
     * 日期格式
     */
    public static final String DATE_FORMAT_DOT = "yyyy.MM.dd";

    /**
     * 日期时分秒格式
     */
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期时分秒格式
     */
    public static final String DATE_TIME_FORMAT_DOT = "yyyy.MM.dd HH:mm:ss";

    /**
     * 时分格式
     */
    public static final String TIME_HOUR_MINUTE_FORMAT = "HH:mm";

    /**
     * 金额格式（两位小数点）
     */
    public static final String PRICE_FORMAT = "0.00";

    /**
     * 上传路径
     */
    public static final String UPLOAD_ADDRESS = "http://s.emubao.com/upload/";
    /**
     * 资源路径
     */
    public static final String RESOURES_ADDRESS = "http://s.emubao.com/weixin/";
    /**
     * 资源路径
     */
    public static final String RESOURES_ADDRESS_IMAGES = "http://s.emubao.com/weixin/images/";


    /**
     * 红包类型中文名
     *
     * @param type 红包类型
     * @return
     */
    public static String redpackettypestr(String type) {
        String typeStr;
        switch (type) {
            case "00":
                typeStr = "新人红包";
                break;
            case "01":
                typeStr = "春节红包";
                break;
            case "02":
                typeStr = "分享红包";
                break;
            case "03":
                typeStr = "每日分享";
                break;
            case "04":
                typeStr = "买羊红包";
                break;
            case "05":
                typeStr = "推荐红包";
                break;
            case "06":
                typeStr = "注册红包";
                break;
            case "07":
                typeStr = "合并红包";
                break;
            case "08":
                typeStr = "赠送红包";
                break;
            case "09":
                typeStr = "生日红包";
                break;
            case "10":
                typeStr = "推荐好友购羊红包";
                break;
            case "11":
                typeStr = "推荐好友红包";
                break;
            case "12":
                typeStr = "羊肉节红包";
                break;
            default:
                typeStr = "拼手气红包";
                break;
        }

        return typeStr;
    }

    /**
     * domain占位符
     */
    public static final String DOMAIN_PLACEHOLDER = "{domain}";

    /**
     * RESOURCE占位符
     */
    public static final String RESOURCE = "{resource}";

    /**
     * sign占位符
     */
    public static final String SIGN_PLACEHOLDER = "{code}";

    /**
     * name占位符
     */
    public static final String TITLE_PLACEHOLDER = "{name}";

    /**
     * 反斜杠
     */
    public static final String BACKSLASH = "/";

    /**
     * 空字符串
     */
    public static final String STRING_EMPTY = "";

    /**
     * 常量“置顶”
     */
    public static final String STRING_TOP = "置顶";

    /**
     * 常量“新”
     */
    public static final String STRING_NEW = "新";

    /**
     * 常量“本周”
     */
    public static final String STRING_THIS_WEEK = "本周";

    /**
     * 常量“历史”
     */
    public static final String STRING_HISTORY = "历史";

    /**
     * http常量
     */
    public static final String HTTP = "http";
}
