package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.SystemDistrict;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface SystemDistrictDao {
   
    SystemDistrict getSystemDistrictrById(@Param("id") int id);
    List<SystemDistrict> pagerSystemDistrictList(@Param("offset") int offset, @Param("limit") int limit);
	int insertSystemDistrict(SystemDistrict systemDistrict);

}
