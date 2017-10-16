package com.zhongmubao.api.config;

import com.zhongmubao.api.config.enmu.SignGiftType;
import com.zhongmubao.api.dto.SignGift;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 常量
 */
public class Constants {

    /**
     * 新手活动Id
     */
    public static final String UPLOAD_URL = "//s.emubao.com/upload/";

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
     * Cahce每日分享随机数
     */
    public static final String CACHE_SHARE_RANDOM_KEY = "ShareRandom";

    /**
     * Cache省市区
     */
    public static final String CACHE_SYSTEM_DISTRICT_KEY = "SystemDistrict";

    /**
     * Cache客户等级
     */
    public static final String CACHE_CUSTOMER_LEVEL_KEY = "CustomerLevel";

    //endregion

    /**
     * 在栏羊只
     */
    public static final List<String> SHEEP_IN_THE_BAR_STATE = Arrays.asList("01", "02", "03", "04");

    /**
     * 在栏羊只赎回中羊只和已赎回羊只
     */
    public static final List<String> SHEEP_IN_THE_BAR_STATE_AND_REDEMING_ANDREDEMED = Arrays.asList("01", "02", "03", "04", "05");

    /**
     * 新手活动Id
     */
    public static final int Activity_ID_XIN_SHOU = 12;

    /**
     * 每日分享红包过期时间（天）
     */
    public static final int DAY_SHARE_REDPACKAGE_EXP_DAY = 30;

    /**
     * 每月最多分享天数
     */
    public static final int MONTH_MAX_SHARE = 31;

    /***
     * 延时卡延时的天数
     */
    public static final  int DELAYED_CARD_DAY= 7;

    /**
     * 签到礼物
     */
    public static final List<SignGift> SIGN_GIFT_LIST = Arrays.asList(
            new SignGift(0, "红包", 1, SignGiftType.RED_PACKAGE),
            new SignGift(1, "神秘礼品", 1, SignGiftType.SECRET_GIFT),
            new SignGift(2, "延时卡", 1, SignGiftType.DELAYED_CARD),
            new SignGift(3, "延时卡", 2, SignGiftType.DELAYED_CARD),
            new SignGift(4, "延时卡", 3, SignGiftType.DELAYED_CARD),
            new SignGift(5, "合并卡", 1, SignGiftType.MERGE_CARD),
            new SignGift(6, "合并卡", 2, SignGiftType.MERGE_CARD),
            new SignGift(7, "合并卡", 3, SignGiftType.MERGE_CARD),
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
    public static final String LOCK_SIGN_KEY="LOCK_SIGN_";
    //endregion
}