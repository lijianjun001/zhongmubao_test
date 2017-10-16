package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.ExtRedPackageHistory;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface ExtRedPackageHistoryDao {
   
    ExtRedPackageHistory getExtRedPackageHistoryrById(@Param("id") int id);
    List<ExtRedPackageHistory> pagerExtRedPackageHistoryList(@Param("offset") int offset, @Param("limit") int limit);
	int insertExtRedPackageHistory(ExtRedPackageHistory extRedPackageHistory);

}
