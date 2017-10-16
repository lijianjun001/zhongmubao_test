package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.CustomerAutoRedeemLog;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface CustomerAutoRedeemLogDao {

    CustomerAutoRedeemLog getCustomerAutoRedeemLogrById(@Param("id") int id);

    List<CustomerAutoRedeemLog> pagerCustomerAutoRedeemLogList(@Param("offset") int offset, @Param("limit") int limit);

    int insertCustomerAutoRedeemLog(CustomerAutoRedeemLog customerAutoRedeemLog);

}
