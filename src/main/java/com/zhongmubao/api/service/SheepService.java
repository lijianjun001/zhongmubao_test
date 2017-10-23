package com.zhongmubao.api.service;

import com.zhongmubao.api.dto.request.OnlyPrimaryIdRequestModel;
import com.zhongmubao.api.dto.request.PageIndexRequestModel;
import com.zhongmubao.api.dto.request.ProjectPlanRequestModel;
import com.zhongmubao.api.dto.request.sheep.MySheepFoldRequestModel;
import com.zhongmubao.api.dto.request.sheep.SheepOrderRequestModel;
import com.zhongmubao.api.dto.request.SystemMonitorRequestModel;
import com.zhongmubao.api.dto.response.index.IndexModel;
import com.zhongmubao.api.dto.response.sheep.*;
import com.zhongmubao.api.dto.response.sheepfold.SheepProjectOrdersModel;
import com.zhongmubao.api.entity.Customer;

public interface SheepService {
    IndexModel index(Customer customer) throws Exception;

    PageSheepOrderModel pageSheepOrder(int customerId, SheepOrderRequestModel model) throws Exception;

    SheepOrderDetailModel sheepOrderDetail(int customerId, OnlyPrimaryIdRequestModel model) throws Exception;

    SheepVendorViewModel pastureDetail(int customerId, OnlyPrimaryIdRequestModel model) throws Exception;

    SheepProjectPlanViewModel sheepProjectPlan(int customerId, OnlyPrimaryIdRequestModel model) throws Exception;

    PageSheepStageModel sheepStage(int customerId, OnlyPrimaryIdRequestModel model) throws Exception;

    PageSheepOrderEarningsModel pageSheepOrderEarnings(int customerId, SheepOrderRequestModel model) throws Exception;

    MySheepfoldModel mySheepfold(int customerId, PageIndexRequestModel model) throws Exception;

    PastureMonitorModel pastureMonitor(int customerId, SystemMonitorRequestModel model) throws Exception;

    ProjectPlanModel projectPlan(ProjectPlanRequestModel model) throws Exception;

    MySheepFoldListViewModel mySheepFoldList(int customerId, MySheepFoldRequestModel model) throws Exception;

    MySheepFoldHeadViewModel mySheepFoldHead(int customerId) throws Exception;

    MySheepFoldHeadViewModel mySheepFoldRedeemedHead(int customerId) throws Exception;

    MySheepFoldRedeemedListViewModel mySheepFoldRedeemedList(int customerId, MySheepFoldRequestModel model) throws Exception;

    SheepProjectOrdersModel mySheepfoldSheepProjectOrders(int customerId, OnlyPrimaryIdRequestModel model) throws Exception;

    SheepProjectInsurance mySheepfoldProjectInsurance(int customerId, OnlyPrimaryIdRequestModel model) throws Exception;

    SheepRedeemableViewModel sheepRedeemableProperty(int customerId, OnlyPrimaryIdRequestModel model) throws Exception;


}
