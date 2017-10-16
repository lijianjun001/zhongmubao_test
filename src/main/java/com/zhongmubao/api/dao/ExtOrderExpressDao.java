package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.ExtOrderExpress;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface ExtOrderExpressDao {

    ExtOrderExpress getExtOrderExpressrById(@Param("id") int id);

    List<ExtOrderExpress> pagerExtOrderExpressList(@Param("offset") int offset, @Param("limit") int limit);

    int insertExtOrderExpress(ExtOrderExpress extOrderExpress);

}
