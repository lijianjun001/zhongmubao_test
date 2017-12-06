package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.CustomerSina;
import org.apache.ibatis.annotations.Param;

/**
 * 新浪客户数据
 *
 * author xy
 */
public interface CustomerSinaDao {

    /**
     * 根据customerId获取用户
     * @param customerId 客户customerId
     * @return 新浪客户
     */
    CustomerSina getCustomerSinaById(@Param("customerId") int customerId);
}
