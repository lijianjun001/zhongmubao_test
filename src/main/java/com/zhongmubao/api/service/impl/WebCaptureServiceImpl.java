package com.zhongmubao.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.google.gson.Gson;
import com.zhongmubao.api.service.WebCaptureService;
import com.zhongmubao.api.test.CreateResultModel;
import com.zhongmubao.api.test.MyCookieJar;
import com.zhongmubao.api.test.ProjectModel;
import com.zhongmubao.api.test.ResultModel;
import okhttp3.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Set;

@Service
public class WebCaptureServiceImpl implements WebCaptureService {
    private WebClient webClient;
    private Set<Cookie> cookies;
    private StringBuffer cookieStr;

    @Override
    public void autoBuySheep(String tel, String pass) {
        webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setJavaScriptEnabled(true);

        webClient.getOptions().setActiveXNative(true);
        webClient.getOptions().setTimeout(1000 * 1000);
        webClient.getOptions().setCssEnabled(false);
        //3 启动客户端重定向
        webClient.getOptions().setRedirectEnabled(true);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false); //js运行错误时，是否抛出异常
        webClient.setJavaScriptTimeout(1000 * 1000);
        //8设置cookie
        webClient.getCookieManager().setCookiesEnabled(true);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());//设置支持AJAX
        try {
            if (cookies == null) {
                HtmlPage htmlPage = webClient.getPage("http://192.168.31.200:46018/Account/Login?");
                HtmlInput htmlInput = (HtmlInput) htmlPage.getElementById("txtAccount");
                htmlInput.setDefaultValue("13260213625");
                HtmlInput password = (HtmlInput) htmlPage.getElementById("txtPassword");
                password.setDefaultValue("123456");

                HtmlElement domElement = (HtmlElement) htmlPage.getElementById("btn_log");
                HtmlPage result_page = domElement.click();// 模拟点击
                // 等待JS驱动dom完成获得还原后的网页
                webClient.waitForBackgroundJavaScript(10000);
//            System.out.println("autoBuySheep" + result_page.asXml());

                cookies = webClient.getCookieManager().getCookies();
                cookieStr = new StringBuffer();
                for (Cookie c : cookies) {
//                    System.out.println("autoBuySheep" + c.getName() + ":" + c.getValue());
                    cookieStr.append(c.getName()).append("=").append(c.getValue()).append(";");
                }
            }
//            getMainPage();
//            getPersonalCenter();
            postData();
            getProducts();
            webClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getMainPage() throws IOException {
        HtmlPage mainPage = webClient.getPage("http://192.168.31.200:46018/Emubao/WebApp#/index");
        for (Cookie c : cookies) {
            webClient.addCookie(c.toString(), new URL("http://192.168.31.200:46018"), null);
        }
//        System.out.println("getMainPage" + mainPage.asXml());

        HtmlElement domElement = (HtmlElement) mainPage.getDocumentElement().getElementsByAttribute("a", "href", "/Customer/PersonalCenter");
        HtmlPage result_page = domElement.click();// 模拟点击
        // 等待JS驱动dom完成获得还原后的网页
        webClient.waitForBackgroundJavaScript(10000);
        System.out.println("PersonalCenter" + result_page.asXml());
    }

    private Headers buildHeader() {
        Headers.Builder headersBuilder = new Headers.Builder()
                .add("Accept", "text/html, application/xhtml+xml, image/jxr, */*")
                .add("Accept-Language", "zh-Hans-CN,zh-Hans,en-US,en;q=0.5")
                .add("Cookie", cookieStr.toString())
                .add("Connection", "Keep-Alive");
        Headers requestHeaders = headersBuilder.build();
        return requestHeaders;
    }


    public void getPersonalCenter() {
        OkHttpClient.Builder mOkHttpClientBuilder = new OkHttpClient.Builder();
        mOkHttpClientBuilder.cookieJar(new MyCookieJar());
        OkHttpClient mOkHttpClient = mOkHttpClientBuilder.build();
        Request request = new Request.Builder().url("http://192.168.31.200:46018/Customer/PersonalCenter").headers(buildHeader()).build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {


            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody responseBody = response.body();

                InputStream inputStream = responseBody.byteStream();
                String str = getString(inputStream);
                Document document = Jsoup.parse(str);
                System.out.println("PersonalCenter" + document.html());
            }
        });
    }

    public static String getString(InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;

        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }


    public void postData() {
        OkHttpClient.Builder mOkHttpClientBuilder = new OkHttpClient.Builder();
        final OkHttpClient mOkHttpClient = mOkHttpClientBuilder.build();

        FormBody.Builder formBodyBuilder = new FormBody.Builder()
                .add("r", Math.random() + "");
        RequestBody requestBody = formBodyBuilder.build();

        Request request = new Request.Builder()
                .url("http://192.168.31.200:46018//Customer/AccountCenter?")
                .headers(buildHeader())
                .post(requestBody)
                .build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody responseBody = response.body();

                InputStream inputStream = responseBody.byteStream();
                String str = getString(inputStream);
                System.out.println("postData" + str);
//                Document document = Jsoup.parse(str);
            }
        });
    }


    public void getProducts() {
        OkHttpClient.Builder mOkHttpClientBuilder = new OkHttpClient.Builder();
        final OkHttpClient mOkHttpClient = mOkHttpClientBuilder.build();

        FormBody.Builder formBodyBuilder = new FormBody.Builder()
                .add("r", Math.random() + "");
        RequestBody requestBody = formBodyBuilder.build();

        Request request = new Request.Builder()
                .url("http://192.168.31.200:46018/Sheep/ProjectListV2?")
                .headers(buildHeader())
                .post(requestBody)
                .build();

        mOkHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody responseBody = response.body();

                InputStream inputStream = responseBody.byteStream();
                String str = getString(inputStream);
                ResultModel resultModel = new Gson().fromJson(str, ResultModel.class);

                System.out.println("getProducts" + resultModel.getData().getList().toString());
//                Document document = Jsoup.parse(str);

                String id = resultModel.getData().getList().get(0).getId();
                createOrder(id);
            }
        });
    }


    public void createOrder(String projectId) {
        OkHttpClient.Builder mOkHttpClientBuilder = new OkHttpClient.Builder();
        final OkHttpClient mOkHttpClient = mOkHttpClientBuilder.build();

        FormBody.Builder formBodyBuilder = new FormBody.Builder()
                .add("r", Math.random() + "")
                .add("id", projectId)
                .add("libraryCount", 0 + "")
                .add("bouns", 0.00 + "")
                .add("redPacketList", 0 + "")
                .add("count", "1");
        RequestBody requestBody = formBodyBuilder.build();

        Request request = new Request.Builder()
                .url("http://192.168.31.200:46018/Sheep/CreateOrder?")
                .headers(buildHeader())
                .post(requestBody)
                .build();

        mOkHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody responseBody = response.body();

                InputStream inputStream = responseBody.byteStream();
                String str = getString(inputStream);
                System.out.println(str);
                CreateResultModel resultModel = new Gson().fromJson(str, CreateResultModel.class);

                if (resultModel.getResult() == 0) {
                    System.out.println("createOrder" + "成功");
                }

//                Document document = Jsoup.parse(str);
            }
        });
    }
}
