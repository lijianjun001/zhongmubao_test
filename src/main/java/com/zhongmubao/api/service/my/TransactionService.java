package com.zhongmubao.api.service.my;

import com.zhongmubao.api.dto.request.my.transaction.TransactionDetailRequestModel;
import com.zhongmubao.api.dto.request.my.transaction.TransactionMonthlyBillRequestModel;
import com.zhongmubao.api.dto.request.my.transaction.TransactionRequestModel;
import com.zhongmubao.api.dto.response.my.transaction.TransactionDetailViewModel;
import com.zhongmubao.api.dto.response.my.transaction.TransactionListViewModel;
import com.zhongmubao.api.dto.response.my.transaction.TransactionMonthlyBillViewModel;
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
