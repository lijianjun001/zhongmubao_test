package com.zhongmubao.api.cache;

import com.zhongmubao.api.config.Constants;
import com.zhongmubao.api.entity.SheepLevel;
import com.zhongmubao.api.util.DateUtil;
import com.zhongmubao.api.util.StringUtil;
import com.zhongmubao.api.util.redis.RedisHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

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
            //ignore
        }
    }

    public boolean getCustomerIsShare(int customerId) {
        try {
            String str = String.valueOf(redisHelper.getHash(Constants.CACHE_CUSTOMER_IS_SHARE_KEY, customerId + ""));
            String value = DateUtil.formatShort(new Date());

            return !StringUtil.isNullOrEmpty(str) && str.equals(value);
        } catch (Exception ex) {
            return false;
        }
    }

    public void saveCustomerIsShare(int customerId) {
        try {
            String value = DateUtil.formatShort(new Date());
            redisHelper.setHash(Constants.CACHE_CUSTOMER_IS_SHARE_KEY, customerId + "", value);
        } catch (Exception ex) {
            //ignore
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
            return redisHelper.hasKey(table, String.valueOf(key));
        } catch (Exception ex) {
            return true;
        }
    }

    public void saveSinglesDay(int key) {
        try {
            String table = Constants.SINGLESDAY;
            redisHelper.setHash(table, String.valueOf(key) + "", "true");
        } catch (Exception ex) {
            //ignore
        }
    }

    /**
     * 客户等级
     * Redis Key:CACHE_CUSTOMER_LEVEL_KEY
     *
     * @return 客户等级集合
     * @author 米立林 2017-10-09
     */
    public List<SheepLevel> getCustomerLevel() {
        try {
            if (redisHelper.hasKey(Constants.CACHE_CUSTOMER_LEVEL_TABLE, Constants.CACHE_CUSTOMER_LEVEL_KEY)) {
                Object object = redisHelper.getHash(Constants.CACHE_CUSTOMER_LEVEL_TABLE, Constants.CACHE_CUSTOMER_LEVEL_KEY);
                return (List<SheepLevel>) object;
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }

    public void savePersonalCenter(String key,Object value) {
        try {
            String table = Constants.PERSONALCENTER;
            redisHelper.setHash(table,key,value);
        } catch (Exception ex) {
            //ignore
        }
    }
    public Object getPersonalCenter() {
        try {
            String table = Constants.PERSONALCENTER;
            return redisHelper.getAllHash(table);
        } catch (Exception ex) {
            //ignore
            return  null;
        }
    }
    public boolean getRealNameType(int customerId) {
        try {
            String str = String.valueOf(redisHelper.getHash(Constants.REALNAMETYPE, customerId + ""));

            return !StringUtil.isNullOrEmpty(str);
        } catch (Exception ex) {
            return false;
        }
    }
}
