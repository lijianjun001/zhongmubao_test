package com.zhongmubao.api.service;

import com.zhongmubao.api.dto.request.system.*;
import com.zhongmubao.api.dto.response.system.PageSystemServerActionViewModel;
import com.zhongmubao.api.dto.response.system.SystemServerActionListViewModel;
import com.zhongmubao.api.entity.Customer;

import java.util.ArrayList;

/**
 * 系统服务
 *
 * @author 孙阿龙
 */
public interface SystemService {

    /**
     * 头条广告
     * @author xy
     * @param model 请求model
     * @throws Exception 错误信息
     */
    void touTiaoAdv(TouTiaoAdvRequestModel model) throws Exception;

    /**
     * 平台跟踪
     *
     * @param customer 客户
     * @param model    请求model
     * @throws Exception 异常
     * @author 孙阿龙
     */
    void platformTracking(Customer customer, PlatformTrackingRequestModel model) throws Exception;

    /**
     * 保存平台ServerAction
     *
     * @param model SystemServerActionSaveRequestModel
     * @throws Exception 异常
     */
    void saveServerAction(SystemServerActionSaveRequestModel model) throws Exception;

    /***
     * 分页
     * @param model 请求实体
     * @throws Exception 异常
     * @return PageSystemServerActionViewModel
     */
    PageSystemServerActionViewModel pagerServerAction(SystemServerActionPagerRequestModel model) throws Exception;

    /**
     * 获取server或者action
     *
     * @param model 请求实体
     * @return SystemServerActionListViewModel
     * @throws Exception 异常
     */
    SystemServerActionListViewModel serverActionList(SystemServerActionListRequestModel model) throws Exception;

    /**
     * 删除SystemServerAction
     *
     * @param model 请求实体
     * @throws Exception 异常
     */
    void DelSystemServerAction(SystemServerActionDelRequestModel model) throws Exception;
}
