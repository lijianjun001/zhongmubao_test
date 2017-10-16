package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.SystemSMSLog;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface SystemSMSLogDao {
   
    SystemSMSLog getSystemSMSLogrById(@Param("id") int id);
    List<SystemSMSLog> pagerSystemSMSLogList(@Param("offset") int offset, @Param("limit") int limit);
	int insertSystemSMSLog(SystemSMSLog systemSMSLog);

}
