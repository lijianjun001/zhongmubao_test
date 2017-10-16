package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.ExtActivity;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface ExtActivityDao {
   
    ExtActivity getExtActivityrById(@Param("id") int id);
    List<ExtActivity> pagerExtActivityList(@Param("offset") int offset, @Param("limit") int limit);
	int insertExtActivity(ExtActivity extActivity);

}
