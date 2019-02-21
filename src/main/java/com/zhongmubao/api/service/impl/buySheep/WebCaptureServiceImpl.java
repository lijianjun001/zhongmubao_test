package com.zhongmubao.api.service.impl.buySheep;

import com.google.gson.Gson;
import com.zhongmubao.api.service.WebCaptureService;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class WebCaptureServiceImpl implements WebCaptureService {
    @Override
    public void autoBuySheep(String tel, String pass) {

        if(AccountManager.getInstance().getUserInfoMap().containsKey(tel)){
            return;
        }
        OkHttpClient.Builder mOkHttpClientBuilder = new OkHttpClient.Builder();
        mOkHttpClientBuilder.cookieJar(new MyCookieJar(tel));
        final OkHttpClient mOkHttpClient = mOkHttpClientBuilder.build();

        FormBody.Builder formBodyBuilder = new FormBody.Builder()
                .add("account", tel)
                .add("r", Math.random() + "")
                .add("password", pass);
        RequestBody requestBody = formBodyBuilder.build();

        Request request = new Request.Builder()
                .url("https://wap.emubao.com/Account/Login?")
//                .headers(requestHeaders)
                .post(requestBody)
                .build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {

                System.out.println(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody responseBody = response.body();
                InputStream inputStream = responseBody.byteStream();
                String str = StringUtils.getString(inputStream);
                AccountInfo accountInfo =  new Gson().fromJson(str, AccountInfo.class);
                AccountManager.getInstance().saveAccountInfo(accountInfo);
            }
        });
    }


    private Headers buildHeader() {
        Headers.Builder headersBuilder = new Headers.Builder()
                .add("Accept", "text/html, application/xhtml+xml, image/jxr, */*")
                .add("Accept-Language", "zh-Hans-CN,zh-Hans,en-US,en;q=0.5")
                .add("Connection", "Keep-Alive");
        Headers requestHeaders = headersBuilder.build();
        return requestHeaders;
    }
}
