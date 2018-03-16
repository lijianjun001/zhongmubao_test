package com.zhongmubao.api.service.my;

import com.zhongmubao.api.dto.request.my.redpackage.DetailRequestModel;
import com.zhongmubao.api.dto.request.my.redpackage.GroupRequestModel;
import com.zhongmubao.api.dto.request.my.redpackage.HistoryRequestModel;
import com.zhongmubao.api.dto.request.my.redpackage.ListRequestModel;
import com.zhongmubao.api.dto.response.my.redpackage.DetailViewModel;
import com.zhongmubao.api.dto.response.my.redpackage.GroupViewModel;
import com.zhongmubao.api.dto.response.my.redpackage.HistoryViewModel;
import com.zhongmubao.api.dto.response.my.redpackage.ListViewModel;
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
     * @return GroupViewModel
     * @throws Exception 异常
     */
    GroupViewModel readPackageGroup(Customer customer, GroupRequestModel model) throws Exception;

    /**
     * 分组红包列表
     *
     * @param customer 客户
     * @param model    请求参数
     * @return ListViewModel
     * @throws Exception
     */
    ListViewModel readPackageGroupList(Customer customer, ListRequestModel model) throws Exception;

    /**
     * 获取历史红包
     *
     * @param customer 客户
     * @param model    请求参数
     * @return HistoryViewModel
     * @throws Exception
     */
    HistoryViewModel readPackageHistory(Customer customer, HistoryRequestModel model) throws Exception;

    /**
     * 红包详情readPackageEarlierHistory
     *
     * @param customer 客户
     * @param model    请求参数
     * @return DetailViewModel
     * @throws Exception
     */
    DetailViewModel readPackageDetail(Customer customer, DetailRequestModel model) throws Exception;

    /**
     * 排序红包列表
     *
     * @param customer 客户
     * @param model    请求参数
     * @return ListViewModel
     * @throws Exception
     */
    ListViewModel readPackageList(Customer customer, ListRequestModel model) throws Exception;

}