package com.zhongmubao.api.service;

import com.zhongmubao.api.dto.request.customer.AccountExistRequestModel;
import com.zhongmubao.api.dto.request.message.CustomermessageRequestModel;
import com.zhongmubao.api.dto.request.customer.RecommendInfoRequestModel;
import com.zhongmubao.api.dto.request.customer.RegisterRequestModel;
import com.zhongmubao.api.dto.request.my.RealNameRequestModel;
import com.zhongmubao.api.dto.response.customer.AccountExistViewModel;
import com.zhongmubao.api.dto.response.message.CustomerMessageListViewModel;
import com.zhongmubao.api.dto.response.customer.RecommendInfoViewModel;
import com.zhongmubao.api.dto.response.customer.RegisterViewModel;
import com.zhongmubao.api.dto.response.my.RealNameViewModel;
import com.zhongmubao.api.entity.Customer;

/**
 * 个人中心
 *
 * @author xy
 */
public interface CustomerService {

    /**
     * 实名是否实名(汇付是否开户[激活])
     *
     * @param customer 用户
     * @param model
     * @return RealNameViewModel
     * @throws Exception
     */
    RealNameViewModel choosePaymentRealName(Customer customer, RealNameRequestModel model) throws Exception;

    /**
     * 注册
     *
     * @param register 客户注册信息
     * @return true or false
     * @throws Exception
     */
    RegisterViewModel register(RegisterRequestModel register) throws Exception;

    /**
     * 通过Code获取客户信息
     *
     * @param register 客户注册信息
     * @return true or false
     * @throws Exception
     */
    RecommendInfoViewModel recommendInfo(RecommendInfoRequestModel register) throws Exception;

    /**
     * 账号是否存在
     *
     * @param model 客户注册手机号
     * @return true or false
     * @throws Exception
     */
    AccountExistViewModel exist(AccountExistRequestModel model) throws Exception;
}
