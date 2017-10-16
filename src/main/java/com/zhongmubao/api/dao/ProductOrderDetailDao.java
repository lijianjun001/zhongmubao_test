package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.ProductOrderDetail;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface ProductOrderDetailDao {

    ProductOrderDetail getProductOrderDetailrById(@Param("id") int id);

    List<ProductOrderDetail> pagerProductOrderDetailList(@Param("offset") int offset, @Param("limit") int limit);

    int insertProductOrderDetail(ProductOrderDetail productOrderDetail);

}
