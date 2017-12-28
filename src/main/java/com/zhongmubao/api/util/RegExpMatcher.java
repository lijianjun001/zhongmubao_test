package com.zhongmubao.api.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式匹配
 * @author 米立林
 */
public class RegExpMatcher {

    /**
     * 正则校验手机号
     * @param mobile 手机号
     * @return true or false
     */
    public static boolean matcherMobile(String mobile){
        /*
          手机正则
         */
        String regExp = "^[1][0-9]{10}$";
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(mobile);
        return matcher.find();
    }

    /**
     * 去掉金额后面无效的0
     * @param price 金额
     * @return String
     */
    public static String matcherPrice(String price){
        if(price.indexOf(".") > 0){
            //正则表达式
            //去掉后面无用的零
            price = price.replaceAll("0+?$", "");
            //如小数点后面全是零则去掉小数点
            price = price.replaceAll("[.]$", "");
        }
        return price;
    }
}
