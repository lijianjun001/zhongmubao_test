package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.SystemToken;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface SystemTokenDao {

    SystemToken getSystemTokenrById(@Param("id") int id);

    List<SystemToken> pagerSystemTokenList(@Param("offset") int offset, @Param("limit") int limit);

    int insertSystemToken(SystemToken systemToken);

}
