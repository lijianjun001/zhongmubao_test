package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.CustomerDepositLog;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface CustomerDepositLogDao {
   
    CustomerDepositLog getCustomerDepositLogrById(@Param("id") int id);
    List<CustomerDepositLog> pagerCustomerDepositLogList(@Param("offset") int offset, @Param("limit") int limit);
	int insertCustomerDepositLog(CustomerDepositLog customerDepositLog);

}
