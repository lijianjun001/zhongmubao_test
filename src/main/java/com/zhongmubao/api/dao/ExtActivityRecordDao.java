package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.ExtActivityRecord;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface ExtActivityRecordDao {
   
    ExtActivityRecord getExtActivityRecordrById(@Param("id") int id);
    ExtActivityRecord getExtActivityRecordrByCustomerIdAndActivityId(@Param("customerId") int customerId,@Param("activityId") int activityId);
    List<ExtActivityRecord> pagerExtActivityRecordList(@Param("offset") int offset, @Param("limit") int limit);
	int insertExtActivityRecord(ExtActivityRecord extActivityRecord);
}
