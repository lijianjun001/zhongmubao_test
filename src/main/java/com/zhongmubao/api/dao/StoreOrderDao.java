package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.StoreOrder;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface StoreOrderDao {

    StoreOrder getStoreOrderrById(@Param("id") int id);

    List<StoreOrder> pagerStoreOrderList(@Param("offset") int offset, @Param("limit") int limit);

    int insertStoreOrder(StoreOrder storeOrder);

}
