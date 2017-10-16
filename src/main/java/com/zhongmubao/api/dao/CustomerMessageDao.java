package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.CustomerMessage;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface CustomerMessageDao {
   
    CustomerMessage getCustomerMessagerById(@Param("id") int id);
    List<CustomerMessage> pagerCustomerMessageList(@Param("offset") int offset, @Param("limit") int limit);
	int insertCustomerMessage(CustomerMessage customerMessage);

}
