package com.zhongmubao.api.util;

import java.util.Random;

/**
 * 随机数
 *
 * @author 孙阿龙
 */
public class MathUtil {
    /**
     * 生成一定范围的随机数
     * @param  beginNum 最小数
     * @param endNum 最大数
     * @return 一个随机数
     */
    public static int random(int beginNum, int endNum) {
        Random rand = new Random();
        return rand.nextInt(endNum - beginNum + 1) + beginNum;
    }
}
