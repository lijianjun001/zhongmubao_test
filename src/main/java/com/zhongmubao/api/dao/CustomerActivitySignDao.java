package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.CustomerActivitySign;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface CustomerActivitySignDao {

    CustomerActivitySign getCustomerActivitySignrById(@Param("id") int id);

    List<CustomerActivitySign> pagerCustomerActivitySignList(@Param("offset") int offset, @Param("limit") int limit);

    int insertCustomerActivitySign(CustomerActivitySign customerActivitySign);

}
