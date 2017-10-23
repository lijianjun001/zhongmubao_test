package com.zhongmubao.api.service;

import com.zhongmubao.api.config.Constants;
import com.zhongmubao.api.config.WxTemplate;
import com.zhongmubao.api.config.enmu.SheepProjectPeriod;
import com.zhongmubao.api.config.enmu.SystemPushType;
import com.zhongmubao.api.dao.ExtRedPackageDao;
import com.zhongmubao.api.entity.Customer;
import com.zhongmubao.api.entity.ExtRedPackage;
import com.zhongmubao.api.mongo.dao.SystemPushMongoDao;
import com.zhongmubao.api.mongo.entity.SystemPushMongo;
import com.zhongmubao.api.util.DateUtil;
import com.zhongmubao.api.util.DoubleUtil;
import com.zhongmubao.api.util.MathUtil;
import com.zhongmubao.api.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

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
            String priceStr = DoubleUtil.toFixed(price * count, "0.00");
            String remark = "" + priceStr + "元（" + DoubleUtil.toFixed(price, "0.00") + "元*" + count + "）增益红包请在：个人中心-现金红包 中查看。";
            String content = WxTemplate.redPackage(customer.getOpenId(), priceStr + "元", remark);

            push(customer, "获赠红包", content, SystemPushType.WEIXIN);
        }
    }

    /**
     * 推送内容统一调用
     *
     * @param customer 客户
     * @param title    标题
     * @param content  内容
     * @param type     类型
     */
    protected void push(Customer customer, String title, String content, SystemPushType type) {
        try {
            SystemPushMongo pushMongo = new SystemPushMongo();
            pushMongo.setCustomerId(customer.getId());
            pushMongo.setTitle(title);
            pushMongo.setContent(content);
            pushMongo.setStatus("01"); //未推送
            pushMongo.setType(type.getName());
            pushMongo.setCreateTime(DateUtil.formatMongo(new Date()));
            systemPushMongoDao.add(pushMongo);
        } catch (Exception ex) {
        }
    }

    /**
     * 计算羊只利率
     * @param price 羊只单价
     * @param rate 比率
     * @param period 周期
     * @return
     * @author 米立林
     */
    public double calcProfitEx(double price, double rate, int period) {
        if (period == SheepProjectPeriod.PERIOD_120.getName() && rate == 13.50) {
            return Math.ceil(price * rate / 100 * period / 365);
        } else {
            return (price * rate / 100 * period / 365);
        }
    }

    /**
     * 羊耳标Photo
     * @param photo SheepPhoto url
     * @return 校验后的完整地址
     */
    protected String formatPhoto(String photo)
    {
        if (photo.toUpperCase().startsWith("HTTP"))
        {
            return photo;
        }

        return String.format("{0}{1}", Constants.UPLOAD_ADDRESS, photo);
    }
}
