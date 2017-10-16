package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.CustomerPackageOrderDetails;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface CustomerPackageOrderDetailsDao {
   
    CustomerPackageOrderDetails getCustomerPackageOrderDetailsrById(@Param("id") int id);
    List<CustomerPackageOrderDetails> pagerCustomerPackageOrderDetailsList(@Param("offset") int offset, @Param("limit") int limit);
	int insertCustomerPackageOrderDetails(CustomerPackageOrderDetails customerPackageOrderDetails);

}
