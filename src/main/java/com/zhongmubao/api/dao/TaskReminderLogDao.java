package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.TaskReminderLog;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface TaskReminderLogDao {
   
    TaskReminderLog getTaskReminderLogrById(@Param("id") int id);
    List<TaskReminderLog> pagerTaskReminderLogList(@Param("offset") int offset, @Param("limit") int limit);
	int insertTaskReminderLog(TaskReminderLog taskReminderLog);

}
