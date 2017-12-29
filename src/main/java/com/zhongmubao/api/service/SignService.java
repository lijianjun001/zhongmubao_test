package com.zhongmubao.api.service;

import com.zhongmubao.api.dto.request.sign.*;
import com.zhongmubao.api.dto.response.sign.MyGiftCardModel;
import com.zhongmubao.api.dto.response.sign.*;
import com.zhongmubao.api.dto.response.sign.list.PageSignGiftModel;
import com.zhongmubao.api.dto.response.sign.SignModel;
import com.zhongmubao.api.dto.response.sign.packagelist.PageSignPackageModel;
import com.zhongmubao.api.entity.Customer;

/**
 * 客户服务
 *
 * @author 孙阿龙
 */
public interface SignService {

    /**
     * 签到
     * @author 孙阿龙
     * @param customer 客户
     * @return SignModel
     * @throws Exception 异常
     */
    SignModel sign(Customer customer) throws Exception;

    /**
     * 礼物分页
     * @author 孙阿龙
     * @param customer 客户
     * @param model 请求model
     * @return PageSignGiftModel
     * @throws Exception 异常
     */
    PageSignGiftModel pageGift(Customer customer, PageSignGiftRequestModel model) throws Exception;

    /**
     * 我的卡
     * @author 孙阿龙
     * @param customerId 客户id
     * @return MyGiftCardModel
     * @throws Exception 异常
     */
    MyGiftCardModel myGiftCard(int customerId) throws Exception;

    /**
     * 使用合并卡
     * @author 孙阿龙
     * @param customer 客户
     * @param model 请求model
     * @throws Exception 异常
     */
    void megreCard(Customer customer, MegreCardRequestModel model) throws Exception;

    /**
     * 使用延期卡
     * @author 孙阿龙
     * @param customerId 客户id
     * @param model 请求model
     * @throws Exception 异常
     */
    void delayedCard(int customerId, DelayedCardRequestModel model) throws Exception;

    /**
     * 签到红包分页
     * @author 孙阿龙
     * @param customerId 客户id
     * @param model 请求model
     * @return PageSignPackageModel
     */
    PageSignPackageModel pageSignPackage(int customerId, PageSignPackageRequestModel model);

    /**
     * 领取神秘礼物
     * @author 孙阿龙
     * @param customerId 客户
     * @param request 请求model
     * @throws Exception 异常
     */
    void recevieSecretGift(int customerId, RrcevieSecretGiftRequestModel request)throws Exception;

    /**
     * 领取话费礼物
     * @author 孙阿龙
     * @param customer 客户
     * @param model 请求model
     * @throws Exception 异常
     */
    void receiveRechargeGift(Customer customer, ReceiveRechargeGiftRequestModel model) throws Exception;

    /**
     * 今日是否分享
     * @param customerId 客户id
     * @return SignTodayIsShareModel
     */
    SignTodayIsShareModel todayIsShare(int customerId);

    /**
     * 分享信息
     * @param customerId 客户id
     * @return SignShareInfoModel
     */
    SignShareInfoModel shareInfo(int customerId);
}
