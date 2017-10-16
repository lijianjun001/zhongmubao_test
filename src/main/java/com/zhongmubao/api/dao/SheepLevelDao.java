package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.SheepLevel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface SheepLevelDao {

    SheepLevel getSheepLevelrById(@Param("id") int id);

    List<SheepLevel> pagerSheepLevelList(@Param("offset") int offset, @Param("limit") int limit);

    int insertSheepLevel(SheepLevel sheepLevel);

}
