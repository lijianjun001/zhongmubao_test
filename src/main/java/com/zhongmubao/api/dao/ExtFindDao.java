package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.ExtFind;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface ExtFindDao {
   
    ExtFind getExtFindrById(@Param("id") int id);
    List<ExtFind> pagerExtFindList(@Param("offset") int offset, @Param("limit") int limit);
	int insertExtFind(ExtFind extFind);

}
