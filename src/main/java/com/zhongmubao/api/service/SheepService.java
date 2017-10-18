package com.zhongmubao.api.service;

import com.zhongmubao.api.dto.Request.OnlyPrimaryIdRequestModel;
import com.zhongmubao.api.dto.Request.ProjectPlanRequestModel;
import com.zhongmubao.api.dto.Request.Sheep.MySheepFoldRequestModel;
import com.zhongmubao.api.dto.Request.Sheep.SheepOrderRequestModel;
import com.zhongmubao.api.dto.Request.SystemMonitorRequestModel;
import com.zhongmubao.api.dto.Response.Index.IndexModel;
import com.zhongmubao.api.dto.Response.Sheep.*;
import com.zhongmubao.api.entity.Customer;

public interface SheepService {
    IndexModel index(Customer customer) throws Exception;
    PageSheepOrderModel pageSheepOrder(int customerId, SheepOrderRequestModel model) throws Exception;
    SheepOrderDetailModel sheepOrderDetail(int customerId, OnlyPrimaryIdRequestModel model) throws Exception;
    SheepVendorViewModel pastureDetail(int customerId, OnlyPrimaryIdRequestModel model) throws Exception;
    SheepProjectPlanViewModel sheepProjectPlan(int customerId, OnlyPrimaryIdRequestModel model) throws Exception;
    PageSheepStageModel sheepStage(int customerId, OnlyPrimaryIdRequestModel model) throws Exception;
    PageSheepOrderEarningsModel pageSheepOrderEarnings(int customerId, SheepOrderRequestModel model) throws Exception;
    MySheepfoldModel mySheepfold(int customerId,int pageIndex) throws Exception;
    PastureMonitorModel pastureMonitor(int customerId, SystemMonitorRequestModel model) throws Exception;
    ProjectPlanModel projectPlan(ProjectPlanRequestModel model) throws Exception;


    MySheepFoldListViewModel mySheepFoldList(int customerId, MySheepFoldRequestModel model) throws Exception;

}
