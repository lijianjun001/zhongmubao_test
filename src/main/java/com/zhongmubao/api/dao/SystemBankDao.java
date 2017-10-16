package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.SystemBank;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface SystemBankDao {

    SystemBank getSystemBankrById(@Param("id") int id);

    List<SystemBank> pagerSystemBankList(@Param("offset") int offset, @Param("limit") int limit);

    int insertSystemBank(SystemBank systemBank);

}
