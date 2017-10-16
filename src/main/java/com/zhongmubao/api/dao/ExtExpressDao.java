package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.ExtExpress;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface ExtExpressDao {
   
    ExtExpress getExtExpressrById(@Param("id") int id);
    List<ExtExpress> pagerExtExpressList(@Param("offset") int offset, @Param("limit") int limit);
	int insertExtExpress(ExtExpress extExpress);

}
