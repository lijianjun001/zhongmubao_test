package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.ExtSmashegg;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface ExtSmasheggDao {
   
    ExtSmashegg getExtSmasheggrById(@Param("id") int id);
    List<ExtSmashegg> pagerExtSmasheggList(@Param("offset") int offset, @Param("limit") int limit);
	int insertExtSmashegg(ExtSmashegg extSmashegg);

}
