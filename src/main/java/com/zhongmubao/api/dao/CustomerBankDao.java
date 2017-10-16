package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.CustomerBank;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface CustomerBankDao {

    CustomerBank getCustomerBankrById(@Param("id") int id);

    List<CustomerBank> pagerCustomerBankList(@Param("offset") int offset, @Param("limit") int limit);

    int insertCustomerBank(CustomerBank customerBank);

}
