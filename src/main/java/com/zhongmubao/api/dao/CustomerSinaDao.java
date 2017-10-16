package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.CustomerSina;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface CustomerSinaDao {
   
    CustomerSina getCustomerSinaById(@Param("id") int id);
    CustomerSina getCustomerSinaByCustomerId(@Param("customerId") int customerId);
    List<CustomerSina> pagerCustomerSinaList(@Param("offset") int offset, @Param("limit") int limit);
	int insertCustomerSina(CustomerSina customerSina);

}
