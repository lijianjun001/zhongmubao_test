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

    /**
     * 交易明细列表
     *
     * @param customer 用户
     * @param model    请求参数
     * @return TransactionListViewModel
     * @throws Exception
     * @author 米立林
     */
    TransactionListViewModel transactionList(Customer customer, TransactionRequestModel model) throws Exception;

    /**
     * 交易记录详情
     *
     * @param customer 客户
     * @param model    请求model
     * @return TransactionDetailViewModel
     * @throws Exception
     * @author 米立林
     */
    TransactionDetailViewModel transactionDetail(Customer customer, TransactionDetailRequestModel model) throws Exception;

    /**
     * 交易明细--月账单
     *
     * @param customer 客户
     * @param model    请求model
     * @return TransactionDetailViewModel
     * @throws Exception
     * @author 米立林
     */
    TransactionMonthlyBillViewModel transactionMonthlyBill(Customer customer, TransactionMonthlyBillRequestModel model) throws Exception;
}
