package com.zhongmubao.api.cache;

import com.zhongmubao.api.config.Constants;
import com.zhongmubao.api.util.DateUtil;
import com.zhongmubao.api.util.StringUtil;
import com.zhongmubao.api.util.redis.RedisHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Redis缓存
 *
 * @author 孙阿龙
 */
@Repository
public class RedisCache {

    @Autowired
    public RedisHelper redisHelper;

    public boolean getNewPeopleProjectIsShow(int customerId) {
        try {
            String key = Constants.CACHE_NEW_PEOPLE_PROJECT_IS_SHOW_KEY + "_" + customerId;
            String str = redisHelper.get(key);
            return !StringUtil.isNullOrEmpty(str);
        } catch (Exception ex) {
            return false;
        }
    }

    public void saveNewPeopleProjectIsShow(int customerId) {
        try {
            String key = Constants.CACHE_NEW_PEOPLE_PROJECT_IS_SHOW_KEY + "_" + customerId;
            redisHelper.save(key, "1");
        } catch (Exception ex) {

        }
    }

    public boolean getCustomerIsShare(int customerId) {
        try {
            String str = String.valueOf(redisHelper.getHash(Constants.CACHE_CUSTOMER_IS_SHARE_KEY, customerId + ""));
            String value = DateUtil.formatShort(new Date());

            if (StringUtil.isNullOrEmpty(str)) {
                return false;
            }
            if (!str.equals(value)) {
                return false;
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public void saveCustomerIsShare(int customerId) {
        try {
            String value = DateUtil.formatShort(new Date());
            redisHelper.setHash(Constants.CACHE_CUSTOMER_IS_SHARE_KEY, customerId + "", value);
        } catch (Exception ex) {

        }
    }

    public double getShareRandom(int key) {
        try {
            String table = Constants.CACHE_SHARE_RANDOM_KEY;
            return Double.parseDouble(redisHelper.getHash(table, String.valueOf(key)) + "");
        } catch (Exception ex) {
            return 0;
        }
    }

    public boolean hasSinglesDay(int key) {
        try {
            String table = Constants.SINGLESDAY;
            Object obj = redisHelper.getHash(table, String.valueOf(key));
            String value = String.valueOf(redisHelper.getHash(table, String.valueOf(key)));
            return value.equals("true") ? true : false;
        } catch (Exception ex) {
            return false;
        }
    }

    public void saveSinglesDay(int key) {
        try {
            String table = Constants.SINGLESDAY;
            redisHelper.setHash(table, String.valueOf(key) + "", "true");
        } catch (Exception ex) {
        }
    }
}
