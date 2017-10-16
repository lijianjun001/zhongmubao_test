package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.CustomerPackageOrder;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface CustomerPackageOrderDao {

    CustomerPackageOrder getCustomerPackageOrderrById(@Param("id") int id);

    List<CustomerPackageOrder> pagerCustomerPackageOrderList(@Param("offset") int offset, @Param("limit") int limit);

    int insertCustomerPackageOrder(CustomerPackageOrder customerPackageOrder);

}
