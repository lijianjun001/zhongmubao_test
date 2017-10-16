package com.zhongmubao.api.service;

import com.zhongmubao.api.dto.Request.OnlyPrimaryIdRequestModel;
import com.zhongmubao.api.dto.Request.Sheep.SheepOrderRequestModel;
import com.zhongmubao.api.dto.Response.Index.IndexModel;
import com.zhongmubao.api.dto.Response.Sheep.PageSheepOrderModel;
import com.zhongmubao.api.dto.Response.Sheep.SheepOrderDetailModel;
import com.zhongmubao.api.dto.Response.Sheep.SheepVendorViewModel;
import com.zhongmubao.api.entity.Customer;

public interface SheepService {
    IndexModel index(Customer customer) throws Exception;

    PageSheepOrderModel pageSheepOrder(int customerId, SheepOrderRequestModel model) throws Exception;

    SheepOrderDetailModel GetDetailByIdAndCustomerId(int customerId, OnlyPrimaryIdRequestModel model) throws Exception;

    SheepVendorViewModel GetPastureDetailById(int customerId, OnlyPrimaryIdRequestModel model) throws Exception;
}
