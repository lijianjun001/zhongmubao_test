package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.SystemSmsLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemSMSLogDao {
   
    SystemSmsLog getSystemSMSLogrById(@Param("id") int id);
    List<SystemSmsLog> pagerSystemSMSLogList(@Param("offset") int offset, @Param("limit") int limit);
	int insertSystemSMSLog(SystemSmsLog systemSmsLog);

}
