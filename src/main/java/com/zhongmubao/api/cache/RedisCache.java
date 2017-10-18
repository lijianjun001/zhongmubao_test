package com.zhongmubao.api.cache;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhongmubao.api.config.Constants;
import com.zhongmubao.api.dao.SheepLevelDao;
import com.zhongmubao.api.dao.SheepVendorDao;
import com.zhongmubao.api.dao.SystemDistrictDao;
import com.zhongmubao.api.dao.SystemMonitorDao;
import com.zhongmubao.api.entity.SheepLevel;
import com.zhongmubao.api.entity.SheepVendor;
import com.zhongmubao.api.entity.SystemDistrict;
import com.zhongmubao.api.entity.SystemMonitor;
import com.zhongmubao.api.mongo.dao.NotifyCycleMongoDao;
import com.zhongmubao.api.mongo.dao.NotifyTypeMongoDao;
import com.zhongmubao.api.mongo.entity.NotifyCycleMongo;
import com.zhongmubao.api.mongo.entity.NotifyTypeMongo;
import com.zhongmubao.api.util.DateUtil;
import com.zhongmubao.api.util.StringUtil;
import com.zhongmubao.api.util.redis.RedisHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class RedisCache {

    @Autowired
    public RedisHelper redisHelper;

    @Autowired
    private SheepVendorDao sheepVendorDao;

    @Autowired
    private SheepLevelDao sheepLevelDao;

    @Autowired
    private SystemMonitorDao systemMonitorDao;

    @Autowired
    private SystemDistrictDao systemDistrictDao;

    @Autowired
    private final NotifyTypeMongoDao notifyTypeMongoDao;

    @Autowired
    private final NotifyCycleMongoDao notifyCycleMongoDao;

    public RedisCache(SystemDistrictDao systemDistrictDao, NotifyTypeMongoDao notifyTypeMongoDao, NotifyCycleMongoDao notifyCycleMongoDao) {
        this.systemDistrictDao = systemDistrictDao;
        this.notifyTypeMongoDao = notifyTypeMongoDao;
        this.notifyCycleMongoDao = notifyCycleMongoDao;
    }


    //CACHE_NEW_PEOPLE_PROJECT_IS_SHOW_KEY
    public List<SheepVendor> vendorList() {
        try {
            String str = redisHelper.get(Constants.CACHE_SHEEP_VENDOR_LIST_KEY);
            ObjectMapper mapper = new ObjectMapper();

            if (StringUtil.isNullOrEmpty(str)) {
                List<SheepVendor> sheepVendorList = sheepVendorDao.getSheepVendorList();
                String json = mapper.writeValueAsString(sheepVendorList);
                redisHelper.save(Constants.CACHE_SHEEP_VENDOR_LIST_KEY, json);
                return sheepVendorList;
            } else {
                return mapper.readValue(str, new TypeReference<List<SheepVendor>>() {
                });
            }
        } catch (Exception ex) {
            return null;
        }
    }

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

    /**
     * 所有省市区列表
     * Redis Key:Constants.CACHE_SYSTEM_DISTRICT_KEY
     *
     * @return 省市区集合
     * @author 米立林
     */
    public List<SystemDistrict> getDistrictList() {
        try {
            String str = redisHelper.get(Constants.CACHE_SYSTEM_DISTRICT_KEY);
            ObjectMapper mapper = new ObjectMapper();

            if (StringUtil.isNullOrEmpty(str)) {
                List<SystemDistrict> districts = systemDistrictDao.pagerSystemDistrictList(0, 4000);
                String json = mapper.writeValueAsString(districts);
                redisHelper.save(Constants.CACHE_SYSTEM_DISTRICT_KEY, json);
                return districts;
            } else {
                return mapper.readValue(str, new TypeReference<List<SystemDistrict>>() {
                });
            }
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 省市区列表
     * Redis Key:Constants.CACHE_SYSTEM_DISTRICT_KEY
     *
     * @param districts 省市区集合
     * @author 米立林
     */
    public void savaDistrictList(List<SystemDistrict> districts) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(districts);
            redisHelper.save(Constants.CACHE_SYSTEM_DISTRICT_KEY, json);

        } catch (Exception ex) {

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

    /**
     * 牧场监控
     * Redis Key:CACHE_MONITOR_KEY
     *
     * @return 牧场监控列表
     * @author 米立林 2017-10-09
     */
    public List<SystemMonitor> getSystemMonitor() {
        try {
            String str = redisHelper.get(Constants.CACHE_MONITOR_KEY);
            ObjectMapper mapper = new ObjectMapper();

            if (StringUtil.isNullOrEmpty(str)) {
                List<SystemMonitor> monitors = systemMonitorDao.pagerSystemMonitorList(0, 1000);
                String json = mapper.writeValueAsString(monitors);
                redisHelper.save(Constants.CACHE_MONITOR_KEY, json);
                return monitors;
            } else {
                return mapper.readValue(str, new TypeReference<List<SystemMonitor>>() {
                });
            }
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 购羊提醒类型
     * Redis Key:CACHE_REMIND_TYPE_KEY
     *
     * @return 提醒类型集合
     * @author 米立林 2017-10-18
     */
    public List<NotifyTypeMongo> getNotifyType() {
        try {
            String str = redisHelper.get(Constants.CACHE_REMIND_TYPE_KEY);
            ObjectMapper mapper = new ObjectMapper();

            if (StringUtil.isNullOrEmpty(str)) {
                List<NotifyTypeMongo> notifyTypes = notifyTypeMongoDao.getList(new Query());
                String json = mapper.writeValueAsString(notifyTypes);
                redisHelper.save(Constants.CACHE_REMIND_TYPE_KEY, json);
                return notifyTypes;
            } else {
                return mapper.readValue(str, new TypeReference<List<NotifyTypeMongo>>() {
                });
            }
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 购羊提醒周期
     * Redis Key:CACHE_REMIND_CYCLE_KEY
     *
     * @return 提醒周期集合
     * @author 米立林 2017-10-18
     */
    public List<NotifyCycleMongo> getNotifyCycle() {
        try {
            String str = redisHelper.get(Constants.CACHE_REMIND_CYCLE_KEY);
            ObjectMapper mapper = new ObjectMapper();

            if (StringUtil.isNullOrEmpty(str)) {
                List<NotifyCycleMongo> notifyCycles = notifyCycleMongoDao.getList(new Query());
                String json = mapper.writeValueAsString(notifyCycles);
                redisHelper.save(Constants.CACHE_REMIND_CYCLE_KEY, json);
                return notifyCycles;
            } else {
                return mapper.readValue(str, new TypeReference<List<NotifyCycleMongo>>() {
                });
            }
        } catch (Exception ex) {
            return null;
        }
    }

}
