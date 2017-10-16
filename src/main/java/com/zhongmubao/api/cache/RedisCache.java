package com.zhongmubao.api.cache;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhongmubao.api.config.Constants;
import com.zhongmubao.api.dao.SheepLevelDao;
import com.zhongmubao.api.dao.SheepVendorDao;
import com.zhongmubao.api.dao.SystemDistrictDao;
import com.zhongmubao.api.entity.SheepLevel;
import com.zhongmubao.api.entity.SheepVendor;
import com.zhongmubao.api.entity.SystemDistrict;
import com.zhongmubao.api.util.DateUtil;
import com.zhongmubao.api.util.StringUtil;
import com.zhongmubao.api.util.redis.RedisHelper;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final SystemDistrictDao systemDistrictDao;

    public RedisCache(SystemDistrictDao systemDistrictDao) {
        this.systemDistrictDao = systemDistrictDao;
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
            if (!str.equals(value)) return false;
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
     * 获取所有省市区列表
     * Redis Key:Constants.CACHE_SYSTEM_DISTRICT_KEY
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
     * 缓存省市区列表
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
     * 获取客户等级
     * Redis Key:CACHE_CUSTOMER_LEVEL_KEY
     * @return 省市区集合
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
