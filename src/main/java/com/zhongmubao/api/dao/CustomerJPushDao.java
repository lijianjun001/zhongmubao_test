package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.CustomerJPush;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface CustomerJPushDao {

    CustomerJPush getCustomerJPushrById(@Param("id") int id);

    List<CustomerJPush> pagerCustomerJPushList(@Param("offset") int offset, @Param("limit") int limit);

    int insertCustomerJPush(CustomerJPush customerJPush);

}
