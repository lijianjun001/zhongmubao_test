package com.zhongmubao.api.util;

/**
 * 数字工具类
 *
 * @author 米立林
 */
public class NumberUtil {
    static String[] CHN_NUMBER = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
    static String[] CHN_UNIT = {"", "十", "百", "千"};
    static String[] CHN_UNIT_SECTION = {"", "万", "亿", "万亿"};

    /**
     * 阿拉伯数字转换为中文数字的核心算法实现。
     *
     * @param num 为需要转换为中文数字的阿拉伯数字，是无符号的整形数
     * @return
     */
    public static String numberToChina(int num) {
        StringBuffer returnStr = new StringBuffer();
        Boolean needZero = false;
        //节权位的位置
        int pos = 0;
        if (num == 0) {
            //如果num为0，进行特殊处理。
            returnStr.insert(0, CHN_NUMBER[0]);
        }
        while (num > 0) {
            int section = num % 10000;
            if (needZero) {
                returnStr.insert(0, CHN_NUMBER[0]);
            }
            String sectionToChn = sectionNumToChn(section);
            //判断是否需要节权位
            sectionToChn += (section != 0) ? CHN_UNIT_SECTION[pos] : CHN_UNIT_SECTION[0];
            returnStr.insert(0, sectionToChn);
            //判断section中的千位上是不是为零，若为零应该添加一个零。
            needZero = ((section < 1000 && section > 0) ? true : false);
            pos++;
            num = num / 10000;
        }
        return returnStr.toString();
    }

    /**
     * 将四位的section转换为中文数字
     *
     * @param section
     * @return
     */
    private static String sectionNumToChn(int section) {
        StringBuffer returnStr = new StringBuffer();
        //节权位的位置编号，0-3依次为个十百千;
        int unitPos = 0;

        Boolean zero = true;
        while (section > 0) {

            int v = (section % 10);
            if (v == 0) {
                if ((section == 0) || !zero) {
                    /*需要补0，zero的作用是确保对连续的多个0，只补一个中文零*/
                    zero = true;
                    returnStr.insert(0, CHN_NUMBER[v]);
                }
            } else {
                //至少有一个数字不是0
                zero = false;
                //数字v所对应的中文数字
                StringBuffer tempStr = new StringBuffer(CHN_NUMBER[v]);
                //数字v所对应的中文权位
                tempStr.append(CHN_UNIT[unitPos]);
                returnStr.insert(0, tempStr);
            }
            //移位
            unitPos++;
            section = section / 10;
        }
        return returnStr.toString();
    }
}
