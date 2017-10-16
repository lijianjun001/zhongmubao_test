package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.SystemTaskLog;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface SystemTaskLogDao {
   
    SystemTaskLog getSystemTaskLogrById(@Param("id") int id);
    List<SystemTaskLog> pagerSystemTaskLogList(@Param("offset") int offset, @Param("limit") int limit);
	int insertSystemTaskLog(SystemTaskLog systemTaskLog);

}
