package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.CustomerRedeemLibraryLog;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface CustomerRedeemLibraryLogDao {
   
    CustomerRedeemLibraryLog getCustomerRedeemLibraryLogrById(@Param("id") int id);
    List<CustomerRedeemLibraryLog> pagerCustomerRedeemLibraryLogList(@Param("offset") int offset, @Param("limit") int limit);
	int insertCustomerRedeemLibraryLog(CustomerRedeemLibraryLog customerRedeemLibraryLog);

}
