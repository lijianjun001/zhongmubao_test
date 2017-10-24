package com.zhongmubao.api.components.recharge;

import com.zhongmubao.api.util.HttpUtil;
import com.zhongmubao.api.util.SecurityUtil;

import java.util.*;

/***
 * 充值
 * @author 孙阿龙
 */
public class Recharge {
    public static void submit(String phone, int price, String orderId) {
        //字典序排序
        HashMap<String, String> map = new HashMap<>();

        map.put("phone_number", phone);
        map.put("card_worth", price + "");
        map.put("sp_order_id", orderId);
        map.put("api_key", Config.API_KEY);

        //region 拿到sign
        Collection<String> keys = map.keySet();
        List list = new ArrayList<>(keys);
        Collections.sort(list);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i) + "" + map.get(list.get(i)));
        }
        sb.append(Config.SECRET_KEY);

        String sign = SecurityUtil.md5(sb.toString()).toLowerCase();

        map.put("sign", sign);
        //endregion

        //region 拼装url参数
        Collection<String> keys1 = map.keySet();
        List list1 = new ArrayList<>(keys1);
        Collections.sort(list);
        StringBuffer sb1 = new StringBuffer();
        for (int i = 0; i < list1.size(); i++) {
            sb1.append(list1.get(i) + "=" + map.get(list1.get(i)));
            if (i <= list.size() - 1) {
                sb1.append("&");
            }
        }
        //endregion

        try {
            String str = HttpUtil.get(Config.SUBMIT_URL + "&" + sb1.toString());
        } catch (Exception ex) {

        }
    }
}
