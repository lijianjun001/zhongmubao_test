package com.zhongmubao.api.config;

public class WxTemplate {
    /**
     * 红包模版
     *
     * @param OpenId 微信OpenId
     * @param title  标题
     * @param price  金额
     * @param remark 备注
     * @return
     */
    public static String redPackage(String OpenId, String price, String remark) {
        return "{" +
                "\"touser\":\"" + OpenId + "\"," +
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
