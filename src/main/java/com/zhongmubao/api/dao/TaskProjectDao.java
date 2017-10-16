package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.TaskProject;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface TaskProjectDao {
   
    TaskProject getTaskProjectrById(@Param("id") int id);
    List<TaskProject> pagerTaskProjectList(@Param("offset") int offset, @Param("limit") int limit);
	int insertTaskProject(TaskProject taskProject);

}
