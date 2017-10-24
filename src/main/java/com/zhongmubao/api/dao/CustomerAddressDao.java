package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.CustomerAddress;
import org.apache.ibatis.annotations.Param;

/**
 * 客户地址数据层
 *
 * @author 孙阿龙
 */
public interface CustomerAddressDao {

    /**
     * 根据用户id获取客户地址
     * @param customerId 用户id
     * @return 客户地址
     */
    CustomerAddress getCustomerAddressByCustomerId(@Param("customerId") int customerId);
}
