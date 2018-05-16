package com.zhongmubao.api.service;

import com.zhongmubao.api.dto.request.system.*;
import com.zhongmubao.api.dto.response.my.ListArticleViewModel;
import com.zhongmubao.api.dto.response.system.IncomeCalcViewModel;
import com.zhongmubao.api.dto.response.system.RedEnvelopeViewModel;
import com.zhongmubao.api.dto.response.system.ShareInfoViewModel;
import com.zhongmubao.api.entity.Customer;

/**
 * 系统服务
 *
 * @author 孙阿龙
 */
public interface SystemService {

    /**
     * 头条广告
     *
     * @param model 请求model
     * @throws Exception 错误信息
     * @author xy
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
     * 分享信息
     *
     * @param customer 客户
     * @param model    请求model
     * @return ShareInfoViewModel
     * @throws Exception 异常
     * @author 米立林
     */
    ShareInfoViewModel shareInfo(Customer customer, ShareInfoRequestModel model) throws Exception;

    /**
     * 发送短信验证码
     *
     * @param customer 客户
     * @param model    请求model
     * @throws Exception 异常
     * @author 米立林
     */
    void sendSms(Customer customer, SendSmsRequestModel model) throws Exception;

    /**
     * 小程序红包
     *
     * @param customer 客户
     * @param model    请求model
     * @return RedEnvelopeViewModel
     * @throws Exception 异常
     * @author 米立林
     */
    RedEnvelopeViewModel redEnvelope(Customer customer, RedEnvelopeRequestModel model) throws Exception;

    /**
     * 拆开红包
     *
     * @param customer 客户
     * @param model    请求model
     * @throws Exception 异常
     * @author 米立林
     */
    void redEnvelopeOpen(Customer customer, RedEnvelopeRequestModel model) throws Exception;

    /**
     * 文章列表
     *
     * @param customer 客户
     * @param model    请求model
     * @return ListArticleViewModel
     * @throws Exception 异常
     * @author 米立林
     */
    ListArticleViewModel miniappsArticleList(Customer customer, PageRequestModel model) throws Exception;

    /**
     * 收益计算
     *
     * @param customer 客户
     * @param model    请求model
     * @return IncomeCalcViewModel
     * @throws Exception 异常
     * @author 米立林
     */
    IncomeCalcViewModel miniappsIncomeCalc(Customer customer, IncomeCalcRequestModel model) throws Exception;


//    void testTransaction();
}
