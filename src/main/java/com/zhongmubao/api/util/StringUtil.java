package com.zhongmubao.api.util;

/**
 * 字符串辅助类
 *
 * @author 孙阿龙
 */
public class StringUtil {

    /***
     * 空或者Null返回默认字符串
     * @param str 被判断的字符串
     * @return 字符串
     */
    public static String nullOrEmptyReturnDefault(String str) {
        return isNullOrEmpty(str) ? "" : str;
    }

    /***
     * 判断字符串不为null并且不为空
     * @param str 被校验的字符串
     * @return true 空 ，false非空
     */
    public static boolean isNullOrEmpty(String str) {
        return str == null || "".equals(str);
    }

    /***
     * 字符串"周一、周二、周三、周四、周五、周六、周日"转换阿拉伯数字
     * @param week 周"一、二、三、四、五、六、日"
     * @return true 空 ，false非空
     */
    public static int convartWeekToNumber(String week) {
        switch (week) {
            case "周一":
                return 1;
            case "周二":
                return 2;
            case "周三":
                return 3;
            case "周四":
                return 4;
            case "周五":
                return 5;
            case "周六":
                return 6;
            case "周日":
                return 7;
            default:
                return 0;
        }
    }
}
