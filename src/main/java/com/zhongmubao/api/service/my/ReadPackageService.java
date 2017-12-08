package com.zhongmubao.api.service.my;

import com.zhongmubao.api.dto.request.my.readpackage.ReadPackageDetailRequestModel;
import com.zhongmubao.api.dto.request.my.readpackage.ReadPackageGroupRequestModel;
import com.zhongmubao.api.dto.request.my.readpackage.ReadPackageListRequestModel;
import com.zhongmubao.api.dto.response.my.readpackage.ReadPackageDetailViewModel;
import com.zhongmubao.api.dto.response.my.readpackage.ReadPackageGroupViewModel;
import com.zhongmubao.api.dto.response.my.readpackage.ReadPackageListViewModel;
import com.zhongmubao.api.dto.response.my.readpackage.RedPackageHistoryViewModel;
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
    ReadPackageListViewModel readPackageGroupList(Customer customer, ReadPackageListRequestModel model) throws Exception;

    /**
     * 获取过期红包
     *
     * @param customer 客户
     * @param model    请求参数
     * @return RedPackageHistoryViewModel
     * @throws Exception
     */
    RedPackageHistoryViewModel readPackageHistory(Customer customer, ReadPackageGroupRequestModel model) throws Exception;

    /**
     * 红包详情
     *
     * @param customer 客户
     * @param model    请求参数
     * @return ReadPackageDetailViewModel
     * @throws Exception
     */
    ReadPackageDetailViewModel readPackageDetail(Customer customer, ReadPackageDetailRequestModel model) throws Exception;

    /**
     * 排序红包列表
     *
     * @param customer 客户
     * @param model    请求参数
     * @return ReadPackageListViewModel
     * @throws Exception
     */
    ReadPackageListViewModel readPackageList(Customer customer, ReadPackageListRequestModel model) throws Exception;

}