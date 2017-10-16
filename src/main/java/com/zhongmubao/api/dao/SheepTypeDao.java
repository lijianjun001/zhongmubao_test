package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.SheepType;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface SheepTypeDao {

    SheepType getSheepTyperById(@Param("id") int id);

    List<SheepType> pagerSheepTypeList(@Param("offset") int offset, @Param("limit") int limit);

    int insertSheepType(SheepType sheepType);

}
