package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.SheepVendorMoneyHistory;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface SheepVendorMoneyHistoryDao {
   
    SheepVendorMoneyHistory getSheepVendorMoneyHistoryrById(@Param("id") int id);
    List<SheepVendorMoneyHistory> pagerSheepVendorMoneyHistoryList(@Param("offset") int offset, @Param("limit") int limit);
	int insertSheepVendorMoneyHistory(SheepVendorMoneyHistory sheepVendorMoneyHistory);

}
