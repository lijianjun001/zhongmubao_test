package com.zhongmubao.api.service;

import com.zhongmubao.api.dto.request.my.readpackage.ReadPackageGroupRequestModel;
import com.zhongmubao.api.dto.response.my.readpackage.ReadPackageGroupViewModel;
import com.zhongmubao.api.entity.Customer;

/**
 * 我的红包
 *
 * @author 孙阿龙
 */
public interface MyReadPackageService {
    /**
     * 我的红包分组请求
     *
     * @param customer 客户
     * @param model    请求参数
     * @return ReadPackageGroupViewModel
     * @throws Exception 异常
     */
    ReadPackageGroupViewModel readPackageGroup(Customer customer, ReadPackageGroupRequestModel model) throws Exception;
}
