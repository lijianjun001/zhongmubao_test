package com.zhongmubao.api.service.impl;

import com.zhongmubao.api.config.Constants;
import com.zhongmubao.api.config.ResultStatus;
import com.zhongmubao.api.config.enmu.*;
import com.zhongmubao.api.dao.CustomerHFDao;
import com.zhongmubao.api.dao.CustomerSinaDao;
import com.zhongmubao.api.dao.SheepOrderDao;
import com.zhongmubao.api.dao.SheepProjectDao;
import com.zhongmubao.api.dto.request.my.RealNameRequestModel;
import com.zhongmubao.api.dto.request.my.transaction.TransactionDetailRequestModel;
import com.zhongmubao.api.dto.request.my.transaction.TransactionMonthlyBillRequestModel;
import com.zhongmubao.api.dto.request.my.transaction.TransactionRequestModel;
import com.zhongmubao.api.dto.response.my.RealNameViewModel;
import com.zhongmubao.api.dto.response.my.transaction.*;
import com.zhongmubao.api.entity.Customer;
import com.zhongmubao.api.entity.CustomerHF;
import com.zhongmubao.api.entity.CustomerSina;
import com.zhongmubao.api.entity.ext.SheepBillInfo;
import com.zhongmubao.api.entity.ext.SheepOrderInfo;
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
import java.util.*;
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
    private final SheepOrderDao sheepOrderDao;

    @Autowired
    public CustomerServiceImpl(CustomerHFDao customerHFDao, CustomerSinaDao customerSinaDao, CustomerHFIndexMongoDao customerHFIndexMongoDao, CustomerHFBalanceMongoDao customerHFBalanceMongoDao, SheepProjectDao sheepProjectDao, SheepOrderDao sheepOrderDao) {
        this.customerHFDao = customerHFDao;
        this.customerSinaDao = customerSinaDao;
        this.customerHFIndexMongoDao = customerHFIndexMongoDao;
        this.customerHFBalanceMongoDao = customerHFBalanceMongoDao;
        this.sheepProjectDao = sheepProjectDao;
        this.sheepOrderDao = sheepOrderDao;
    }
    //region 是否实名
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
            if (customerHF.getIsBandCard() && !StringUtil.isNullOrEmpty(customerHF.getUsrCustId())) {
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
    //endregion

    //region 交易详情
    @Override
    public TransactionListViewModel transactionList(Customer customer, TransactionRequestModel model) throws
            Exception {
        if (null == model) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }

        Date date = new Date();
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
                    assert rechargeDetail != null;
                    transactionViewModel.setTransactionDate(rechargeDetail.getTransactionDate());
                } else if (mongo.getType().equals(TransactionDetailType.WITHDRAW.getName())) {
                    WithdrawDetailModel withdrawdetail = SerializeUtil.deSerialize(mongo.getTransactionDetail(), WithdrawDetailModel.class);
                    assert withdrawdetail != null;
                    transactionViewModel.setTransactionDate(withdrawdetail.getTransactionDate());
                } else if (mongo.getType().equals(TransactionDetailType.BUYSHEEP.getName())) {
                    BuySheepDetailModel buySheepDetail = SerializeUtil.deSerialize(mongo.getTransactionDetail(), BuySheepDetailModel.class);
                    assert buySheepDetail != null;
                    transactionViewModel.setTransactionDate(buySheepDetail.getBuySheepDate());
                } else if (mongo.getType().equals(TransactionDetailType.REDEEM.getName())) {
                    RedeemDetailModel redeemDetail = SerializeUtil.deSerialize(mongo.getTransactionDetail(), RedeemDetailModel.class);
                    assert redeemDetail != null;
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

    @Override
    public TransactionMonthlyBillViewModel transactionMonthlyBill(Customer customer, TransactionMonthlyBillRequestModel model) throws Exception {
        if (null == model || StringUtil.isNullOrEmpty(model.getBillDate())) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        Date billDate = DateUtil.strToDate(model.getBillDate());
        TransactionMonthlyBillViewModel viewModel = new TransactionMonthlyBillViewModel();
        viewModel.setResultType(TransactionBillResultType.NORMAL.getName());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        String time = format.format(billDate);
        viewModel.setYearMonth(time);
        // 获取当前月的第一天和最后一天
        Date startDate = DateUtil.monthFirstDay(billDate);
        Date endDate = DateUtil.monthLastDay(billDate);

        List<SheepBillInfo> sheepBillInfo = sheepProjectDao.getBuySheepBillByCustomerId(customer.getId(), DateUtil.format(startDate, Constants.DATE_TIME_FORMAT), DateUtil.format(endDate, Constants.DATE_TIME_FORMAT));
        if (null == sheepBillInfo) {
            sheepBillInfo = new ArrayList<>();
        }
        if (sheepBillInfo.size() == 0) {
            viewModel.setResultType(TransactionBillResultType.NO_BUY_SHEEP.getName());
        }

        // 总金额
        DoubleSummaryStatistics stats = sheepBillInfo.stream().mapToDouble(SheepBillInfo::getTotalAmount).summaryStatistics();
        viewModel.setTotalAmount(DoubleUtil.toFixed(stats.getSum(), Constants.PRICE_FORMAT));
        List<SheepProjectInfoModel> list = sheepBillInfo.stream().map(
                en -> new SheepProjectInfoModel(
                        DoubleUtil.toFixed(en.getTotalAmount(), Constants.PRICE_FORMAT),
                        getStatisticsTitle(en.getType(), en.getPeriod()),
                        en.getPeriod() + "天",
                        en.getTotalCount() + "只"
                )).collect(Collectors.toList());
        viewModel.setList(new ArrayList(list));
        // 总收益
        List<SheepOrderInfo> sheepOrderInfo = sheepOrderDao.statisticsBuySheepIncome(customer.getId(), DateUtil.format(startDate, Constants.DATE_TIME_FORMAT), DateUtil.format(endDate, Constants.DATE_TIME_FORMAT));
        double totalIncome = 0;
        double sheepIncome = 0;
        double redPacketIncome = 0;
        for (SheepOrderInfo order : sheepOrderInfo) {
            double sIncom = order.getCount() * calcProfitEx(order.getPrice(), order.getRate(), order.getPeriod());
            sheepIncome += sIncom;
            double rIncome = order.getRedPackageAmount();
            redPacketIncome += rIncome;
            totalIncome += (sIncom + rIncome);
        }
        viewModel.setTotalIncome("+" + DoubleUtil.toFixed(totalIncome, Constants.PRICE_FORMAT));
        // 羊只收益
        viewModel.setSheepIncome("+" + DoubleUtil.toFixed(sheepIncome, Constants.PRICE_FORMAT));
        // 红包增加收益
        viewModel.setRedPacketIncome("+" + DoubleUtil.toFixed(redPacketIncome, Constants.PRICE_FORMAT));
        // 总充值
        List<CustomerHFBalanceMongo> hfRecharge = customerHFBalanceMongoDao.calcTransactionTotalAmount(customer.getId(), TransactionDetailType.RECHARGE.getName(), DateUtil.formatMongo(startDate), DateUtil.formatMongo(endDate));
        DoubleSummaryStatistics statsRecharge = hfRecharge.stream().mapToDouble(CustomerHFBalanceMongo::getAddBalance).summaryStatistics();
        double totalRecharge = statsRecharge.getSum();
        viewModel.setTotalRecharge("+" + DoubleUtil.toFixed(totalRecharge, Constants.PRICE_FORMAT));
        // 总提现
        List<CustomerHFBalanceMongo> hfWithdraw = customerHFBalanceMongoDao.calcTransactionTotalAmount(customer.getId(), TransactionDetailType.WITHDRAW.getName(), DateUtil.formatMongo(startDate), DateUtil.formatMongo(endDate));
        DoubleSummaryStatistics statsWithdraw = hfWithdraw.stream().mapToDouble(CustomerHFBalanceMongo::getAddBalance).summaryStatistics();
        double totalWithdraw = statsWithdraw.getSum();
        viewModel.setTotalWithdraw("-" + DoubleUtil.toFixed(totalWithdraw, Constants.PRICE_FORMAT));

        // 缺醒类型
        if (TransactionBillResultType.NO_BUY_SHEEP.getName().equals(viewModel.getResultType()) && totalIncome <= 0 && totalRecharge <= 0 && totalWithdraw <= 0) {
            viewModel.setResultType(TransactionBillResultType.NO_TRANSACTION.getName());
        }

        return viewModel;
    }

    /**
     * 月账单购羊标题
     *
     * @param type 羊标类型
     * @return String
     */
    private String getStatisticsTitle(String type, int period) {
        String title = "新型羊标A";
        if (ProjectType.NORMAL.getName().equals(type)) {
            // 00 羊标
            if (period == SheepProjectPeriod.PERIOD_120.getName()) {
                title = "购羊标A";
            } else if (period == SheepProjectPeriod.PERIOD_240.getName()) {
                title = "购羊标B";
            } else {
                title = "购羊标C";
            }
        } else if (ProjectType.SLAUGHTER.getName().equals(type)) {
            // 03 商铺
            if (period == SheepProjectPeriod.PERIOD_120.getName()) {
                title = "商铺标A";
            } else if (period == SheepProjectPeriod.PERIOD_240.getName()) {
                title = "商铺标B";
            } else {
                title = "商铺标C";
            }
        } else if (ProjectType.NEW_PEOPLE_7.getName().equals(type)) {
            // 04 新手商铺
            if (period == SheepProjectPeriod.PERIOD_120.getName()) {
                title = "新手商标A";
            } else if (period == SheepProjectPeriod.PERIOD_240.getName()) {
                title = "新手商标B";
            } else {
                title = "新手商标C";
            }
        } else if (ProjectType.NEW_PEOPLE_120.getName().equals(type)) {
            // 06 新手羊
            if (period == SheepProjectPeriod.PERIOD_120.getName()) {
                title = "新手羊标A";
            } else if (period == SheepProjectPeriod.PERIOD_240.getName()) {
                title = "新手羊标B";
            } else {
                title = "新手羊标C";
            }
        }
        return title;
    }

    //endregion
}
