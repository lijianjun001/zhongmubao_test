package com.zhongmubao.api.service;

import com.zhongmubao.api.dto.Request.PageNoticeRequestModel;
import com.zhongmubao.api.dto.Request.Address.SystemDistriceRequestModel;
import com.zhongmubao.api.dto.Response.Address.ListSystemDistrictModel;
import com.zhongmubao.api.dto.Response.Notice.PageNoticeModel;
import com.zhongmubao.api.entity.SystemDistrict;

import java.util.List;

public interface SystemService {

    PageNoticeModel pageNotice(PageNoticeRequestModel requestModel) throws Exception;

    ListSystemDistrictModel GetSystemDistrictByParentCode(SystemDistriceRequestModel requestModel) throws Exception;
}
