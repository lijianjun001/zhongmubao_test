package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.SystemMessageTemplate;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface SystemMessageTemplateDao {
   
    SystemMessageTemplate getSystemMessageTemplaterById(@Param("id") int id);
    List<SystemMessageTemplate> pagerSystemMessageTemplateList(@Param("offset") int offset, @Param("limit") int limit);
	int insertSystemMessageTemplate(SystemMessageTemplate systemMessageTemplate);

}
