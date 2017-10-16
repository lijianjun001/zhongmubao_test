package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface ProductDao {

    Product getProductrById(@Param("id") int id);

    List<Product> pagerProductList(@Param("offset") int offset, @Param("limit") int limit);

    int insertProduct(Product product);

}
