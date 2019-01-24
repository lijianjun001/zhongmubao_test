package com.zhongmubao.api.service.impl;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.zhongmubao.api.service.WebCaptureService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class WebCaptureServiceImpl implements WebCaptureService {
    private WebClient webClient;
    private Set<Cookie> cookies;

    @Override
    public void autoBuySheep(String tel, String pass) {
        webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setTimeout(10 * 1000);
        webClient.getOptions().setCssEnabled(false);
        //3 启动客户端重定向
        webClient.getOptions().setRedirectEnabled(true);
        webClient.getOptions().setThrowExceptionOnScriptError(false); //js运行错误时，是否抛出异常
        webClient.setJavaScriptTimeout(10 * 1000);
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
                Map<String, String> responseCookies = new HashMap<>();
                for (Cookie c : cookies) {
                    responseCookies.put(c.getName(), c.getValue());
                    System.out.println("autoBuySheep" + c.getName() + ":" + c.getValue());
                }
            }
            getMainPage();
            webClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getMainPage() throws IOException {
        HtmlPage mainPage = webClient.getPage("http://192.168.31.200:46018/Emubao/WebApp#/PastureProfit");
        for (Cookie c : cookies) {
            webClient.addCookie(c.toString(), new URL("http://192.168.31.200:46018"), null);
        }
        System.out.println("getMainPage" + mainPage.asXml());
    }
}
