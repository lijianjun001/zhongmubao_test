package com.zhongmubao.api.service.impl.buySheep;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

public class MyCookieJar implements CookieJar {

    private String account;
    public MyCookieJar(String account) {
        this.account=account;
    }


    private static Map<String,List<Cookie>> cookies=new HashMap<>();

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        MyCookieJar.cookies.put(account,cookies);
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        return cookies.get(account) != null ? cookies.get(account) : new ArrayList<>();
    }
}
