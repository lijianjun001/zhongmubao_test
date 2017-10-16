package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.CustomerMoneyBoxHistory;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface CustomerMoneyBoxHistoryDao {
   
    CustomerMoneyBoxHistory getCustomerMoneyBoxHistoryrById(@Param("id") int id);
    List<CustomerMoneyBoxHistory> pagerCustomerMoneyBoxHistoryList(@Param("offset") int offset, @Param("limit") int limit);
	int insertCustomerMoneyBoxHistory(CustomerMoneyBoxHistory customerMoneyBoxHistory);

}
