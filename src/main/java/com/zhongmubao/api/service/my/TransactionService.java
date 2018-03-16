package com.zhongmubao.api.service.my;

import com.zhongmubao.api.dto.request.my.transaction.DetailRequestModel;
import com.zhongmubao.api.dto.request.my.transaction.MonthlyBillRequestModel;
import com.zhongmubao.api.dto.request.my.transaction.RequestModel;
import com.zhongmubao.api.dto.response.my.transaction.DetailViewModel;
import com.zhongmubao.api.dto.response.my.transaction.TransactionListViewModel;
import com.zhongmubao.api.dto.response.my.transaction.MonthlyBillViewModel;
import com.zhongmubao.api.entity.Customer;

/**
 * 交易明细
 * @author 米立林
 */
public interface TransactionService {

    /**
     * 交易明细列表
     *
     * @param customer 用户
     * @param model    请求参数
     * @return TransactionListViewModel
     * @throws Exception
     * @author 米立林
     */
    TransactionListViewModel transactionList(Customer customer, RequestModel model) throws Exception;

    /**
     * 交易记录详情
     *
     * @param customer 客户
     * @param model    请求model
     * @return DetailViewModel
     * @throws Exception
     * @author 米立林
     */
    DetailViewModel transactionDetail(Customer customer, DetailRequestModel model) throws Exception;

    /**
     * 交易明细--月账单
     *
     * @param customer 客户
     * @param model    请求model
     * @return DetailViewModel
     * @throws Exception
     * @author 米立林
     */
    MonthlyBillViewModel transactionMonthlyBill(Customer customer, MonthlyBillRequestModel model) throws Exception;
}
