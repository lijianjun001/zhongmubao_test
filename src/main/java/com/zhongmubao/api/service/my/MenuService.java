package com.zhongmubao.api.service.my;

import com.zhongmubao.api.dto.request.my.menu.ListRequestModel;
import com.zhongmubao.api.dto.request.my.RealNameRequestModel;
import com.zhongmubao.api.dto.response.my.menu.ListViewModel;
import com.zhongmubao.api.dto.response.my.RealNameViewModel;
import com.zhongmubao.api.entity.Customer;

/**
 * 个人中心
 * @author xy
 */
public interface MenuService {

    /**
     * 个人中心
     * @param customer 用户
     * @param model
     * @return ListViewModel
     * @throws Exception
     */
    ListViewModel list(Customer customer, ListRequestModel model) throws Exception;

    /**
     *  实名是否实名(汇付是否开户[激活])
     * @param customer 用户
     * @param model
     * @return RealNameViewModel
     * @throws Exception
     */
    RealNameViewModel choosePaymentRealName(Customer customer, RealNameRequestModel model) throws Exception;
}
