package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.ExBlackList;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface ExBlackListDao {

    ExBlackList getExBlackListrById(@Param("id") int id);

    List<ExBlackList> pagerExBlackListList(@Param("offset") int offset, @Param("limit") int limit);

    int insertExBlackList(ExBlackList exBlackList);

}
