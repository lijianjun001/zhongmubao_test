package com.zhongmubao.api.service.impl.mp;

import com.zhongmubao.api.config.Constants;
import com.zhongmubao.api.config.ResultStatus;
import com.zhongmubao.api.config.enmu.RedEnvelopeStatus;
import com.zhongmubao.api.config.enmu.RedPackageType;
import com.zhongmubao.api.dao.CustomerDao;
import com.zhongmubao.api.dao.SheepOrderDao;
import com.zhongmubao.api.dao.SheepProjectDao;
import com.zhongmubao.api.dto.request.mp.share.CalcProfitRequestModel;
import com.zhongmubao.api.dto.request.mp.share.FriendsRequestModel;
import com.zhongmubao.api.dto.request.mp.share.OpenRequestModel;
import com.zhongmubao.api.dto.request.mp.share.PageRequestModel;
import com.zhongmubao.api.dto.response.mp.share.*;
import com.zhongmubao.api.dto.response.my.ArticleModel;
import com.zhongmubao.api.entity.Customer;
import com.zhongmubao.api.entity.ext.SheepOrderInfo;
import com.zhongmubao.api.exception.ApiException;
import com.zhongmubao.api.mongo.dao.ArticleMongoDao;
import com.zhongmubao.api.mongo.dao.RedEnvelopeInfoMongoDao;
import com.zhongmubao.api.mongo.dao.RedEnvelopeMongoDao;
import com.zhongmubao.api.mongo.entity.ArticleMongo;
import com.zhongmubao.api.mongo.entity.RedEnvelopeInfoMongo;
import com.zhongmubao.api.mongo.entity.RedEnvelopeMongo;
import com.zhongmubao.api.mongo.entity.base.PageModel;
import com.zhongmubao.api.service.mp.MPShareService;
import com.zhongmubao.api.service.impl.BaseService;
import com.zhongmubao.api.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 微信小程序服务实现层
 *
 * @author 米立林
 */
@Service
public class MPShareServiceImpl extends BaseService implements MPShareService {

    private final RedEnvelopeMongoDao redEnvelopeMongoDao;
    private final RedEnvelopeInfoMongoDao redEnvelopeInfoMongoDao;
    private final CustomerDao customerDao;
    private final ArticleMongoDao articleMongoDao;
    private final SheepOrderDao sheepOrderDao;
    private final SheepProjectDao sheepProjectDao;

    @Autowired
    public MPShareServiceImpl(RedEnvelopeMongoDao redEnvelopeMongoDao, RedEnvelopeInfoMongoDao redEnvelopeInfoMongoDao, CustomerDao customerDao, ArticleMongoDao articleMongoDao, SheepOrderDao sheepOrderDao, SheepProjectDao sheepProjectDao) {
        this.redEnvelopeMongoDao = redEnvelopeMongoDao;
        this.redEnvelopeInfoMongoDao = redEnvelopeInfoMongoDao;
        this.customerDao = customerDao;
        this.articleMongoDao = articleMongoDao;
        this.sheepOrderDao = sheepOrderDao;
        this.sheepProjectDao = sheepProjectDao;
    }

    @Override
    public FriendsViewModel friends(Customer customer, FriendsRequestModel model) throws Exception {
        if (null == model || StringUtil.isNullOrEmpty(model.getId())) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        Date now = new Date();
        int countdown;
        boolean flat = false;
        String status;
        FriendsViewModel viewModel = new FriendsViewModel();
        RedEnvelopeMongo redEnvelopeMongo = redEnvelopeMongoDao.getById(model.getId());
        if (redEnvelopeMongo == null) {
            throw new ApiException(ResultStatus.INVALID_REC_ENVELOPE_ERROR);
        }
        status = redEnvelopeMongo.getStatus();
        ArrayList<RedEnvelopeCustomer> joins = new ArrayList<>();
        List<RedEnvelopeInfoMongo> redEnvelopeInfoMongos = redEnvelopeInfoMongoDao.getListByRedEnvelopeId(redEnvelopeMongo.id);

        RedEnvelopeInfoMongo currentCustomer = redEnvelopeInfoMongoDao.getOne(customer.getId(), redEnvelopeMongo.id);
        if (currentCustomer == null) {
            if (redEnvelopeInfoMongos.size() >= redEnvelopeMongo.getHeadcount()) {
                status = RedEnvelopeStatus.NOT_JOIN.getName();
            } else {
                if (redEnvelopeMongo.getStatus().equals(RedEnvelopeStatus.UNDERWAY.getName())) {
                    // 加入抢红包
                    flat = true;
                    currentCustomer = new RedEnvelopeInfoMongo();
                    currentCustomer.setRedEnvelopeId(redEnvelopeMongo.id);
                    currentCustomer.setJoinCustomerId(customer.getId());
                    currentCustomer.setJoinNo(redEnvelopeInfoMongos.size() + 1);
                    currentCustomer.setOpened(false);
                    currentCustomer.setCreated(DateUtil.addHours(now, 8));
                    redEnvelopeInfoMongos.add(currentCustomer);
                }
            }
        }

        for (RedEnvelopeInfoMongo info : redEnvelopeInfoMongos) {
            Customer joinCustomer = customerDao.getCustomerById(info.getJoinCustomerId());
            RedEnvelopeCustomer customerInfo = new RedEnvelopeCustomer();
            customerInfo.setPhoto(ApiUtil.formartPhoto(customerInfo.getPhoto()));
            customerInfo.setName(joinCustomer.getNickName());
            customerInfo.setPrice(info.getPrice());
            joins.add(customerInfo);
            if (info.getJoinCustomerId() == customer.getId()) {
                currentCustomer = info;
            }
        }

        // 人员满，则分配红包
        if (redEnvelopeInfoMongos.size() == redEnvelopeMongo.getHeadcount() && redEnvelopeMongo.getStatus().equals(RedEnvelopeStatus.UNDERWAY.getName())) {
            RedPacketUtil redPacketUtil = new RedPacketUtil();
            List<Float> prices = redPacketUtil.splitRedPackets(redEnvelopeMongo.getPrice(), redEnvelopeMongo.getHeadcount());
            // 分配到个人
            for (int i = 0; i < redEnvelopeInfoMongos.size(); i++) {
                String price = DoubleUtil.toFixed(prices.get(i), Constants.PRICE_FORMAT);
                RedEnvelopeInfoMongo mongo = redEnvelopeInfoMongos.get(i);
                mongo.setPrice(price);
                redEnvelopeInfoMongoDao.save(mongo);
                joins.get(i).setPrice(price);
            }

            // 修改RedEnvelope状态为"完成"
            redEnvelopeMongo.setStatus(RedEnvelopeStatus.SUCCESS.getName());
            redEnvelopeMongoDao.update(redEnvelopeMongo);
        } else {
            if (flat) {
                // 把当前用户添加到Mongo
                redEnvelopeInfoMongoDao.save(currentCustomer);
            }
        }
        countdown = (int) DateUtil.subDateOfSecond(redEnvelopeMongo.getEndTime(), now);
        if (countdown <= 0) {
            status = RedEnvelopeStatus.FAILED.getName();
        } else {
            if (!status.equals(RedEnvelopeStatus.NOT_JOIN.getName())) {
                if (currentCustomer.isOpened()) {
                    status = RedEnvelopeStatus.OPENED.getName();
                } else if (redEnvelopeInfoMongos.size() >= redEnvelopeMongo.getHeadcount()) {
                    status = RedEnvelopeStatus.SUCCESS.getName();
                }
            }
        }

        int headcount = redEnvelopeMongo.getHeadcount() - joins.size();
        viewModel.setStatus(status);
        viewModel.setSurplus(headcount);
        viewModel.setTotalPrice(String.valueOf(redEnvelopeMongo.getPrice()));
        viewModel.setCountdown(countdown);
        viewModel.setList(joins);

        return viewModel;
    }

    @Override
    public void open(Customer customer, OpenRequestModel model) throws Exception {
        if (null == model || StringUtil.isNullOrEmpty(model.getId())) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        Date now = new Date();
        // 修改已开红包状态
        RedEnvelopeInfoMongo currentCustomer = redEnvelopeInfoMongoDao.getOne(customer.getId(), model.getId());
        if (currentCustomer == null) {
            throw new ApiException(ResultStatus.PARAMETER_ERROR);
        }
        List<RedEnvelopeInfoMongo> redEnvelopeInfoMongos = redEnvelopeInfoMongoDao.getListByRedEnvelopeId(currentCustomer.getRedEnvelopeId());
        int headcount = 5;
        if (redEnvelopeInfoMongos.size() < headcount) {
            throw new ApiException(ResultStatus.NOW_COMPLETED_ERROR);
        }
        if (!currentCustomer.isOpened()) {
            currentCustomer.setOpened(true);
            redEnvelopeInfoMongoDao.update(currentCustomer);

            // 发送红包
            sendRedPackage(customer, RedPackageType.SHARE, Double.parseDouble(currentCustomer.getPrice()), now, 1);
        }
    }

    @Override
    public ListArticleViewModel article(Customer customer, PageRequestModel model) throws Exception {
        if (null == model) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }

        ListArticleViewModel viewModel = new ListArticleViewModel();
        PageModel<ArticleMongo> pager = new PageModel<>();
        pager.setPageNo(model.getPageIndex());
        pager = articleMongoDao.pager(false, pager);

        List<ArticleModel> list = pager.getDatas().stream()
                .map(en -> new ArticleModel(
                        en.id,
                        en.getTitle(),
                        en.getContent(),
                        ApiUtil.formartPhoto(en.getUrl()),
                        DateUtil.format(en.getCreated(), Constants.DATE_FORMAT_CHINA)))
                .collect(Collectors.toList());

        viewModel.setTotalPage(pager.getTotalPages());
        viewModel.setList(list);
        return viewModel;
    }

    @Override
    public IndexViewModel index(Customer customer) throws Exception {

        Date now = new Date();
        // 1、总资产
        double totalAsset = 0;
        List<SheepOrderInfo> sheepOrders = sheepOrderDao.statisticsAssetInfo(customer.getId(), Constants.SHEEP_IN_THE_BAR_STATE);
        for (SheepOrderInfo order : sheepOrders) {
            double sheepIncom = order.getCount() * ApiUtil.calcProfitEx(order.getPrice(), order.getRate(), order.getPeriod());
            double redIncome = order.getRedPackageAmount();
            totalAsset += (sheepIncom + redIncome);
        }
        // 2、累计总收益
        double totalIncome = sheepOrderDao.statisticsTotalRedeemIncome(customer.getId());
        // 3、累计月收益
        Date beginTime = DateUtil.formatMongo(DateUtil.monthFirstDay());
        Date endTime = DateUtil.formatMongo(now);
        double monthIncome = sheepOrderDao.statisticsRedeemIncome(customer.getId(), DateUtil.formatDefault(beginTime), DateUtil.formatDefault(endTime));

        PageModel<ArticleMongo> pager = new PageModel<>();
        pager.setPageNo(1);
        pager = articleMongoDao.pager(false, pager);

        List<ArticleModel> list = pager.getDatas().stream()
                .map(en -> new ArticleModel(
                        en.id,
                        en.getTitle(),
                        en.getContent(),
                        ApiUtil.formartPhoto(en.getUrl()),
                        DateUtil.format(en.getCreated(), Constants.DATE_FORMAT_CHINA)))
                .collect(Collectors.toList());

        IndexViewModel viewModel = new IndexViewModel();
        viewModel.setName(customer.getNickName());
        viewModel.setPhoto(ApiUtil.formartPhoto(customer.getPhoto()));
        viewModel.setTotalAsset(DoubleUtil.toFixed(totalAsset, Constants.PRICE_FORMAT));
        viewModel.setTotalIncome(DoubleUtil.toFixed(totalIncome, Constants.PRICE_FORMAT));
        viewModel.setMonthIncome(DoubleUtil.toFixed(monthIncome, Constants.PRICE_FORMAT));
        viewModel.setTotalPage(pager.getTotalPages());
        viewModel.setList(list);
        return viewModel;
    }

    @Override
    public MyPastureViewModel myPasture(Customer customer) throws Exception {
        // 1、在栏羊只
        int totalCount = sheepOrderDao.sumSheepOrderCountByCustomerIdAndState(customer.getId(), Constants.SHEEP_IN_THE_BAR_STATE);

        // 2、最早赎回
        Date firstRedeemTime = sheepProjectDao.firstRedeem(customer.getId(), Constants.SHEEP_IN_THE_BAR_STATE);
        String firstRedeem = DateUtil.format(firstRedeemTime, Constants.DATETIME_FORMAT_MONTH_DAY);

        // 3、最晚赎回
        Date finallyRedeemTime = sheepProjectDao.finallyRedeem(customer.getId(), Constants.SHEEP_IN_THE_BAR_STATE);
        String finallyRedeem = DateUtil.format(finallyRedeemTime, Constants.DATETIME_FORMAT_MONTH_DAY);

        MyPastureViewModel viewModel = new MyPastureViewModel();
        viewModel.setInbarCount(totalCount);
        viewModel.setFirstRedeemDate(firstRedeem);
        viewModel.setFinallyRedeemDate(finallyRedeem);
        return viewModel;
    }

    @Override
    public CalcProfitViewModel calcProfit(Customer customer, CalcProfitRequestModel model) throws Exception {
        if (null == model) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        double money = model.getMoney();
        if (money == 0) {
            return new CalcProfitViewModel();
        }
        double zmbIncome = money * 0.125;
        double bankIncome = money * 0.031;
        double zfmIncome = money * 0.043;

        CalcProfitViewModel viewModel = new CalcProfitViewModel();
        viewModel.setMoney(DoubleUtil.toFixed(money, Constants.PRICE_FORMAT));
        viewModel.setZmbIncome(DoubleUtil.toFixed(zmbIncome, Constants.PRICE_FORMAT));
        viewModel.setBankIncome(DoubleUtil.toFixed(bankIncome, Constants.PRICE_FORMAT));
        viewModel.setZfbIncome(DoubleUtil.toFixed(zfmIncome, Constants.PRICE_FORMAT));
        return viewModel;
    }
}
