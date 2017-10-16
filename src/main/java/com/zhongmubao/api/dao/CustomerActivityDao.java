package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.CustomerActivity;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface CustomerActivityDao {

    CustomerActivity getCustomerActivityrById(@Param("id") int id);

    List<CustomerActivity> pagerCustomerActivityList(@Param("offset") int offset, @Param("limit") int limit);

    int insertCustomerActivity(CustomerActivity customerActivity);

}
