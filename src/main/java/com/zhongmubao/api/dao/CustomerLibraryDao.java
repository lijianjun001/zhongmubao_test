package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.CustomerLibrary;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface CustomerLibraryDao {
   
    CustomerLibrary getCustomerLibraryrById(@Param("id") int id);
    List<CustomerLibrary> pagerCustomerLibraryList(@Param("offset") int offset, @Param("limit") int limit);
	int insertCustomerLibrary(CustomerLibrary customerLibrary);

}
