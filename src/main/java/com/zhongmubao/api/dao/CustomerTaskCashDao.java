package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.CustomerTaskCash;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface CustomerTaskCashDao {
   
    CustomerTaskCash getCustomerTaskCashrById(@Param("id") int id);
    List<CustomerTaskCash> pagerCustomerTaskCashList(@Param("offset") int offset, @Param("limit") int limit);
	int insertCustomerTaskCash(CustomerTaskCash customerTaskCash);

}
