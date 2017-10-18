package com.zhongmubao.api.config;

public class WxTemplate {
    /**
     * 红包模版
     *
     * @param openId 微信OpenId
     * @param price  金额
     * @param remark 备注
     * @return
     */
    public static String redPackage(String openId, String price, String remark) {
        return "{" +
                "\"touser\":\"" + openId + "\"," +
                "\"template_id\":\"FQidyQgTGVnheuHAzehDDBtS7kUUGXL0EhudsRzGa88\"," +
                "\"url\":\"http://m.emubao.com/Emubao/WebApp#/BonusList\"," +
                "\"topcolor\":\"#f43534\"," +
                "\"data\":{" +
                "\"first\": {" +
                "\"value\":\"你获得了众牧宝增益红包\"," +
                "\"color\":\"#173177\"" +
                "}," +
                "\"present_income\":{" +
                "\"value\":\"" + price + "\"," +
                "\"color\":\"#173177\"" +
                "}," +
                "\"remark\":{" +
                "\"value\":\"" + remark + "\"," +
                "\"color\":\"#173177\"" +
                "}}}";
    }
}
