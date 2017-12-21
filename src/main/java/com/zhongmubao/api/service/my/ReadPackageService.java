package com.zhongmubao.api.service.my;

import com.zhongmubao.api.dto.request.my.redpackage.RedPackageDetailRequestModel;
import com.zhongmubao.api.dto.request.my.redpackage.RedPackageGroupRequestModel;
import com.zhongmubao.api.dto.request.my.redpackage.RedPackageHistoryRequestModel;
import com.zhongmubao.api.dto.request.my.redpackage.RedPackageListRequestModel;
import com.zhongmubao.api.dto.response.my.redpackage.RedPackageDetailViewModel;
import com.zhongmubao.api.dto.response.my.redpackage.RedPackageGroupViewModel;
import com.zhongmubao.api.dto.response.my.redpackage.RedPackageListViewModel;
import com.zhongmubao.api.dto.response.my.redpackage.RedPackageHistoryViewModel;
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
     * @return RedPackageGroupViewModel
     * @throws Exception 异常
     */
    RedPackageGroupViewModel readPackageGroup(Customer customer, RedPackageGroupRequestModel model) throws Exception;

    /**
     * 分组红包列表
     *
     * @param customer 客户
     * @param model    请求参数
     * @return RedPackageListViewModel
     * @throws Exception
     */
    RedPackageListViewModel readPackageGroupList(Customer customer, RedPackageListRequestModel model) throws Exception;

    /**
     * 获取历史红包
     *
     * @param customer 客户
     * @param model    请求参数
     * @return RedPackageHistoryViewModel
     * @throws Exception
     */
    RedPackageHistoryViewModel readPackageHistory(Customer customer, RedPackageHistoryRequestModel model) throws Exception;

    /**
     * 红包详情readPackageEarlierHistory
     *
     * @param customer 客户
     * @param model    请求参数
     * @return RedPackageDetailViewModel
     * @throws Exception
     */
    RedPackageDetailViewModel readPackageDetail(Customer customer, RedPackageDetailRequestModel model) throws Exception;

    /**
     * 排序红包列表
     *
     * @param customer 客户
     * @param model    请求参数
     * @return RedPackageListViewModel
     * @throws Exception
     */
    RedPackageListViewModel readPackageList(Customer customer, RedPackageListRequestModel model) throws Exception;

}