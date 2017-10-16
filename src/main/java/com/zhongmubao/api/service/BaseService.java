package com.zhongmubao.api.service;

import com.zhongmubao.api.config.WxTemplate;
import com.zhongmubao.api.dao.CustomerDao;
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

//
public class BaseService {
    @Autowired
    private ExtRedPackageDao extRedPackageDao;
    @Autowired
    private SystemPushMongoDao systemPushMongoDao;

    protected void sendRedPackage(Customer customer, String type, double price, Date expTime, int count) {
        Date now = new Date();
        for (int i = 0; i < count; i++) {
            ExtRedPackage extRedPackage = new ExtRedPackage(
                    customer.getId(),
                    type,
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
            try {
                String priceStr = DoubleUtil.toFixed(price * count, "0.00");
                String remark = "" + priceStr + "元（" + DoubleUtil.toFixed(price, "0.00") + "元*" + count + "）增益红包请在：个人中心-现金红包 中查看。";
                String content = WxTemplate.redPackage(customer.getOpenId(), priceStr + "元", remark);

                SystemPushMongo pushMongo = new SystemPushMongo();
                pushMongo.setCustomerId(customer.getId());
                pushMongo.setTitle("获赠红包");
                pushMongo.setContent(content);
                pushMongo.setStatus("01"); //未推送
                pushMongo.setType("04"); //微信
                pushMongo.setCreateTime(DateUtil.formartMongo(new Date()));
                systemPushMongoDao.add(pushMongo);
            } catch (Exception ex) {

            }
        }
    }
}
