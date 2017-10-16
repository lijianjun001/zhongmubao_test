package com.zhongmubao.api.dao;

import com.github.pagehelper.Page;
import com.zhongmubao.api.dto.Request.OnlyPrimaryIdRequestModel;
import com.zhongmubao.api.dto.Response.Sheep.SheepVendorViewModel;
import com.zhongmubao.api.entity.SheepOrder;
import com.zhongmubao.api.entity.ext.SheepOrderCountAndMinCreated;
import com.zhongmubao.api.entity.ext.SheepOrderDetailInfo;
import com.zhongmubao.api.entity.ext.SheepOrderInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SheepOrderDao {

    SheepOrder getSheepOrderrById(@Param("id") int id);

    List<SheepOrder> pagerSheepOrderList(@Param("offset") int offset, @Param("limit") int limit);

    int insertSheepOrder(SheepOrder sheepOrder);

    int sumSheepOrderCountByCustomerAndStates(@Param("customerId") int customerId, @Param("states") List<String> states);

    SheepOrderCountAndMinCreated getSheepOrderCountAndMinCreatedByCustomerAndStatesAndProjectType(@Param("customerId") int customerId, @Param("states") List<String> states, @Param("projectType") String projectType);

    int countSheepOrderByCustomerIdAndBeginTimeAndEndTimeAndState(@Param("customerId") int customerId, @Param("beginTime") Date beginTime, @Param("endTime") Date endTime, @Param("states") List<String> states);

    Page<SheepOrderInfo> pageSheepOrderByCustomerId(@Param("customerId") int customerId, @Param("states") List<String> states);

    SheepOrderDetailInfo GetDetailByIdAndCustomerId(@Param("customerId") int customerId, @Param("id") int id);
}
