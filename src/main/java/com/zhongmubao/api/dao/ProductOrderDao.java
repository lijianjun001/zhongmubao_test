package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.ProductOrder;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface ProductOrderDao {

    ProductOrder getProductOrderrById(@Param("id") int id);

    List<ProductOrder> pagerProductOrderList(@Param("offset") int offset, @Param("limit") int limit);

    int insertProductOrder(ProductOrder productOrder);

}
