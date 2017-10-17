package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.SheepProjectPlan;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface SheepProjectPlanDao {
   
    SheepProjectPlan getSheepProjectPlanById(@Param("id") int id);
    List<SheepProjectPlan> pagerSheepProjectPlanList(@Param("offset") int offset, @Param("limit") int limit);
	int insertSheepProjectPlan(SheepProjectPlan sheepProjectPlan);
    SheepProjectPlan lastSheepProjectPlan(int id);
}
