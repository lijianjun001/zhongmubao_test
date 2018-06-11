package com.zhongmubao.api.service.mp;

import com.zhongmubao.api.dto.request.mp.share.CalcProfitRequestModel;
import com.zhongmubao.api.dto.request.mp.share.FriendsRequestModel;
import com.zhongmubao.api.dto.request.mp.share.OpenRequestModel;
import com.zhongmubao.api.dto.request.mp.share.PageRequestModel;
import com.zhongmubao.api.dto.response.mp.share.*;
import com.zhongmubao.api.entity.Customer;

/**
 * 微信小程序服务接口层
 *
 * @author 米立林
 */
public interface MPShareService {


    /***
     * 发起分享
     * @param customer 客户
     * @return LaunchViewModel
     * @throws Exception 异常
     */
    LaunchViewModel launch(Customer customer) throws Exception;

    /**
     * 红包进度
     *
     * @param customer 客户
     * @param model    请求model
     * @return RedEnvelopeViewModel
     * @throws Exception 异常
     * @author 米立林
     */
    FriendsViewModel friends(Customer customer, FriendsRequestModel model) throws Exception;

    /**
     * 拆开红包
     *
     * @param customer 客户
     * @param model    请求model
     * @throws Exception 异常
     * @author 米立林
     */
    void open(Customer customer, OpenRequestModel model) throws Exception;

    /**
     * 文章列表
     *
     * @param customer 客户
     * @param model    请求model
     * @return ListArticleViewModel
     * @throws Exception 异常
     * @author 米立林
     */
    ListArticleViewModel article(Customer customer, PageRequestModel model) throws Exception;

    /**
     * 我的资产
     *
     * @param customer 客户
     * @return true or false
     * @throws Exception
     */
    IndexViewModel index(Customer customer) throws Exception;

    /**
     * 我的牧场
     *
     * @param customer 当前用户
     * @return MyPastureViewModel
     * @throws Exception Exception
     */
    MyPastureViewModel myPasture(Customer customer) throws Exception;

    /**
     * 收益计算
     *
     * @param customer 客户
     * @param model    请求model
     * @return CalcProfitViewModel
     * @throws Exception 异常
     * @author 米立林
     */
    CalcProfitViewModel calcProfit(Customer customer, CalcProfitRequestModel model) throws Exception;
}
