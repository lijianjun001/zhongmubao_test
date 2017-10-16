package com.zhongmubao.api.service;

import com.zhongmubao.api.dto.Request.PageNoticeRequestModel;
import com.zhongmubao.api.dto.Request.Address.SystemDistrictRequestModel;
import com.zhongmubao.api.dto.Request.SendSmsCodeRequestModel;
import com.zhongmubao.api.dto.Response.Address.ListSystemDistrictModel;
import com.zhongmubao.api.dto.Response.Notice.PageNoticeModel;
import com.zhongmubao.api.entity.Customer;

public interface SystemService {

    PageNoticeModel pageNotice(PageNoticeRequestModel requestModel) throws Exception;
    ListSystemDistrictModel getSystemDistrictList(SystemDistrictRequestModel requestModel) throws Exception;
    void sendSmsCode(Customer customer,SendSmsCodeRequestModel model) throws Exception;
}