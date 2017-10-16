package com.zhongmubao.api.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SerializeUtil {
    /***
     * 序列化对象
     * @param o
     * @return
     */
    public static String serialize(Object o) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(o);
        } catch (Exception ex) {
        }
        return "";
    }

    /***
     * 反序列化对象
     * @param jsonStr
     * @param _class
     * @param <T>
     * @return
     */
    public static <T> T deSerialize(String jsonStr, Class<T> _class) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(jsonStr, _class);
        } catch (Exception ex) {
        }
        return null;
    }
}
