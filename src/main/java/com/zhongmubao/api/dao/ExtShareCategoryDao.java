package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.ExtShareCategory;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface ExtShareCategoryDao {
   
    ExtShareCategory getExtShareCategoryrById(@Param("id") int id);
    List<ExtShareCategory> pagerExtShareCategoryList(@Param("offset") int offset, @Param("limit") int limit);
	int insertExtShareCategory(ExtShareCategory extShareCategory);

}
