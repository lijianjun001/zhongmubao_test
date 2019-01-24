package com.zhongmubao.api.test;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;
import com.gargoylesoftware.htmlunit.util.Cookie;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class WebCaptureController {
    public static void main(String[] args) {

        try {
            WebClient webClient = new WebClient(BrowserVersion.CHROME);
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
            HtmlPage htmlPage = webClient.getPage("http://192.168.31.200:46018/Account/Login?");

            HtmlInput htmlInput = (HtmlInput) htmlPage.getElementById("txtAccount");
            htmlInput.setDefaultValue("13260213625");
            HtmlInput password = (HtmlInput) htmlPage.getElementById("txtPassword");
            password.setDefaultValue("123456");

            HtmlElement domElement = (HtmlElement) htmlPage.getElementById("btn_log");
            HtmlPage result_page = domElement.click();// 模拟点击
            // 等待JS驱动dom完成获得还原后的网页
            webClient.waitForBackgroundJavaScript(10000);
//            System.out.println("<<<<<<<<" + result_page.asXml());


            Set<Cookie> cookies = webClient.getCookieManager().getCookies();
            Map<String, String> responseCookies = new HashMap<>();
            for (Cookie c : cookies) {
                responseCookies.put(c.getName(), c.getValue());
                System.out.println("------" + c.getName() + ":" + c.getValue());
            }
            HtmlPage mainPage = webClient.getPage("http://192.168.31.200:46018/Emubao/WebApp#/PastureProfit");
            for (Cookie c : cookies) {
                webClient.addCookie(c.toString(),new URL("http://192.168.31.200:46018"),null);
            }
//
            System.out.println(">>>>>>" + mainPage.asXml());

//            HtmlPage htmlPage = webClient.getPage("https://www.baidu.com");
//            HtmlInput htmlInput = (HtmlInput) htmlPage.getElementById("kw");
//            htmlInput.setDefaultValue("android");
//            HtmlElement baidu = (HtmlElement) htmlPage.getElementById("su");
//            HtmlPage result_page = baidu.click();// 模拟点击
//            System.out.println(">>>>>>>>>" + result_page.asXml());
//
//
////            ScriptResult sr = htmlPage.executeJavaScript("javascript:(0);");
////            HtmlPage nextPage = (HtmlPage) sr.getJavaScriptResult();
////            ScriptResult result = htmlPage.executeJavaScript("document.getElementById('btn_log').click()");
////            HtmlPage jspage = (HtmlPage) result.getJavaScriptResult();
////            System.out.println("》》》》》》》》"+jspage.asXml());
            webClient.close();

//            Document document = null;
//            try {
//                document = Jsoup.connect("http://192.168.31.200:46018/Emubao/WebApp#/PastureProfit").cookies(responseCookies).get();
//                System.out.println("document.head"+document.html());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

        } catch (FailingHttpStatusCodeException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void test() {
        try (final WebClient webClient = new WebClient()) {
            final HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");
            Assert.assertEquals("HtmlUnit - Welcome to HtmlUnit", page.getTitleText());

            final String pageAsXml = page.asXml();
            Assert.assertTrue(pageAsXml.contains("<body class=\"composite\">"));

            final String pageAsText = page.asText();
            Assert.assertTrue(pageAsText.contains("Support for the HTTP and HTTPS protocols"));
        } catch (MalformedURLException e) {


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void submittingForm() throws Exception {
        try (final WebClient webClient = new WebClient()) {

            // Get the first page
            final HtmlPage page1 = webClient.getPage("http://some_url");

            // Get the form that we are dealing with and within that form,
            // find the submit button and the field that we want to change.
            final HtmlForm form = page1.getFormByName("myform");

            final HtmlSubmitInput button = form.getInputByName("submitbutton");
            final HtmlTextInput textField = form.getInputByName("userid");

            // Change the value of the text field
            textField.type("root");

            // Now submit the form by clicking the button and get back the second page.
            final HtmlPage page2 = button.click();
        }
    }


    public static void login() throws IOException {

        WebClient webClient = new WebClient(BrowserVersion.CHROME);

        //参数设置
        // 1 启动JS
        webClient.getOptions().setJavaScriptEnabled(true);
        // 2 禁用Css，可避免自动二次请求CSS进行渲染
        webClient.getOptions().setCssEnabled(false);
        //3 启动客户端重定向
        webClient.getOptions().setRedirectEnabled(true);
        // 4 运行错误时，是否抛出异常
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        // 5 设置超时
        webClient.getOptions().setTimeout(50000);
        //6 设置忽略证书
        //webClient.getOptions().setUseInsecureSSL(true);
        //7 设置Ajax
        //webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        //8设置cookie
        webClient.getCookieManager().setCookiesEnabled(true);

        //获取页面
        HtmlPage page = webClient.getPage("http://mail.163.com/");
        // 根据form的名字获取页面表单，也可以通过索引来获取：page.getForms().get(0)
        HtmlForm form = page.getFormByName("login163");
        HtmlTextInput username = (HtmlTextInput) form.getInputByName("username");
        HtmlPasswordInput password = (HtmlPasswordInput) form.getInputByName("password");
        username.setValueAttribute("sharpsword");
        password.setValueAttribute("xyz");
        HtmlButton button = (HtmlButton) page.getHtmlElementById("loginBtn");
        HtmlPage retPage = (HtmlPage) button.click();
        // 等待JS驱动dom完成获得还原后的网页
        webClient.waitForBackgroundJavaScript(10000);
        //输出网页内容
        System.out.println(retPage.asXml());
        //获取cookie
        Set<Cookie> cookies = webClient.getCookieManager().getCookies();
        ;
        Map<String, String> responseCookies = new HashMap<String, String>();
        for (Cookie c : cookies) {
            responseCookies.put(c.getName(), c.getValue());
            System.out.print(c.getName() + ":" + c.getValue());
        }
        Document document = null;
        try {
            document = Jsoup.connect("http://192.168.31.200:46018/Customer/PersonalCenter").cookies(responseCookies).get();
            System.out.println("document.head"+document.html());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //关闭webclient
        webClient.close();
    }

}







