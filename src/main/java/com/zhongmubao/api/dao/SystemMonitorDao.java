package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.SystemMonitor;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface SystemMonitorDao {

    SystemMonitor getSystemMonitorrById(@Param("id") int id);

    List<SystemMonitor> pagerSystemMonitorList(@Param("offset") int offset, @Param("limit") int limit);

    int insertSystemMonitor(SystemMonitor systemMonitor);

}
