package com.zhongmubao.api.util;

public class DoubleUtil {
    /**
     * 浮点精确小数点后N位
     * @param d 浮点
     * @param formatStr 格式:0.00
     * @return 被格式化的浮点，字符串
     */
    public static String toFixed(double d, String formatStr) {
        java.text.DecimalFormat df = new java.text.DecimalFormat(formatStr);
        return df.format(d);
    }
}
