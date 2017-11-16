package com.zhongmubao.api.components.hf;

import java.util.*;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.zhongmubao.api.components.hf.request.*;
import com.zhongmubao.api.components.hf.response.*;
import com.zhongmubao.api.util.DateUtil;
import com.zhongmubao.api.util.DoubleUtil;
import com.zhongmubao.api.util.SecurityUtil;
import com.zhongmubao.api.util.StringUtil;
import org.apache.commons.lang.StringUtils;

import com.zhongmubao.api.components.hf.http.HttpClientHandler;

/**
 * Hf核心
 *
 * @author 孙阿龙
 */
public class HfCore {

    //region 基础

    /**
     * 格式化请求参数
     *
     * @throws Exception 错误信息
     */
    private static Map<String, String> formartParams(List<BaseModel> list, boolean md5Encryption) throws Exception {
        Map<String, String> params = new HashMap<>(0);
        StringBuilder buffer = new StringBuilder();
        list = list.stream().sorted(Comparator.comparing(BaseModel::getSort)).collect(Collectors.toList());
        for (BaseModel model : list) {
            if (StringUtil.isNullOrEmpty(model.getValue())) {
                continue;
            }
            params.put(model.getKey(), model.getValue());
            if (model.getSort() > 0) {
                buffer.append(StringUtils.trimToEmpty(model.getValue()));
            }
        }
        String sign = buffer.toString();
        if (md5Encryption) {
            sign = SecurityUtil.md5(sign).toLowerCase();
        }
        params.put("ChkValue", SignUtils.encryptByRSA(sign));
        return params;
    }

    //endregion

    /**
     * 查询后台账户余额
     *
     * @param requestModel 请求参数
     * @return HfQueryBalanceBgResponse
     * @throws Exception 异常
     */
    public static HfSendSmsCodeResponse sendSmsCode(HfSendSmsCodeRequest requestModel) throws Exception {
        String version = "10";
        String cmdId = "SendSmsCode";

        BaseModel model = new BaseModel();
        List<BaseModel> list = new ArrayList<>();
        list.add(new BaseModel(1, "Version", version));
        list.add(new BaseModel(2, "CmdId", cmdId));
        list.add(new BaseModel(3, "MerCustId", Config.MER_CUST_ID));
        list.add(new BaseModel(4, "UsrCustId", requestModel.getUsrCustId()));
        list.add(new BaseModel(5, "BusiType", requestModel.getBusiType().getName()));
        list.add(new BaseModel(6, "OpenAcctId", requestModel.getOpenAcctId()));
        list.add(new BaseModel(7, "UsrMp", requestModel.getUsrMp()));
        list.add(new BaseModel(8, "SmsTempType", requestModel.getSmsTempType() == null ? null : requestModel.getSmsTempType().getName()));

        Map<String, String> params = formartParams(list, true);
        String result = HttpClientHandler.doPost(params);
        return new Gson().fromJson(result, HfSendSmsCodeResponse.class);
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
        if (StringUtil.isNullOrEmpty(requestModel.getUsrCustId())) {
            throw new Exception("客户号不能为空");
        }

        BaseModel model = new BaseModel();
        List<BaseModel> list = new ArrayList<>();
        list.add(new BaseModel(1, "Version", version));
        list.add(new BaseModel(2, "CmdId", cmdId));
        list.add(new BaseModel(3, "MerCustId", Config.MER_CUST_ID));
        list.add(new BaseModel(4, "UsrCustId", requestModel.getUsrCustId()));

        Map<String, String> params = formartParams(list, false);
        String result = HttpClientHandler.doPost(params);
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

        BaseModel model = new BaseModel();
        List<BaseModel> list = new ArrayList<>();
        list.add(new BaseModel(1, "Version", version));
        list.add(new BaseModel(2, "CmdId", cmdId));
        list.add(new BaseModel(3, "MerCustId", Config.MER_CUST_ID));

        Map<String, String> params = formartParams(list, false);
        String result = HttpClientHandler.doPost(params);
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

        BaseModel model = new BaseModel();
        List<BaseModel> list = new ArrayList<>();
        list.add(new BaseModel(1, "Version", version));
        list.add(new BaseModel(2, "CmdId", cmdId));
        list.add(new BaseModel(3, "MerCustId", Config.MER_CUST_ID));
        list.add(new BaseModel(4, "OrdId", requestModel.getOrdId()));
        list.add(new BaseModel(5, "UsrCustId", requestModel.getUsrCustId()));
        list.add(new BaseModel(6, "TransAmt", requestModel.getTransAmt()));
        list.add(new BaseModel(7, "RetUrl", Config.CASH_RET_URL));
        list.add(new BaseModel(8, "BgRetUrl", Config.CASH_BG_RET_URL));
        list.add(new BaseModel(9, "Remark", requestModel.getRemark()));

        Map<String, String> params = formartParams(list, false);
        return HttpClientHandler.doPost(params);
    }

    /**
     * 充值
     *
     * @param requestModel 请求参数
     * @return HfDirectRechargeResponse
     * @throws Exception 异常
     */
    public static HfDirectRechargeResponse directRecharge(HfDirectRechargeRequest requestModel) throws Exception {
        String version = "10";
        String cmdId = "DirectRecharge";

        BaseModel model = new BaseModel();
        List<BaseModel> list = new ArrayList<>();
        list.add(new BaseModel(1, "Version", version));
        list.add(new BaseModel(2, "CmdId", cmdId));
        list.add(new BaseModel(3, "MerCustId", Config.MER_CUST_ID));
        list.add(new BaseModel(4, "UsrCustId", requestModel.getUsrCustId()));
        list.add(new BaseModel(5, "OrdId", requestModel.getOrdId()));
        list.add(new BaseModel(6, "OrdDate", DateUtil.format(new Date(), "yyyyMMdd")));
        list.add(new BaseModel(7, "GateBusiId", "QP"));
        list.add(new BaseModel(8, "TransAmt", DoubleUtil.toFixed(requestModel.getTransAmt(), "0.00")));
        list.add(new BaseModel(9, "SmsSeq", requestModel.getSmsSeq()));
        list.add(new BaseModel(10, "SmsCode", requestModel.getSmsCode()));
        list.add(new BaseModel(12, "BgRetUrl", Config.DIRECT_RECHARGE_BG_URL));


        Map<String, String> params = formartParams(list, true);
        String result = HttpClientHandler.doPost(params);
        return new Gson().fromJson(result, HfDirectRechargeResponse.class);
    }

    /**
     * 交易状态查询
     *
     * @param requestModel 请求参数
     * @return HfDirectRechargeResponse
     * @throws Exception 异常
     */
    public static HfQueryTransStatResponse queryTransStat(HfQueryTransStatRequest requestModel) throws Exception {
        String version = "10";
        String cmdId = "QueryTransStat";

        BaseModel model = new BaseModel();
        List<BaseModel> list = new ArrayList<>();
        list.add(new BaseModel(1, "Version", version));
        list.add(new BaseModel(2, "CmdId", cmdId));
        list.add(new BaseModel(3, "MerCustId", Config.MER_CUST_ID));
        list.add(new BaseModel(5, "OrdId", requestModel.getOrdId()));
        list.add(new BaseModel(6, "OrdDate", DateUtil.format(requestModel.getOrdDate(), "yyyyMMdd")));
        list.add(new BaseModel(7, "QueryTransType", requestModel.getQueryTransType().getName()));

        Map<String, String> params = formartParams(list, false);
        String result = HttpClientHandler.doPost(params);
        HfQueryTransStatResponse response = new Gson().fromJson(result, HfQueryTransStatResponse.class);
        boolean isCheckSuccess = SignUtils.verifyByRSA(response.getPlainStr(), response.getChkValue());
        if (!isCheckSuccess) {
            throw new Exception("验证签名失败");
        }
        return response;
    }

    /**
     * 商户扣款对账
     *
     * @param requestModel 请求参数
     * @return HfDirectRechargeResponse
     * @throws Exception 异常
     */
    public static HfTrfReconciliationResponse trfReconciliation(HfTrfReconciliationRequest requestModel) throws Exception {
        String version = "10";
        String cmdId = "TrfReconciliation";

        BaseModel model = new BaseModel();
        List<BaseModel> list = new ArrayList<>();
        list.add(new BaseModel(1, "Version", version));
        list.add(new BaseModel(2, "CmdId", cmdId));
        list.add(new BaseModel(3, "MerCustId", Config.MER_CUST_ID));
        list.add(new BaseModel(5, "BeginDate", DateUtil.format(requestModel.getBeginDate(), "yyyyMMdd")));
        list.add(new BaseModel(6, "EndDate", DateUtil.format(requestModel.getEndDate(), "yyyyMMdd")));
        list.add(new BaseModel(7, "PageNum", requestModel.getPageNum() + ""));
        list.add(new BaseModel(8, "PageSize", requestModel.getPageSize() + ""));

        Map<String, String> params = formartParams(list, false);
        String result = HttpClientHandler.doPost(params);
        return new Gson().fromJson(result, HfTrfReconciliationResponse.class);
    }

    /**
     * 放还款对账
     *
     * @param requestModel 请求参数
     * @return HfReconciliationRequest
     * @throws Exception 异常
     */
    public static HfReconciliationResponse reconciliation(HfReconciliationRequest requestModel) throws Exception {
        String version = "10";
        String cmdId = "Reconciliation";

        BaseModel model = new BaseModel();
        List<BaseModel> list = new ArrayList<>();
        list.add(new BaseModel(1, "Version", version));
        list.add(new BaseModel(2, "CmdId", cmdId));
        list.add(new BaseModel(3, "MerCustId", Config.MER_CUST_ID));
        list.add(new BaseModel(4, "BeginDate", DateUtil.format(requestModel.getBeginDate(), "yyyyMMdd")));
        list.add(new BaseModel(5, "EndDate", DateUtil.format(requestModel.getEndDate(), "yyyyMMdd")));
        list.add(new BaseModel(6, "PageNum", requestModel.getPageNum()));
        list.add(new BaseModel(7, "PageSize", requestModel.getPageSize()));
        list.add(new BaseModel(8, "QueryTransType", requestModel.getQuerytranstype()));

        Map<String, String> params = formartParams(list, false);
        String result = HttpClientHandler.doPost(params);
        HfReconciliationResponse response = new Gson().fromJson(result, HfReconciliationResponse.class);

        return response;
    }

    /**
     * 取现对账
     *
     * @param requestModel 请求参数
     * @return HfReconciliationRequest
     * @throws Exception 异常
     */
    public static HfCashReconciliationResponse cashReconciliation(HfCashReconciliationRequeset requestModel) throws Exception {
        String version = "20";
        String cmdId = "CashReconciliation";

        BaseModel model = new BaseModel();
        List<BaseModel> list = new ArrayList<>();
        list.add(new BaseModel(1, "Version", version));
        list.add(new BaseModel(2, "CmdId", cmdId));
        list.add(new BaseModel(3, "MerCustId", Config.MER_CUST_ID));
        list.add(new BaseModel(4, "BeginDate", DateUtil.format(requestModel.getBeginDate(), "yyyyMMdd")));
        list.add(new BaseModel(5, "EndDate", DateUtil.format(requestModel.getEndDate(), "yyyyMMdd")));
        list.add(new BaseModel(6, "PageNum", requestModel.getPageNum()));
        list.add(new BaseModel(7, "PageSize", requestModel.getPageSize()));

        Map<String, String> params = formartParams(list, false);
        String result = HttpClientHandler.doPost(params);
        HfCashReconciliationResponse response = new Gson().fromJson(result, HfCashReconciliationResponse.class);

        return response;
    }

    /**
     * 充值对账
     *
     * @param requestModel 请求参数
     * @return HfReconciliationRequest
     * @throws Exception 异常
     */
    public static HfSaveReconciliationResponse saveReconciliation(HfSaveReconciliationRequeset requestModel) throws Exception {
        String version = "10";
        String cmdId = "SaveReconciliation";

        BaseModel model = new BaseModel();
        List<BaseModel> list = new ArrayList<>();
        list.add(new BaseModel(1, "Version", version));
        list.add(new BaseModel(2, "CmdId", cmdId));
        list.add(new BaseModel(3, "MerCustId", Config.MER_CUST_ID));
        list.add(new BaseModel(4, "BeginDate", DateUtil.format(requestModel.getBeginDate(), "yyyyMMdd")));
        list.add(new BaseModel(5, "EndDate", DateUtil.format(requestModel.getEndDate(), "yyyyMMdd")));
        list.add(new BaseModel(6, "PageNum", requestModel.getPageNum()));
        list.add(new BaseModel(7, "PageSize", requestModel.getPageSize()));

        Map<String, String> params = formartParams(list, false);
        String result = HttpClientHandler.doPost(params);
        HfSaveReconciliationResponse response = new Gson().fromJson(result, HfSaveReconciliationResponse.class);

        return response;
    }
}
