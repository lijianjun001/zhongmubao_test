package com.zhongmubao.api.service;

import com.zhongmubao.api.dto.request.my.RealNameRequestModel;
import com.zhongmubao.api.dto.request.my.transaction.TransactionDetailRequestModel;
import com.zhongmubao.api.dto.request.my.transaction.TransactionMonthlyBillRequestModel;
import com.zhongmubao.api.dto.request.my.transaction.TransactionRequestModel;
import com.zhongmubao.api.dto.response.my.RealNameViewModel;
import com.zhongmubao.api.dto.response.my.transaction.TransactionDetailViewModel;
import com.zhongmubao.api.dto.response.my.transaction.TransactionListViewModel;
import com.zhongmubao.api.dto.response.my.transaction.TransactionMonthlyBillViewModel;
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
}
