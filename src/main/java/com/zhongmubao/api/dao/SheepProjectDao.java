package com.zhongmubao.api.dao;

import com.zhongmubao.api.entity.SheepProject;
import com.zhongmubao.api.entity.SheepProjectIndex;
import com.zhongmubao.api.entity.ext.PastureDetailExtModel;
import com.zhongmubao.api.entity.ext.SheepOrderInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface SheepProjectDao {

    SheepProject getSheepProjectById(@Param("id") int id);

    List<SheepProject> pagerSheepProjectList(@Param("offset") int offset, @Param("limit") int limit);

    int insertSheepProject(SheepProject sheepProject);

    List<SheepProjectIndex> getIndexSheepProject(@Param("paidState") String paidState, @Param("noPayState") String noPayState, @Param("beginTime") Date beginTime);

    PastureDetailExtModel getPastureDetailById(@Param("id") int id);

    SheepOrderInfo getSheepProjectByIdAndCustomerId(@Param("projectId") int projectId, @Param("customerId") int customerId, @Param("states") List<String> states);
}
