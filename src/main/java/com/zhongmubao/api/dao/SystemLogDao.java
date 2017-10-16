package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.SystemLog;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface SystemLogDao {
   
    SystemLog getSystemLogrById(@Param("id") int id);
    List<SystemLog> pagerSystemLogList(@Param("offset") int offset, @Param("limit") int limit);
	int insertSystemLog(SystemLog systemLog);

}
