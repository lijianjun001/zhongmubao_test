package com.zhongmubao.api.service;

import com.zhongmubao.api.dto.request.PageIndexRequestModel;
import com.zhongmubao.api.dto.request.address.SystemDistrictRequestModel;
import com.zhongmubao.api.dto.request.SendSmsCodeRequestModel;
import com.zhongmubao.api.dto.response.address.ListSystemDistrictModel;
import com.zhongmubao.api.dto.response.notice.PageNoticeModel;
import com.zhongmubao.api.entity.Customer;

public interface SystemService {

    PageNoticeModel pageNotice(PageIndexRequestModel requestModel) throws Exception;
    ListSystemDistrictModel getSystemDistrictList(SystemDistrictRequestModel requestModel) throws Exception;
    void sendSmsCode(Customer customer,SendSmsCodeRequestModel model) throws Exception;
}
