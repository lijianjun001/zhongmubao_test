package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.StoreOrderDetails;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface StoreOrderDetailsDao {
   
    StoreOrderDetails getStoreOrderDetailsrById(@Param("id") int id);
    List<StoreOrderDetails> pagerStoreOrderDetailsList(@Param("offset") int offset, @Param("limit") int limit);
	int insertStoreOrderDetails(StoreOrderDetails storeOrderDetails);

}
