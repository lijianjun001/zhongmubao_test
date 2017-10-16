package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.CustomerTransfer;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface CustomerTransferDao {
   
    CustomerTransfer getCustomerTransferrById(@Param("id") int id);
    List<CustomerTransfer> pagerCustomerTransferList(@Param("offset") int offset, @Param("limit") int limit);
	int insertCustomerTransfer(CustomerTransfer customerTransfer);

}
