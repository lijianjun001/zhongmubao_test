package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.ProductProject;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface ProductProjectDao {

    ProductProject getProductProjectrById(@Param("id") int id);

    List<ProductProject> pagerProductProjectList(@Param("offset") int offset, @Param("limit") int limit);

    int insertProductProject(ProductProject productProject);

}
