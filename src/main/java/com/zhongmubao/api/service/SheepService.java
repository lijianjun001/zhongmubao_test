package com.zhongmubao.api.service;

import com.zhongmubao.api.dto.response.my.MyPastureViewModel;
import com.zhongmubao.api.entity.Customer;

/**
 * 牧场养羊
 *
 * @author 米立林
 */
public interface SheepService {
    /**
     * 微信小程序-我的牧场
     *
     * @param customer 当前用户
     * @return MyPastureViewModel
     * @throws Exception Exception
     */
    MyPastureViewModel miniappsMyPasture(Customer customer) throws Exception;
}
