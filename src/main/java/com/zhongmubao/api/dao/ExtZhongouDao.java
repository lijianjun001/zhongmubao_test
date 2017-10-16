package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.ExtZhongou;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface ExtZhongouDao {
   
    ExtZhongou getExtZhongourById(@Param("id") int id);
    List<ExtZhongou> pagerExtZhongouList(@Param("offset") int offset, @Param("limit") int limit);
	int insertExtZhongou(ExtZhongou extZhongou);

}
