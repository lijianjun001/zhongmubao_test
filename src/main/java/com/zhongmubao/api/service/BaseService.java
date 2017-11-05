package com.zhongmubao.api.service;

import com.zhongmubao.api.config.WxTemplate;
import com.zhongmubao.api.config.enmu.RedPackageType;
import com.zhongmubao.api.config.enmu.SystemPushType;
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
                    expTime,
                    now,
                    now,
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
     * @param type 类型
     * @param price 金额
     * @param expTime 过期时间
     * @param count 数量
     * @return 格式化后的内容
     */
    private String formartRedPackageRemark(RedPackageType type, double price, Date expTime, int count) {
        String priceStr = DoubleUtil.toFixed(price * count, "0.00");
        String remark = "" + priceStr + "元（" + DoubleUtil.toFixed(price, "0.00") + "元*" + count + "）增益红包请在：个人中心-现金红包 中查看。";

        switch (type){
            case DAY_SHARE:
                    remark = "红包到账！恭喜您今日抢到拼手气红包"+priceStr+"元，快去抢羊增加收益吧！（每日仅有一次分享签到得红包机会）累积分享签到还可得神秘礼物哦！";
                break;
            default:
                break;
        }
        return remark;
    }
    /**
     * 推送内容统一调用
     * @author 孙阿龙
     * @param customer 客户
     * @param title    标题
     * @param content  内容
     * @param type     类型
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
}
