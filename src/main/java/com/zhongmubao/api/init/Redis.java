package com.zhongmubao.api.init;


import com.zhongmubao.api.config.Constants;
import com.zhongmubao.api.dao.SheepLevelDao;
import com.zhongmubao.api.entity.SheepLevel;
import com.zhongmubao.api.util.SerializeUtil;
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
     * 加载Redis
     *
     * @author 孙阿龙
     */
    void init() {
        this.initCustomerLevelCacheToRedis();
    }

    /**
     * 客户等级缓存至Redis
     * Redis Key:CACHE_CUSTOMER_LEVEL_KEY
     *
     * @author 米立林 2017-10-09
     */
    private void initCustomerLevelCacheToRedis() {
        if (redisHelper.get(Constants.CACHE_CUSTOMER_LEVEL_TABLE) != null) {
            return;
        }
        List<SheepLevel> sheepLevels = sheepLevelDao.pagerSheepLevelList(0, 10);
        redisHelper.save(Constants.CACHE_CUSTOMER_LEVEL_TABLE, SerializeUtil.serialize(sheepLevels));
    }
}
