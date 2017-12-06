package com.zhongmubao.api.service;

import com.zhongmubao.api.dto.request.customer.center.PersonalCenterRequestModel;
import com.zhongmubao.api.dto.request.customer.center.RealNameRequestModel;
import com.zhongmubao.api.dto.response.customer.center.PersonalCenterViewModel;
import com.zhongmubao.api.dto.response.customer.center.RealNameViewModel;
import com.zhongmubao.api.entity.Customer;

/**
 * 个人中心
 * @author xy
 */
public interface CustomerService {

    /**
     * 个人中心
     * @param customer 用户
     * @param model
     * @return PersonalCenterViewModel
     * @throws Exception
     */
    PersonalCenterViewModel personalCenter(Customer customer, PersonalCenterRequestModel model) throws Exception;

    /**
     *  实名是否实名(汇付是否开户[激活])
     * @param customer 用户
     * @param model
     * @return RealNameViewModel
     * @throws Exception
     */
    RealNameViewModel realName(Customer customer, RealNameRequestModel model) throws Exception;
}
