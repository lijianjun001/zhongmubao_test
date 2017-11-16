package com.zhongmubao.api.components.hf.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhongmubao.api.components.hf.Config;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import chinapnr.Base64;
import org.apache.http.util.EntityUtils;

/**
 * @author 孙阿龙
 */
public class HttpClientHandler {

    /**
     * MAP类型数组转换成NameValuePair类型
     */
    private static List<NameValuePair> buildNameValuePair(Map<String, String> params) {
        List<NameValuePair> nvps = new ArrayList<>();
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }

        return nvps;
    }

    public static String getBase64Encode(String str) {
        if (str == null || "".equals(str)) {
            return "";
        }
        try {
            byte[] bt = str.getBytes("UTF-8");
            str = String.valueOf(Base64.encode(bt));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String getBase64Decode(String str) {
        if (str == null || "".equals(str)) {
            return "";
        }
        char[] ch = str.toCharArray();
        byte[] bt = Base64.decode(ch);
        try {
            str = new String(bt, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String doPost(Map<String, String> params) throws IOException {
        String result = null;
        List<NameValuePair> nvps = HttpClientHandler.buildNameValuePair(params);
        EntityBuilder builder = EntityBuilder.create();
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(Config.HTTP_HOST);
            builder.setParameters(nvps);
            httpPost.setEntity(builder.build());

            try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
                try {
                    HttpEntity entity = response.getEntity();
                    String ok = "OK";
                    if (ok.equals(response.getStatusLine().getReasonPhrase())
                            && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                        result = EntityUtils.toString(entity, "UTF-8");
                    }
                    EntityUtils.consume(entity);
                } finally {
                    response.close();
                }
            }
        }
        return result;
    }
}
