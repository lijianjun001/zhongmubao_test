package com.zhongmubao.api.service.my;

import com.zhongmubao.api.dto.request.my.readpackage.ReadPackageGroupRequestModel;
import com.zhongmubao.api.dto.request.my.readpackage.ReadPackageListRequestModel;
import com.zhongmubao.api.dto.response.my.readpackage.ReadPackageGroupViewModel;
import com.zhongmubao.api.dto.response.my.readpackage.ReadPackageListViewModel;
import com.zhongmubao.api.dto.response.my.readpackage.RedPackageExpiredViewModel;
import com.zhongmubao.api.entity.Customer;

/**
 * 我的红包
 *
 * @author 孙阿龙
 */
public interface ReadPackageService {
    /**
     * 我的红包分组请求
     *
     * @param customer 客户
     * @param model    请求参数
     * @return ReadPackageGroupViewModel
     * @throws Exception 异常
     */
    ReadPackageGroupViewModel readPackageGroup(Customer customer, ReadPackageGroupRequestModel model) throws Exception;

    /**
     * 分组红包列表
     *
     * @param customer 客户
     * @param model    请求参数
     * @return ReadPackageListViewModel
     * @throws Exception
     */
    ReadPackageListViewModel readPackageList(Customer customer, ReadPackageListRequestModel model) throws Exception;

    /**
     * 获取过期红包
     *
     * @param customer 客户
     * @param model    请求参数
     * @return RedPackageExpiredViewModel
     * @throws Exception
     */
    RedPackageExpiredViewModel readPackageExpired(Customer customer, ReadPackageGroupRequestModel model) throws Exception;
}
