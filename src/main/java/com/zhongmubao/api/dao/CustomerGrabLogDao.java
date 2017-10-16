package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.CustomerGrabLog;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface CustomerGrabLogDao {

    CustomerGrabLog getCustomerGrabLogrById(@Param("id") int id);

    List<CustomerGrabLog> pagerCustomerGrabLogList(@Param("offset") int offset, @Param("limit") int limit);

    int insertCustomerGrabLog(CustomerGrabLog customerGrabLog);

}
