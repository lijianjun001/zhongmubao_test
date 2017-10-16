package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.SystemAdminLog;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface SystemAdminLogDao {

    SystemAdminLog getSystemAdminLogrById(@Param("id") int id);

    List<SystemAdminLog> pagerSystemAdminLogList(@Param("offset") int offset, @Param("limit") int limit);

    int insertSystemAdminLog(SystemAdminLog systemAdminLog);

}
