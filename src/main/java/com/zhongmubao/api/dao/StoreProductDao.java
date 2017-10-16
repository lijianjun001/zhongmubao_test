package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.StoreProduct;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface StoreProductDao {
   
    StoreProduct getStoreProductrById(@Param("id") int id);
    List<StoreProduct> pagerStoreProductList(@Param("offset") int offset, @Param("limit") int limit);
	int insertStoreProduct(StoreProduct storeProduct);

}
