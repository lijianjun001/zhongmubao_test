package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.CustomerHF;
import org.apache.ibatis.annotations.Param;

/**
 * 汇付客户数据
 *
 * @author xy
 */
public interface CustomerHFDao {
    /**
     * 根据customerId获取用户
     * @param customerId 客户customerId
     * @return 汇付客户
     */
    CustomerHF getCustomerHFById(@Param("customerId") int customerId);
}
