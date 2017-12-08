package com.zhongmubao.api.service.my;

import com.zhongmubao.api.dto.request.my.center.PersonalCenterRequestModel;
import com.zhongmubao.api.dto.request.my.RealNameRequestModel;
import com.zhongmubao.api.dto.response.my.center.PersonalCenterViewModel;
import com.zhongmubao.api.dto.response.my.RealNameViewModel;
import com.zhongmubao.api.entity.Customer;

/**
 * 个人中心
 * @author xy
 */
public interface CenterService {

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
