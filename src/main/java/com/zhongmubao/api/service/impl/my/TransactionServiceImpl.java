package com.zhongmubao.api.service.impl.my;

import com.zhongmubao.api.config.Constants;
import com.zhongmubao.api.config.ResultStatus;
import com.zhongmubao.api.config.enmu.ProjectType;
import com.zhongmubao.api.config.enmu.SheepProjectPeriod;
import com.zhongmubao.api.config.enmu.TransactionBillResultType;
import com.zhongmubao.api.config.enmu.TransactionDetailType;
import com.zhongmubao.api.dao.SheepOrderDao;
import com.zhongmubao.api.dao.SheepProjectDao;
import com.zhongmubao.api.dto.request.my.transaction.DetailRequestModel;
import com.zhongmubao.api.dto.request.my.transaction.MonthlyBillRequestModel;
import com.zhongmubao.api.dto.request.my.transaction.RequestModel;
import com.zhongmubao.api.dto.response.my.transaction.*;
import com.zhongmubao.api.entity.Customer;
import com.zhongmubao.api.entity.ext.SheepBillInfo;
import com.zhongmubao.api.entity.ext.SheepOrderInfo;
import com.zhongmubao.api.exception.ApiException;
import com.zhongmubao.api.mongo.dao.CustomerHFBalanceMongoDao;
import com.zhongmubao.api.mongo.entity.CustomerHFBalanceMongo;
import com.zhongmubao.api.mongo.entity.base.PageModel;
import com.zhongmubao.api.service.impl.BaseService;
import com.zhongmubao.api.service.my.TransactionService;
import com.zhongmubao.api.util.*;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 交易明细实现类
 *
 * @author 米立林
 */
@Service
public class TransactionServiceImpl extends BaseService implements TransactionService {
    private final CustomerHFBalanceMongoDao customerHFBalanceMongoDao;
    private final SheepProjectDao sheepProjectDao;
    private final SheepOrderDao sheepOrderDao;

    public TransactionServiceImpl(CustomerHFBalanceMongoDao customerHFBalanceMongoDao, SheepProjectDao sheepProjectDao, SheepOrderDao sheepOrderDao) {
        this.customerHFBalanceMongoDao = customerHFBalanceMongoDao;
        this.sheepProjectDao = sheepProjectDao;
        this.sheepOrderDao = sheepOrderDao;
    }

    //region 交易明细列表
    @Override
    public TransactionListViewModel transactionList(Customer customer, RequestModel model) throws Exception {
        if (null == model) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }

        Date date = DateUtil.strToShortDate(model.getBillDate());
        if (date == null) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
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
                    mongo.getTitle(),
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
                } else if (mongo.getType().equals(TransactionDetailType.REDEEM_RED.getName())) {
                    RedeemRedDetailModel redeemRedDetail = SerializeUtil.deSerialize(mongo.getTransactionDetail(), RedeemRedDetailModel.class);
                    assert redeemRedDetail != null;
                    transactionViewModel.setTransactionDate(redeemRedDetail.getOperationDate());
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

    @Override
    public DetailViewModel transactionDetail(Customer customer, DetailRequestModel model) throws Exception {
        if (null == model || StringUtil.isNullOrEmpty(model.getId())) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        CustomerHFBalanceMongo record = customerHFBalanceMongoDao.getSingle(customer.getId(), model.getId());
        if (record == null || StringUtil.isNullOrEmpty(record.type)) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        DetailViewModel transactionDetailViewModel = new DetailViewModel();
        transactionDetailViewModel.setBillType(record.getType());
        transactionDetailViewModel.setTransactionDetail(record.getTransactionDetail());
        transactionDetailViewModel.setBalance(DoubleUtil.toFixed(record.getNowBalance(), Constants.PRICE_FORMAT));

        return transactionDetailViewModel;
    }

    @Override
    public MonthlyBillViewModel transactionMonthlyBill(Customer customer, MonthlyBillRequestModel model) throws Exception {
        if (null == model || StringUtil.isNullOrEmpty(model.getBillDate())) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        Date billDate = DateUtil.strToShortDate(model.getBillDate());
        MonthlyBillViewModel viewModel = new MonthlyBillViewModel();
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
                        getStatisticsTitle(en.getType(), en.getPeriod()) + "（" + en.getPeriod() + "天）",
                        en.getPeriod() + "",
                        en.getTotalCount() + "",
                        getPieColor(en.getType(), en.getPeriod())
                )).collect(Collectors.toList());
        viewModel.setList(new ArrayList(list));
        // 总收益
        List<SheepOrderInfo> sheepOrderInfo = sheepOrderDao.statisticsBuySheepIncome(customer.getId(), DateUtil.format(startDate, Constants.DATE_TIME_FORMAT), DateUtil.format(endDate, Constants.DATE_TIME_FORMAT));
        double totalIncome = 0;
        double sheepIncome = 0;
        double redPacketIncome = 0;
        for (SheepOrderInfo order : sheepOrderInfo) {
            double sIncom = order.getCount() * ApiUtil.calcProfitEx(order.getPrice(), order.getRate(), order.getPeriod());
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
        viewModel.setTotalWithdraw(DoubleUtil.toFixed(totalWithdraw, Constants.PRICE_FORMAT));

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
        String title = "新型羊标";
        if (ProjectType.NORMAL.getName().equals(type)) {
            // 00 羊标
            title = "购羊标";
        } else if (ProjectType.SLAUGHTER.getName().equals(type)) {
            // 03 商铺
            title = "商铺标";
        } else if (ProjectType.NEW_PEOPLE_7.getName().equals(type)) {
            // 04 新手商铺
            title = "新手商标";
        } else if (ProjectType.NEW_PEOPLE_120.getName().equals(type)) {
            // 06 新手羊
            title = "新手羊标";
        }
        return title;
    }

    /**
     * 月账单购羊标主题颜色
     *
     * @param type 羊标类型
     * @return String
     */
    private String getPieColor(String type, int period) {
        String color = "#FE0000";
        if (ProjectType.NORMAL.getName().equals(type)) {
            // 00 羊标
            if (period == SheepProjectPeriod.PERIOD_120.getName()) {
                color = "#7EFF00";
            } else if (period == SheepProjectPeriod.PERIOD_240.getName()) {
                color = "#00FF01";
            } else {
                color = "#00FF7F";
            }
        } else if (ProjectType.SLAUGHTER.getName().equals(type)) {
            // 03 商铺
            if (period == SheepProjectPeriod.PERIOD_120.getName()) {
                color = "#01FFFF";
            } else if (period == SheepProjectPeriod.PERIOD_240.getName()) {
                color = "#027FFF";
            } else {
                color = "#0000FE";
            }
        } else if (ProjectType.NEW_PEOPLE_7.getName().equals(type)) {
            // 04 新手商铺
            if (period == SheepProjectPeriod.PERIOD_120.getName()) {
                color = "#7F00FF";
            } else if (period == SheepProjectPeriod.PERIOD_240.getName()) {
                color = "#FF00FE";
            } else {
                color = "#FF0080";
            }
        } else if (ProjectType.NEW_PEOPLE_120.getName().equals(type)) {
            // 06 新手羊
            if (period == SheepProjectPeriod.PERIOD_120.getName()) {
                color = "#FE0000";
            } else if (period == SheepProjectPeriod.PERIOD_240.getName()) {
                color = "#FF7F00";
            } else {
                color = "#FFFF01";
            }
        }
        return color;
    }
    //endregion
}
