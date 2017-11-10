package com.zhongmubao.api.cache;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhongmubao.api.config.Constants;
import com.zhongmubao.api.dao.SheepLevelDao;
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
    @Autowired
    public SheepLevelDao sheepLevelDao;

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
            String str = redisHelper.get(Constants.CACHE_CUSTOMER_LEVEL_KEY);
            ObjectMapper mapper = new ObjectMapper();

            if (StringUtil.isNullOrEmpty(str)) {
                List<SheepLevel> sheepLevels = sheepLevelDao.pagerSheepLevelList(0, 10);
                String json = mapper.writeValueAsString(sheepLevels);
                redisHelper.save(Constants.CACHE_CUSTOMER_LEVEL_KEY, json);
                return sheepLevels;
            } else {
                return mapper.readValue(str, new TypeReference<List<SheepLevel>>() {
                });
            }
        } catch (Exception ex) {
            return null;
        }
    }
}
