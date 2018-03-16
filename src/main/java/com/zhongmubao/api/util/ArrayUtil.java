package com.zhongmubao.api.util;

import java.util.List;

/**
 * 集合工具类
 *
 * @author 米立林
 */
public class ArrayUtil<T> {

    /***
     * 判断集合不为null并有数据
     * @param list 集合对象
     * @return true 空 ，false非空
     */
    public static <T> boolean isNull(List<T> list) {
        if (null != list && list.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
