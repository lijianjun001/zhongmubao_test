package com.zhongmubao.api.service.my;

import com.zhongmubao.api.dto.request.my.menu.ListRequestModel;
import com.zhongmubao.api.dto.response.my.menu.ListViewModel;
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

}
