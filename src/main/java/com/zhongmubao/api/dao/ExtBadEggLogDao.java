package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.ExtBadEggLog;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface ExtBadEggLogDao {

    ExtBadEggLog getExtBadEggLogrById(@Param("id") int id);

    List<ExtBadEggLog> pagerExtBadEggLogList(@Param("offset") int offset, @Param("limit") int limit);

    int insertExtBadEggLog(ExtBadEggLog extBadEggLog);

}
