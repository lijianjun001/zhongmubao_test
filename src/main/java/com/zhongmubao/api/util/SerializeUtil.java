package com.zhongmubao.api.util;

import com.google.gson.Gson;

/**
 * 序列化类
 *
 * @author 孙阿龙
 */
public class SerializeUtil {
    /***
     * 序列化对象
     * @param o 对象
     * @return 序列化后的字符串
     */
    public static String serialize(Object o){
        try {
            return new Gson().toJson(o);
        } catch (Exception ignored){}
        return "";
    }

    /***
     * 反序列化对象
     * @param jsonStr json字符串
     * @param  c 类
     * @param <T> 一个对象
     * @return 对象
     */
    public static <T> T deSerialize(String jsonStr, Class<T> c){
        try {
            return new Gson().fromJson(jsonStr, c);
        } catch (Exception ignored){

        }
        return null;
    }
}
