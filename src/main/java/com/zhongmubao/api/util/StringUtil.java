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
}
