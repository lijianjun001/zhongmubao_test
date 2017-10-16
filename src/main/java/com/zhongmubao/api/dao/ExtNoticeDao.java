package com.zhongmubao.api.dao;

import com.github.pagehelper.Page;
import com.zhongmubao.api.entity.ExtNotice;
import com.zhongmubao.api.entity.SystemDistrict;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface ExtNoticeDao {
   
    ExtNotice getExtNoticerById(@Param("id") int id);
    List<ExtNotice> pagerExtNoticeList(@Param("offset") int offset, @Param("limit") int limit);
	int insertExtNotice(ExtNotice extNotice);
    Page<ExtNotice> pageEffectiveExtNotice();

}
