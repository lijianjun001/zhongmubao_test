package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.CustomerGiftOrder;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface CustomerGiftOrderDao {

    CustomerGiftOrder getCustomerGiftOrderrById(@Param("id") int id);

    List<CustomerGiftOrder> pagerCustomerGiftOrderList(@Param("offset") int offset, @Param("limit") int limit);

    int insertCustomerGiftOrder(CustomerGiftOrder customerGiftOrder);

}
