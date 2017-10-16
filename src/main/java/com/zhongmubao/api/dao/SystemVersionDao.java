package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.SystemVersion;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface SystemVersionDao {
   
    SystemVersion getSystemVersionrById(@Param("id") int id);
    List<SystemVersion> pagerSystemVersionList(@Param("offset") int offset, @Param("limit") int limit);
	int insertSystemVersion(SystemVersion systemVersion);

}
