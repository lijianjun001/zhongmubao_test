package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.StoreCategory;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface StoreCategoryDao {
   
    StoreCategory getStoreCategoryrById(@Param("id") int id);
    List<StoreCategory> pagerStoreCategoryList(@Param("offset") int offset, @Param("limit") int limit);
	int insertStoreCategory(StoreCategory storeCategory);

}
