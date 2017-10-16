package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.TaskReminder;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface TaskReminderDao {
   
    TaskReminder getTaskReminderrById(@Param("id") int id);
    List<TaskReminder> pagerTaskReminderList(@Param("offset") int offset, @Param("limit") int limit);
	int insertTaskReminder(TaskReminder taskReminder);

}
