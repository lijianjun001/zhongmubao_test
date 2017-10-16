package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.Store;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface StoreDao {
   
    Store getStorerById(@Param("id") int id);
    List<Store> pagerStoreList(@Param("offset") int offset, @Param("limit") int limit);
	int insertStore(Store store);

}
