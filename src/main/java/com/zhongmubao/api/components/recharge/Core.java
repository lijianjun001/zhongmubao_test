package com.zhongmubao.api.components.recharge;

import com.zhongmubao.api.util.HttpUtil;
import org.json.JSONObject;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Core {

    private static String _secret_key = "BwHVb2y95AAfvvjiMh49YywybebkaUsBgZmhQKfJ0FPu2ZDD8vAVTWiV2j725ns0";
    private static String _gateway = "http://api.huafeiduo.com/gateway.cgi";
    private static String api_key = "aEsJAYb7usSzFj4E34u59eTUd5jueFne9FOztPuN8ccBm67dYq9VvQFyKOTOi7Th";

    private static String MD5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        String newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
        return newstr;
    }

    private static String _getSign(Hashtable<String, String> dic) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String paramString = "";
        List<String> v = new ArrayList<String>(dic.keySet());
        Collections.sort(v, new Comparator<Object>() {
            public int compare(Object arg0, Object arg1) {
                return Integer.valueOf(arg0.toString()) - Integer.valueOf(arg1.toString());
            }
        });
        for (String key : v)
            paramString += (key + "" + dic.get(key));

        String sign = MD5(paramString + _secret_key);

        return sign;
    }


    /*
    *	发送HTTP请求
    *	@param $url string 请求地址
    *	@param $params array 请求参数
    *	@param $method string GET或POST
    *	@param $timeout 超时时间
    *	@return string HTTP请求结果
    */
    private static String _sendRequest(String url, Hashtable<String, String> dic, String method) throws IOException {
        StringBuilder builder = new StringBuilder();
        if (method == "GET") {
            int i = 0;
            for (String obj : dic.keySet()) {
                if (i > 0)
                    builder.append("&");
                builder.append(String.format("{0}={1}", obj, dic.get(obj)));
                i++;
            }
        }
        url += builder;

        return HttpUtil.get(url);
    }


    /*
    *	检查指定面额和手机号码当前是否可以下单, 以及获取下单价格
	*	@param $card_worth int 待检测的充值面额 1、2、5、10、20、30、50、100、300 元
	*	@param $phone_number string 待检测的充值手机号码
	*	@return 检查结果为＂可以下单＂时返回数组, 此数组形如:
	*		array(
	*			'price' => 49.5, // 成本价
	*			'card_worth'=> 50, //面额
	*			'phone_number'=> 15623722222, //手机号码
	*			'area'=> '湖北武汉', //手机归属地
	*			'platform'=> '联通'	 //运营商
	*		)
    *		检查结果为"正在维护(无法充值)"时返回FALSE
	*/
    public static JSONObject check(String card_worth, String phone_number) throws IOException, NoSuchAlgorithmException {
        Hashtable<String, String> dic = new Hashtable<String, String>();
        dic.put("card_worth", card_worth);
        dic.put("phone_number", phone_number);
        dic.put("api_key", api_key);

        String sign = _getSign(dic);
        dic.put("sign", sign);

        String ret_json_string = _sendRequest(_gateway + "?mod=order.phone.check&", dic, "GET");

        return new JSONObject(ret_json_string);
    }

    /*
            *	提交充值(下单)
            * 	注意:
            *		1. 提交成功不代表充值成功, 充值成功与否需要依赖异步回调结果, 或提交后调用order.phone.status接口确认订单状态
            *		2. 如果接口没有明确返回失败(例如请求超时),  则不能说明订单是充值失败
            *	@param $card_worth int 充值面额 充值面额，目前支持的面额有：1、2、5、10、20、30、50、100、300 元
            *	@param $phone_number string 充值手机号码
            *	@param $sp_order_id string 商户订单号
            *	@return boolean 提交成功返回TRUE, 提交失败返回FALSE
            */
    public static JSONObject submit(String card_worth, String phone_number, String sp_order_id) throws IOException, NoSuchAlgorithmException {
        Hashtable<String, String> dic = new Hashtable<String, String>();
        dic.put("card_worth", card_worth);
        dic.put("phone_number", phone_number);
        dic.put("sp_order_id", sp_order_id);
        dic.put("api_key", api_key);


        String sign = _getSign(dic);
        dic.put("sign", sign);
        String ret_json_string = _sendRequest(_gateway + "?mod=order.phone.submit&", dic, "GET");

        return new JSONObject(ret_json_string);
    }

    /*
        *	检查当前订单状态
        *	@param $sp_order_id string 商户订单号(同order.phone.submit接口中的sp_order_id)
        *	@return string:
        *		"init": 充值中
        *		"recharging": 充值中
        *		"success": 充值成功
        *		"failure": 充值失败
        */
    public static JSONObject status(String sp_order_id) throws IOException, NoSuchAlgorithmException {

        Hashtable<String, String> dic = new Hashtable<String, String>();
        dic.put("sp_order_id", sp_order_id);
        dic.put("api_key", api_key);

        String sign = _getSign(dic);
        dic.put("sign", sign);
        String ret_json_string = _sendRequest(_gateway + "?mod=order.phone.status&", dic, "GET");

        return new JSONObject(ret_json_string);
    }

    /*
    *	针对notify_url来验证消息是否是话费多发出的合法消息
    *	@return boolean 验证通过时返回TRUE 否则返回FALSE
    */
    public static boolean verifyNotify(Hashtable<String, String> dic) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String orderId = dic.get("order_id");
        String status = dic.get("status");
        String worth = dic.get("worth");
        String price = dic.get("price");
        String phone = dic.get("phone");
        String spId = dic.get("sp_order_id");
        String sign = dic.get("sign");

        if (sign == MD5(orderId + status + worth + price + phone + spId + _secret_key)) {
            return true;
        }

        return false;
    }


}
