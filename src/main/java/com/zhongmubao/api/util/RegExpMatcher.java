package com.zhongmubao.api.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式匹配
 *
 * @author 米立林
 */
public class RegExpMatcher {

    // 判断手机正则
    private static String regExp = "^[1][0-9]{10}$";

    /**
     * 正则校验手机号
     *
     * @param mobile 手机号
     * @return ture or false
     */
    public static boolean MatcherMobile(String mobile) {
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(mobile);
        return matcher.find();
    }
}
