package com.zhongmubao.api.service.impl.my;

import com.zhongmubao.api.dto.request.my.readpackage.ReadPackageGroupRequestModel;
import com.zhongmubao.api.dto.request.my.readpackage.ReadPackageListRequestModel;
import com.zhongmubao.api.dto.response.my.readpackage.ReadPackageGroupViewModel;
import com.zhongmubao.api.entity.Customer;
import com.zhongmubao.api.service.my.ReadPackageService;

/**
 * 我的红包
 *
 * @author 孙阿龙
 */
public class ReadPackageServiceImpl implements ReadPackageService {
    @Override
    public ReadPackageGroupViewModel readPackageGroup(Customer customer, ReadPackageGroupRequestModel model) throws Exception {
        return null;
    }

    @Override
    public ReadPackageGroupViewModel readPackageList(Customer customer, ReadPackageListRequestModel model) throws Exception {
        return null;
    }
}
