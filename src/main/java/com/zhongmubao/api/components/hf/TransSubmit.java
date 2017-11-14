/**
 * 说明：Demo仅提供加签、验签和简单的接口调用参考
 * 具体的开发请以平台情况和接口文档为准
 * <p>
 * 汇付天下有限公司
 * <p>
 * Copyright (c) 2006-2013 ChinaPnR,Inc.All Rights Reserved.
 */
package com.zhongmubao.api.components.hf;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.zhongmubao.api.components.hf.http.HttpClientHandler;

public class TransSubmit {

    //如果关注性能问题可以考虑使用HttpClientConnectionManager
    public String doPost(Map<String, String> params) throws ClientProtocolException, IOException {
        String result = null;
        List<NameValuePair> nvps = HttpClientHandler.buildNameValuePair(params);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        EntityBuilder builder = EntityBuilder.create();
        try {
            HttpPost httpPost = new HttpPost(HttpClientHandler.HTTP_HOST);
            builder.setParameters(nvps);
            httpPost.setEntity(builder.build());
            CloseableHttpResponse response = httpclient.execute(httpPost);

            try {
                HttpEntity entity = response.getEntity();
                if (response.getStatusLine().getReasonPhrase().equals("OK")
                        && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    result = EntityUtils.toString(entity, "UTF-8");
                }
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
        return result;
    }

    /**
     * 模拟放款接口请求参数
     * @throws Exception
     *
     */
    private Map<String, String> getParams() throws Exception {
        String version = "10";
        String cmdId = "Loans";
        String merCustId = "6000060000002526";
        String ordId = "201405140000006";
        String ordDate = "20140514";
        String outCustId = "6000060000009547";
        String transAmt = "0.01";
        String fee = "0.00";
        String subOrdId = "00000000003";
        String subOrdDate = "20140424";
        String inCustId = "6000060000009574";
        String divDetails = "";
        String isDefault = "N";
        String bgRetUrl = "http://192.168.22.204/p2p_trade_demo-JAVA/loans";
        // 若为中文，请用Base64转码
        String merPriv = HttpClientHandler.getBase64Encode("11");

        Map<String, String> params = new HashMap<String, String>();
        params.put("Version", version);
        params.put("CmdId", cmdId);
        params.put("MerCustId", merCustId);
        params.put("OrdId", ordId);
        params.put("OrdDate", ordDate);
        params.put("OutCustId", outCustId);
        params.put("TransAmt", transAmt);
        params.put("Fee", fee);
        params.put("SubOrdId", subOrdId);
        params.put("SubOrdDate", subOrdDate);
        params.put("InCustId", inCustId);
        params.put("DivDetails", divDetails);
        params.put("IsDefault", isDefault);
        params.put("BgRetUrl", bgRetUrl);
        params.put("MerPriv", merPriv);

        // 组装加签字符串原文
        // 注意加签字符串的组装顺序参 请照接口文档
        StringBuffer buffer = new StringBuffer();
        buffer.append(StringUtils.trimToEmpty(version)).append(StringUtils.trimToEmpty(cmdId))
                .append(StringUtils.trimToEmpty(merCustId)).append(StringUtils.trimToEmpty(ordId))
                .append(StringUtils.trimToEmpty(ordDate)).append(StringUtils.trimToEmpty(outCustId))
                .append(StringUtils.trimToEmpty(transAmt)).append(StringUtils.trimToEmpty(fee))
                .append(StringUtils.trimToEmpty(subOrdId)).append(StringUtils.trimToEmpty(subOrdDate))
                .append(StringUtils.trimToEmpty(inCustId)).append(StringUtils.trimToEmpty(divDetails))
                .append(StringUtils.trimToEmpty(isDefault)).append(StringUtils.trimToEmpty(bgRetUrl))
                .append(StringUtils.trimToEmpty(merPriv));
        String plainStr = buffer.toString();
        System.out.println(plainStr);

        params.put("ChkValue", SignUtils.encryptByRSA(plainStr));

        return params;
    }

    /**
     * 模拟接口调用 - 放款
     * 根据接口文档使用post方式
     * @throws Exception
     *
     */
    public static void main() throws Exception {
        TransSubmit ts = new TransSubmit();
        Map<String, String> params = ts.getParams();
        // 1. result 为同步返回的结果(jason格式)，可转换成对应的实体对象
        // 2. 注意：此返回结果中没有使用encode，所以不需要做decode处理
        // 3. 验证签名的方式与异步应答的验签相同，可参照异步应答接收的处理方式
        String result = ts.doPost(params);
        System.out.println(result);
    }
}
