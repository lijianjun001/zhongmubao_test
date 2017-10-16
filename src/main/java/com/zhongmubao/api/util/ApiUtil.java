package com.zhongmubao.api.util;

import com.zhongmubao.api.config.Constants;

public class ApiUtil {
    public static String formatImg(String img) {
        if (img.startsWith("http")) {
            return img;
        }
        return Constants.UPLOAD_URL + img;
    }
}
