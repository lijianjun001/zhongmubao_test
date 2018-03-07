package com.zhongmubao.api.service.impl;

import com.zhongmubao.api.config.Constants;
import com.zhongmubao.api.config.WxTemplate;
import com.zhongmubao.api.config.enmu.*;
import com.zhongmubao.api.dao.ExtRedPackageDao;
import com.zhongmubao.api.entity.Customer;
import com.zhongmubao.api.entity.ExtRedPackage;
import com.zhongmubao.api.mongo.dao.SystemPushMongoDao;
import com.zhongmubao.api.mongo.entity.SystemPushMongo;
import com.zhongmubao.api.util.DateUtil;
import com.zhongmubao.api.util.DoubleUtil;
import com.zhongmubao.api.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * 基础服务
 *
 * @author 孙阿龙
 */
public class BaseService {
    @Autowired
    private ExtRedPackageDao extRedPackageDao;
    @Autowired
    private SystemPushMongoDao systemPushMongoDao;

    protected void sendRedPackage(Customer customer, RedPackageType type, double price, Date expTime, int count) {
        if (price < 0) {
            return;
        }
        Date now = new Date();
        for (int i = 0; i < count; i++) {
            ExtRedPackage extRedPackage = new ExtRedPackage(
                    customer.getId(),
                    type.getName(),
                    0,
                    false,
                    price,
                    false,
                    null,
                    120,
                    DateUtil.dayEnd(expTime),
                    now,
                    now,
                    1,
                    1
            );
            extRedPackageDao.insertExtRedPackage(extRedPackage);
        }

        if (!StringUtil.isNullOrEmpty(customer.getOpenId())) {
            String priceStr = DoubleUtil.toFixed(price * count, "0.00");
            String remark = formartRedPackageRemark(type, price, expTime, count);
            String content = WxTemplate.redPackage(customer.getOpenId(), priceStr + "元", remark);
            push(customer, "获赠红包", content, SystemPushType.WEIXIN);
        }
    }

    /**
     * 获取微信 内容
     *
     * @param type    类型
     * @param price   金额
     * @param expTime 过期时间
     * @param count   数量
     * @return 格式化后的内容
     */
    private String formartRedPackageRemark(RedPackageType type, double price, Date expTime, int count) {
        String priceStr = DoubleUtil.toFixed(price * count, "0.00");
        String remark = "" + priceStr + "元（" + DoubleUtil.toFixed(price, "0.00") + "元*" + count + "）增益红包请在：个人中心-现金红包 中查看。";

        switch (type) {
            case DAY_SHARE:
                remark = "红包到账！恭喜您今日抢到拼手气红包" + priceStr + "元，快去抢羊增加收益吧！（每日仅有一次分享签到得红包机会）累积分享签到还可得神秘礼物哦！";
                break;
            default:
                break;
        }
        return remark;
    }

    /**
     * 推送内容统一调用
     *
     * @param customer 客户
     * @param title    标题
     * @param content  内容
     * @param type     类型
     * @author 孙阿龙
     */
    private void push(Customer customer, String title, String content, SystemPushType type) {
        try {
            SystemPushMongo pushMongo = new SystemPushMongo();
            pushMongo.setCustomerId(customer.getId());
            pushMongo.setTitle(title);
            pushMongo.setContent(content);
            /* 未推送 */
            pushMongo.setStatus("01");
            pushMongo.setType(type.getName());
            pushMongo.setCreateTime(DateUtil.formatMongo(new Date()));
            systemPushMongoDao.add(pushMongo);
        } catch (Exception ignore) {
        }
    }

    /**
     * 发送短信统一调用
     *
     * @param phone   手机号
     * @param title   标题
     * @param content 内容
     * @author 米立林
     */
    protected void sendSms(String phone, String title, String content) {
        try {
            SystemPushMongo pushMongo = new SystemPushMongo();
            pushMongo.setPhone(phone);
            pushMongo.setTitle(title);
            pushMongo.setContent(content);
            /* 未推送 */
            pushMongo.setStatus("01");
            pushMongo.setType(SystemPushType.SMS.getName());
            pushMongo.setCreateTime(DateUtil.formatMongo(new Date()));
            systemPushMongoDao.add(pushMongo);
        } catch (Exception ignore) {
        }
    }

    /**
     * 计算羊只利率
     *
     * @param price  羊只单价
     * @param rate   比率
     * @param period 周期
     * @return 收益
     * @author 米立林
     */
    protected double calcProfitEx(double price, double rate, int period) {
        if (period == SheepProjectPeriod.PERIOD_120.getName() && rate == 13.50) {
            return Math.ceil(price * rate / 100 * period / 365);
        } else {
            return (price * rate / 100 * period / 365);
        }
    }

    /**
     * 通过平台获取平台域名
     *
     * @param platform 平台
     * @return
     */
    protected String getDomainByPlatform(String platform) {
        if (Platform.IOS.getName().equals(platform)) {
            return Domain.IOS.getDomain();
        } else if (Platform.ANDROID.getName().equals(platform)) {
            return Domain.ANDROID.getDomain();
        } else if (Platform.WEIXIN.getName().equals(platform)) {
            return Domain.WEIXIN.getDomain();
        } else if (Platform.WAP.getName().equals(platform)) {
            return Domain.WAP.getDomain();
        }
        return "";
    }


    /**
     * 生成Code
     *
     * @param customerId 客户id
     * @return code
     * @author 米立林
     */
    protected static String inviteCode(int customerId) {
        int maxLength = 6;
        String str = Integer.toString(customerId + 88888);

        int length = maxLength - str.length();
        if (length <= 0) {
            return str;
        }
        StringBuffer buffer = new StringBuffer();
        for (int j = 0; j <= maxLength - str.length(); j++) {
            buffer.append("Y" + str);
        }
        str = buffer.toString();
        return str;
    }

    /**
     * code解码
     *
     * @param code 客户code
     * @return 客户id
     * @author 米立林
     */
    protected static int dInviteCode(String code) {
        try {
            String str = code.trim().toLowerCase().replace("y", Constants.EMPTY_STRING);
            return Integer.parseInt(str) - 88888;
        } catch (Exception ex) {
            return 0;
        }
    }
}
