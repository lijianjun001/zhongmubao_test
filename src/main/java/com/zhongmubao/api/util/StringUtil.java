package com.zhongmubao.api.util;

public class StringUtil {
    /**
     * 使用java正则表达式去掉多余的.与0
     *
     * @param s 字符串
     * @return 新的字符串
     */
    public static String subZeroAndDot(String s) {
        if (s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }

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
