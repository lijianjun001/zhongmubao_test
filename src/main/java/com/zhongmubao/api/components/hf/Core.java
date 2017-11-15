package com.zhongmubao.api.components.hf;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.zhongmubao.api.components.hf.request.HfCashRequest;
import com.zhongmubao.api.components.hf.request.HfDirectRechargeRequest;
import com.zhongmubao.api.components.hf.request.HfQueryAcctsRequest;
import com.zhongmubao.api.components.hf.response.HfQueryAcctsResponse;
import com.zhongmubao.api.components.hf.request.HfQueryBalanceBgRequest;
import com.zhongmubao.api.components.hf.response.HfQueryBalanceBgResponse;
import com.zhongmubao.api.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.zhongmubao.api.components.hf.http.HttpClientHandler;

/**
 * Hf核心
 *
 * @author 孙阿龙
 */
public class Core {

    static ObjectMapper mapper = new ObjectMapper();
    //region 基础

    private static String doPost(Map<String, String> params) throws IOException {
        String result = null;
        List<NameValuePair> nvps = HttpClientHandler.buildNameValuePair(params);
        EntityBuilder builder = EntityBuilder.create();
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(HttpClientHandler.HTTP_HOST);
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

    //endregion

    /**
     * 格式化请求参数
     *
     * @throws Exception 错误信息
     */
    private static Map<String, String> formartParams(List<HfBaseModel> list) throws Exception {
        Map<String, String> params = new HashMap<>(0);
        StringBuilder buffer = new StringBuilder();
        list = list.stream().sorted(Comparator.comparing(HfBaseModel::getSort)).collect(Collectors.toList());
        for (HfBaseModel model : list) {
            params.put(model.getKey(), model.getValue());
            if (model.getSort() > 0) {
                buffer.append(StringUtils.trimToEmpty(model.getValue()));
            }
        }
        params.put("ChkValue", SignUtils.encryptByRSA(buffer.toString()));
        return params;
    }

    /**
     * 查询后台账户余额
     *
     * @param requestModel 请求参数
     * @return HfQueryBalanceBgResponse
     * @throws Exception 异常
     */
    public static HfQueryBalanceBgResponse queryBalanceBg(HfQueryBalanceBgRequest requestModel) throws Exception {
        String version = "10";
        String cmdId = "QueryBalanceBg";
        if (StringUtil.isNullOrEmpty(requestModel.getMerCustId())) {
            throw new Exception("商户号不能为空");
        }
        if (StringUtil.isNullOrEmpty(requestModel.getUsrCustId())) {
            throw new Exception("客户号不能为空");
        }

        HfBaseModel model = new HfBaseModel();
        List<HfBaseModel> list = new ArrayList<>();
        list.add(new HfBaseModel(1, "Version", version));
        list.add(new HfBaseModel(2, "CmdId", cmdId));
        list.add(new HfBaseModel(3, "MerCustId", requestModel.getMerCustId()));
        list.add(new HfBaseModel(4, "UsrCustId", requestModel.getUsrCustId()));

        Map<String, String> params = formartParams(list);
        String result = doPost(params);
        HfQueryBalanceBgResponse response = new Gson().fromJson(result, HfQueryBalanceBgResponse.class);
        boolean isCheckSuccess = SignUtils.verifyByRSA(response.getPlainStr(), response.getChkValue());
        if (!isCheckSuccess) {
            throw new Exception("验证签名失败");
        }
        return response;
    }

    /**
     * 商户子账户查询
     *
     * @param requestModel 请求参数
     * @return HfQueryAcctsResponse
     * @throws Exception 异常
     */
    public static HfQueryAcctsResponse queryaccts(HfQueryAcctsRequest requestModel) throws Exception {
        String version = "10";
        String cmdId = "QueryAccts";
        if (StringUtil.isNullOrEmpty(requestModel.getMerCustId())) {
            throw new Exception("商户号不能为空");
        }

        HfBaseModel model = new HfBaseModel();
        List<HfBaseModel> list = new ArrayList<>();
        list.add(new HfBaseModel(1, "Version", version));
        list.add(new HfBaseModel(2, "CmdId", cmdId));
        list.add(new HfBaseModel(3, "MerCustId", requestModel.getMerCustId()));

        Map<String, String> params = formartParams(list);
        String result = doPost(params);
        HfQueryAcctsResponse response = new Gson().fromJson(result, HfQueryAcctsResponse.class);

        boolean isCheckSuccess = SignUtils.verifyByRSA(response.getPlaintStr(), response.getChkValue());
        if (!isCheckSuccess) {
            throw new Exception("验证签名失败");
        }
        return response;
    }

    /**
     * 提现
     *
     * @param requestModel 请求参数
     * @return HTML
     * @throws Exception 异常
     */
    public static String cash(HfCashRequest requestModel) throws Exception {
        String version = "20";
        String cmdId = "Cash";

        HfBaseModel model = new HfBaseModel();
        List<HfBaseModel> list = new ArrayList<>();
        list.add(new HfBaseModel(1, "Version", version));
        list.add(new HfBaseModel(2, "CmdId", cmdId));
        list.add(new HfBaseModel(3, "MerCustId", requestModel.getMerCustId()));
        list.add(new HfBaseModel(4, "OrdId", requestModel.getOrdId()));
        list.add(new HfBaseModel(5, "UsrCustId", requestModel.getUsrCustId()));
        list.add(new HfBaseModel(6, "TransAmt", requestModel.getTransAmt()));
        list.add(new HfBaseModel(7, "RetUrl", requestModel.getRetUrl()));
        list.add(new HfBaseModel(8, "BgRetUrl", requestModel.getBgRetUrl()));
        list.add(new HfBaseModel(9, "Remark", requestModel.getRemark()));

        Map<String, String> params = formartParams(list);
        return doPost(params);
    }

    public static String directRecharge(HfDirectRechargeRequest requestModel) throws Exception {
        String version = "10";
        String cmdId = "DirectRecharge";

        //            requestModel.setMerCustId("6000060007633813");
//            requestModel.setUsrCustId("6000060007653943");
        HfBaseModel model = new HfBaseModel();
        List<HfBaseModel> list = new ArrayList<>();
        list.add(new HfBaseModel(1, "Version", version));
        list.add(new HfBaseModel(2, "CmdId", cmdId));
        list.add(new HfBaseModel(3, "MerCustId", "6000060007633813"));
        list.add(new HfBaseModel(4, "UsrCustId", "6000060007653943"));
        list.add(new HfBaseModel(5, "OrdId", "2017111517511152222"));
        list.add(new HfBaseModel(6, "OrdDate", "20171115"));
        list.add(new HfBaseModel(7, "GateBusiId", "B2C"));
        list.add(new HfBaseModel(8, "TransAmt", "1.0"));
        list.add(new HfBaseModel(9, "RetUrl", "http://baidu.com"));
        list.add(new HfBaseModel(10, "BgRetUrl", "http://baidu.com"));


        Map<String, String> params = formartParams(list);
        String str = doPost(params);
        return doPost(params);
    }
}
