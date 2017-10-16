package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.SheepBonus;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface SheepBonusDao {

    SheepBonus getSheepBonusrById(@Param("id") int id);

    List<SheepBonus> pagerSheepBonusList(@Param("offset") int offset, @Param("limit") int limit);

    int insertSheepBonus(SheepBonus sheepBonus);

}
