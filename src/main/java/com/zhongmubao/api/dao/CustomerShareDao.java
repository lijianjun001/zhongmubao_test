package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.CustomerShare;
import com.zhongmubao.api.util.datasource.DataSource;

/**
 * 签到分享记录
 * @author 米立林
 */
public interface CustomerShareDao {
    /**
     * 添加分享
     * @param customerShare 实体
     * @return 0 1
     */
    //@DataSource("slave")
    int insertCustomerShare(CustomerShare customerShare);
}
