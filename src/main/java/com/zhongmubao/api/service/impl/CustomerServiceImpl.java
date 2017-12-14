package com.zhongmubao.api.service.impl;

import com.zhongmubao.api.config.Constants;
import com.zhongmubao.api.config.ResultStatus;
import com.zhongmubao.api.config.enmu.RealNameStatus;
import com.zhongmubao.api.dao.CustomerHFDao;
import com.zhongmubao.api.dao.CustomerSinaDao;
import com.zhongmubao.api.dao.SheepProjectDao;
import com.zhongmubao.api.dto.request.my.RealNameRequestModel;
import com.zhongmubao.api.dto.request.my.transaction.TransactionDetailRequestModel;
import com.zhongmubao.api.dto.request.my.transaction.TransactionRequestModel;
import com.zhongmubao.api.dto.response.my.RealNameViewModel;
import com.zhongmubao.api.dto.response.my.transaction.*;
import com.zhongmubao.api.entity.Customer;
import com.zhongmubao.api.entity.CustomerHF;
import com.zhongmubao.api.entity.CustomerSina;
import com.zhongmubao.api.exception.ApiException;
import com.zhongmubao.api.mongo.dao.CustomerHFBalanceMongoDao;
import com.zhongmubao.api.mongo.dao.CustomerHFIndexMongoDao;
import com.zhongmubao.api.mongo.entity.CustomerHFIndexMongo;
import com.zhongmubao.api.mongo.entity.CustomerHFBalanceMongo;
import com.zhongmubao.api.mongo.entity.base.PageModel;
import com.zhongmubao.api.service.CustomerService;
import com.zhongmubao.api.util.DateUtil;
import com.zhongmubao.api.util.DoubleUtil;
import com.zhongmubao.api.util.SerializeUtil;
import com.zhongmubao.api.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Customer
 *
 * @author xy
 */
@Service
public class CustomerServiceImpl extends BaseService implements CustomerService {
    private final CustomerHFDao customerHFDao;
    private final CustomerSinaDao customerSinaDao;
    private final CustomerHFIndexMongoDao customerHFIndexMongoDao;
    private final CustomerHFBalanceMongoDao customerHFBalanceMongoDao;
    private final SheepProjectDao sheepProjectDao;

    @Autowired
    public CustomerServiceImpl(CustomerHFDao customerHFDao, CustomerSinaDao customerSinaDao, CustomerHFIndexMongoDao customerHFIndexMongoDao, CustomerHFBalanceMongoDao customerHFBalanceMongoDao, SheepProjectDao sheepProjectDao) {
        this.customerHFDao = customerHFDao;
        this.customerSinaDao = customerSinaDao;
        this.customerHFIndexMongoDao = customerHFIndexMongoDao;
        this.customerHFBalanceMongoDao = customerHFBalanceMongoDao;
        this.sheepProjectDao = sheepProjectDao;
    }

    @Override
    public RealNameViewModel choosePaymentRealName(Customer customer, RealNameRequestModel model) throws Exception {

        RealNameViewModel realNameViewModel = new RealNameViewModel();
        CustomerHF customerHF = customerHFDao.getCustomerHFById(customer.getId());
        CustomerSina customerSina = customerSinaDao.getCustomerSinaById(customer.getId());
        realNameViewModel.setCenterShowHFRealName(false);
        realNameViewModel.setIndexShowHFRealName(false);
        realNameViewModel.setCenterShowSinaRealName(false);
        realNameViewModel.setIndexShowSinaRealName(false);
        realNameViewModel.setCustomerType("L");
        boolean ishf = false;
        String dateFormat = "yyyy-MM-dd HH:mm:ss";
        String dateStr = "2017-12-01 00:00:00";

        //首页显示
        CustomerHFIndexMongo customerHFIndexMongo = customerHFIndexMongoDao.getByCustomerId(customer.getId());
        if (null != customerHFIndexMongo) {
            if (customerHF == null) {
                realNameViewModel.setCenterShowHFRealName(true);
                realNameViewModel.setIndexShowHFRealName(true);
            } else {
                if (!(customerHF.getIsBandCard() && !StringUtil.isNullOrEmpty(customerHF.getUsrCustId()))) {
                    realNameViewModel.setCenterShowHFRealName(true);
                    realNameViewModel.setIndexShowHFRealName(true);
                }
        if (null != customerHFIndexMongoDao.getByCustomerId(customer.getId())) {
            if (customerHF == null) {
                realNameViewModel.setShowHFIndex(true);
                realNameViewModel.setShowHFCenter(true);
            } else {
                if (!(customerHF.getIsBandCard() && !StringUtil.isNullOrEmpty(customerHF.getUsrCustId()))) {
                    realNameViewModel.setShowHFIndex(true);
                    realNameViewModel.setShowHFCenter(true);
                }
            }
            //不删除恒显示
            //customerHFIndexMongoDao.delete(customerHFIndexMongo);
        }

        if (customer.getCreated().getTime() > (new SimpleDateFormat(dateFormat).parse(dateStr)).getTime()) {
            realNameViewModel.setCustomerType("X");
            ishf = true;
        }
        if (!ishf && customerSina == null) {
            ishf = true;
        }
        if (!ishf && customerHF != null) {
            if(customerHF.getIsBandCard() && !StringUtil.isNullOrEmpty(customerHF.getUsrCustId())) {
                ishf = true;
            }
        }

        //显示汇付 or 新浪
        if (ishf) {
            if (customerHF == null) {
                realNameViewModel.setCenterShowHFRealName(true);
                realNameViewModel.setIndexShowHFRealName(true);
                realNameViewModel.setRealName(RealNameStatus.HFF.getName());
                realNameViewModel.setRealNameSatus(RealNameStatus.HFF.getStatus());
                realNameViewModel.setRealNameType(RealNameStatus.HFF.getType());
                realNameViewModel.setRealNameImg(Constants.RESOURES_ADDRESS_IMAGES + RealNameStatus.HFF.getImg());
                return realNameViewModel;
            }
            if (!StringUtil.isNullOrEmpty(customerHF.getUsrCustId()) && customerHF.getIsBandCard() && customerHF.getIsBosAcct()) {
                realNameViewModel.setRealName(RealNameStatus.HFS.getName());
                realNameViewModel.setRealNameSatus(RealNameStatus.HFS.getStatus());
                realNameViewModel.setRealNameType(RealNameStatus.HFS.getType());
                realNameViewModel.setRealNameImg(Constants.RESOURES_ADDRESS_IMAGES + RealNameStatus.HFS.getImg());
            } else {
                realNameViewModel.setCenterShowHFRealName(true);
                realNameViewModel.setIndexShowHFRealName(true);
                realNameViewModel.setRealName(RealNameStatus.HFF.getName());
                realNameViewModel.setRealNameSatus(RealNameStatus.HFF.getStatus());
                realNameViewModel.setRealNameType(RealNameStatus.HFF.getType());
                realNameViewModel.setRealNameImg(Constants.RESOURES_ADDRESS_IMAGES + RealNameStatus.HFF.getImg());
            }

        } else {

            if (customerSina == null) {
                realNameViewModel.setCenterShowSinaRealName(true);
                realNameViewModel.setIndexShowSinaRealName(true);
                realNameViewModel.setRealName(RealNameStatus.XLF.getName());
                realNameViewModel.setRealNameSatus(RealNameStatus.XLF.getStatus());
                realNameViewModel.setRealNameType(RealNameStatus.XLF.getType());
                realNameViewModel.setRealNameImg(Constants.RESOURES_ADDRESS_IMAGES + RealNameStatus.XLF.getImg());
                return realNameViewModel;
            }
            if (customerSina.getIsRealName()) {
                realNameViewModel.setRealName(RealNameStatus.XLS.getName());
                realNameViewModel.setRealNameSatus(RealNameStatus.XLS.getStatus());
                realNameViewModel.setRealNameType(RealNameStatus.XLS.getType());
                realNameViewModel.setRealNameImg(Constants.RESOURES_ADDRESS_IMAGES + RealNameStatus.XLS.getImg());
            } else {
                realNameViewModel.setCenterShowSinaRealName(true);
                realNameViewModel.setIndexShowSinaRealName(true);
                realNameViewModel.setRealName(RealNameStatus.XLF.getName());
                realNameViewModel.setRealNameSatus(RealNameStatus.XLF.getStatus());
                realNameViewModel.setRealNameType(RealNameStatus.XLF.getType());
                realNameViewModel.setRealNameImg(Constants.RESOURES_ADDRESS_IMAGES + RealNameStatus.XLF.getImg());
            }

        }
        return realNameViewModel;
    }

    //region 交易详情
    @Override
    public TransactionListViewModel transactionList(Customer customer, TransactionRequestModel model) throws Exception {
        if (null == model) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }

        Date date = new Date();
        if (!StringUtil.isNullOrEmpty(model.getBillDate())) {
            date = DateUtil.strToDate(model.getBillDate());
        }
        // 获取当前月的第一天和最后一天
        Date startDate = DateUtil.formatMongo(DateUtil.monthFirstDay(date));
        Date endDate = DateUtil.formatMongo(DateUtil.monthLastDay(date));

        PageModel<CustomerHFBalanceMongo> pager = new PageModel<>();
        pager.setPageNo(model.getPageIndex());
        pager = customerHFBalanceMongoDao.pager(customer.getId(), startDate, endDate, model.getBillType(), pager);
        ArrayList<TransactionViewModel> list = new ArrayList<>();
        for (CustomerHFBalanceMongo mongo : pager.getDatas()) {
            TransactionViewModel transactionViewModel = new TransactionViewModel(
                    mongo.getType(),
                    getTransactionTitle(customer.getId(), mongo.getType(), mongo.getTransactionNum()),
                    "",
                    getTransactionAmount(mongo.getType(), mongo.getAddBalance()),
                    mongo.id
            );
            try {
                if (mongo.getType().equals(TransactionDetailType.RECHARGE.getName())) {
                    RechargeDetailModel rechargeDetail = SerializeUtil.deSerialize(mongo.getTransactionDetail(), RechargeDetailModel.class);
                    transactionViewModel.setTransactionDate(rechargeDetail.getTransactionDate());
                } else if (mongo.getType().equals(TransactionDetailType.WITHDRAW.getName())) {
                    WithdrawDetailModel WithdrawDetail = SerializeUtil.deSerialize(mongo.getTransactionDetail(), WithdrawDetailModel.class);
                    transactionViewModel.setTransactionDate(WithdrawDetail.getTransactionDate());
                } else if (mongo.getType().equals(TransactionDetailType.BUYSHEEP.getName())) {
                    BuySheepDetailModel buySheepDetail = SerializeUtil.deSerialize(mongo.getTransactionDetail(), BuySheepDetailModel.class);
                    transactionViewModel.setTransactionDate(buySheepDetail.getBuySheepDate());
                } else if (mongo.getType().equals(TransactionDetailType.REDEEM.getName())) {
                    RedeemDetailModel redeemDetail = SerializeUtil.deSerialize(mongo.getTransactionDetail(), RedeemDetailModel.class);
                    transactionViewModel.setTransactionDate(redeemDetail.getOperationDate());
                }
                list.add(transactionViewModel);
            } catch (Exception ex) {
                transactionViewModel.setTransactionDate(DateUtil.formatDefault(mongo.getCreateTime()));
                list.add(transactionViewModel);
            }
        }

        return new TransactionListViewModel(pager.getTotalPages(), list);
    }

    private String getTransactionAmount(String transType, double amount) {
        String amountStr = DoubleUtil.toFixed(amount, Constants.PRICE_FORMAT);
        String transactionAmount = amountStr;
        if (transType.equals(TransactionDetailType.RECHARGE.getName()) || transType.equals(TransactionDetailType.REDEEM.getName())) {
            transactionAmount = "+" + amountStr;
        } else if (transType.equals(TransactionDetailType.WITHDRAW.getName()) || transType.equals(TransactionDetailType.BUYSHEEP.getName())) {
            transactionAmount = amountStr;
        }
        return transactionAmount;
    }

    private String getTransactionTitle(int customerId, String transType, String transactionNum) {
        String title = "";
        String code = transactionNum.substring(2);
        if (transType.equals(TransactionDetailType.RECHARGE.getName())) {
            title = "充值汇付天下众牧宝账号";
        } else if (transType.equals(TransactionDetailType.WITHDRAW.getName())) {
            title = "提现到银行卡";
        } else if (transType.equals(TransactionDetailType.BUYSHEEP.getName())) {
            String projectTitle = sheepProjectDao.getProjectTitleByBustomerIdAndCode(customerId, code);
            title = "购买" + (StringUtil.isNullOrEmpty(projectTitle) ? "" : projectTitle);
        } else if (transType.equals(TransactionDetailType.REDEEM.getName())) {
            String projectTitle = sheepProjectDao.getProjectTitleByBustomerIdAndCode(customerId, code);
            title = "赎回" + (StringUtil.isNullOrEmpty(projectTitle) ? "" : projectTitle);
        } else if (transType.equals(TransactionDetailType.REDEEM_RED.getName())) {
            String projectTitle = sheepProjectDao.getProjectTitleByBustomerIdAndCode(customerId, code);
            title = "赎回" + (StringUtil.isNullOrEmpty(projectTitle) ? "" : projectTitle) + "红包补贴";
        }
        return title;
    }

    @Override
    public TransactionDetailViewModel transactionDetail(Customer customer, TransactionDetailRequestModel model) throws Exception {
        if (null == model || StringUtil.isNullOrEmpty(model.getId())) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        CustomerHFBalanceMongo record = customerHFBalanceMongoDao.getSingle(customer.getId(), model.getId());
        if (record == null || StringUtil.isNullOrEmpty(record.type)) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        TransactionDetailViewModel transactionDetailViewModel = new TransactionDetailViewModel();
        transactionDetailViewModel.setBillType(record.getType());
        transactionDetailViewModel.setTransactionDetail(record.getTransactionDetail());
        transactionDetailViewModel.setBalance(DoubleUtil.toFixed(record.getNowBalance(), Constants.PRICE_FORMAT));

        return transactionDetailViewModel;
    }
    //endregion
}
