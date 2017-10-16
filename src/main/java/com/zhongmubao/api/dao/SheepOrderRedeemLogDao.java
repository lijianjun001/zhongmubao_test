package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.SheepOrderRedeemLog;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface SheepOrderRedeemLogDao {

    SheepOrderRedeemLog getSheepOrderRedeemLogrById(@Param("id") int id);

    List<SheepOrderRedeemLog> pagerSheepOrderRedeemLogList(@Param("offset") int offset, @Param("limit") int limit);

    int insertSheepOrderRedeemLog(SheepOrderRedeemLog sheepOrderRedeemLog);

}
