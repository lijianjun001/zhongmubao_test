package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.SystemConfig;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface SystemConfigDao {

    SystemConfig getSystemConfigrById(@Param("id") int id);

    List<SystemConfig> pagerSystemConfigList(@Param("offset") int offset, @Param("limit") int limit);

    int insertSystemConfig(SystemConfig systemConfig);

}
