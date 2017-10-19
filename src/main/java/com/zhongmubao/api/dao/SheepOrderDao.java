package com.zhongmubao.api.dao;

import com.github.pagehelper.Page;
import com.zhongmubao.api.dto.Request.OnlyPrimaryIdRequestModel;
import com.zhongmubao.api.dto.Response.Sheep.SheepVendorViewModel;
import com.zhongmubao.api.entity.SheepOrder;
import com.zhongmubao.api.entity.ext.SheepOrderCountAndMinCreated;
import com.zhongmubao.api.entity.ext.SheepOrderDetailInfo;
import com.zhongmubao.api.entity.ext.SheepOrderEarnings;
import com.zhongmubao.api.entity.ext.SheepOrderInfo;
import com.zhongmubao.api.entity.ext.MySheepFoldItem;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SheepOrderDao {
   
    SheepOrder getSheepOrderrById(@Param("id") int id);
    List<SheepOrder> pagerSheepOrderList(@Param("offset") int offset, @Param("limit") int limit);
	int insertSheepOrder(SheepOrder sheepOrder);
    int sumSheepOrderCountByCustomerAndStates(@Param("customerId") int customerId,@Param("states") List<String> states);
    SheepOrderCountAndMinCreated getSheepOrderCountAndMinCreatedByCustomerAndStatesAndProjectType(@Param("customerId") int customerId, @Param("states") List<String> states, @Param("projectType") String projectType);
    int countSheepOrderByCustomerIdAndBeginTimeAndEndTimeAndState(@Param("customerId") int customerId, @Param("beginTime") Date beginTime, @Param("endTime") Date endTime, @Param("states") List<String> states);
    Page<SheepOrderInfo> pageSheepOrderByCustomerId(@Param("customerId") int customerId, @Param("states") List<String> states);
    SheepOrderDetailInfo getDetailByIdAndCustomerId(@Param("customerId") int customerId, @Param("id") int id);
    Page<SheepOrderEarnings> pageSheepOrderByCustomerIdAndState(@Param("customerId") int customerId, @Param("states") List<String> states);
    /**
     * 根据标期分组 GROUP BY ProjectId
     * @param customerId 当前用户
     * @param states 订单状态
     * @return 标期订单列表
     * @author 米立林 2017-10-09
     */
    Page<SheepOrderInfo> pageSheepOrderByCustomerIdGroupByProjectId(@Param("customerId") int customerId, @Param("states") List<String> states);

    List<MySheepFoldItem> mySheepFoldList(@Param("customerId") int customerId, @Param("states") List<String> states,@Param("pagestart") int pagestart,@Param("pageend") int pageend,@Param("type") String type);
    int mySheepFoldListCount(@Param("customerId") int customerId, @Param("states") List<String> states,@Param("type") String type);
    int mySheepFoldListSumCount(@Param("customerId") int customerId, @Param("states") List<String> states,@Param("type") String type);

}
