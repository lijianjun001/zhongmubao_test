package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.CustomerLibraryOrder;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface CustomerLibraryOrderDao {

    CustomerLibraryOrder getCustomerLibraryOrderrById(@Param("id") int id);

    List<CustomerLibraryOrder> pagerCustomerLibraryOrderList(@Param("offset") int offset, @Param("limit") int limit);

    int insertCustomerLibraryOrder(CustomerLibraryOrder customerLibraryOrder);

}
