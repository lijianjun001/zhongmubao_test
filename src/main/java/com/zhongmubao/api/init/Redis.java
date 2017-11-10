package com.zhongmubao.api.init;


import com.zhongmubao.api.config.Constants;
import com.zhongmubao.api.dao.SheepLevelDao;
import com.zhongmubao.api.entity.SheepLevel;
import com.zhongmubao.api.util.redis.RedisHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 孙阿龙
 */
@Component
public class Redis {

    private final RedisHelper redisHelper;
    private final SheepLevelDao sheepLevelDao;

    @Autowired
    public Redis(RedisHelper redisHelper, SheepLevelDao sheepLevelDao) {
        this.redisHelper = redisHelper;
        this.sheepLevelDao = sheepLevelDao;
    }

    /**
     * 客户等级缓存至Redis
     * Redis Key:CACHE_CUSTOMER_LEVEL_KEY
     *
     * @return 客户等级集合
     * @author 米立林 2017-10-09
     */
    public void setCustomerLevelCacheToRedis() {
        List<SheepLevel> sheepLevels = sheepLevelDao.pagerSheepLevelList(0, 10);
        redisHelper.setHash(Constants.CACHE_CUSTOMER_LEVEL_TABLE, Constants.CACHE_CUSTOMER_LEVEL_KEY, sheepLevels);
    }
}
