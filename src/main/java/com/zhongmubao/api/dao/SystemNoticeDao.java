package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.SystemNotice;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface SystemNoticeDao {
   
    SystemNotice getSystemNoticerById(@Param("id") int id);
    List<SystemNotice> pagerSystemNoticeList(@Param("offset") int offset, @Param("limit") int limit);
	int insertSystemNotice(SystemNotice systemNotice);

}
