package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.CustomerGrabLibraryLog;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface CustomerGrabLibraryLogDao {
   
    CustomerGrabLibraryLog getCustomerGrabLibraryLogrById(@Param("id") int id);
    List<CustomerGrabLibraryLog> pagerCustomerGrabLibraryLogList(@Param("offset") int offset, @Param("limit") int limit);
	int insertCustomerGrabLibraryLog(CustomerGrabLibraryLog customerGrabLibraryLog);

}
