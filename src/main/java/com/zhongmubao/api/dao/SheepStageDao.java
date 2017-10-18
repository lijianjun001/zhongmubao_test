package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.SheepStage;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface SheepStageDao {
   
    SheepStage getSheepStagerById(@Param("id") int id);
    List<SheepStage> pagerSheepStageList(@Param("offset") int offset, @Param("limit") int limit);
	int insertSheepStage(SheepStage sheepStage);
    List<SheepStage> getAllSheepStage();
}
