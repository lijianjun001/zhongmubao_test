package com.zhongmubao.api.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhongmubao.api.cache.RedisCache;
import com.zhongmubao.api.components.recharge.Recharge;
import com.zhongmubao.api.config.Constants;
import com.zhongmubao.api.config.ResultStatus;
import com.zhongmubao.api.config.enmu.*;
import com.zhongmubao.api.dao.*;
import com.zhongmubao.api.dto.request.sign.*;
import com.zhongmubao.api.dto.response.sign.*;
import com.zhongmubao.api.dto.response.sign.list.PageSignGiftModel;
import com.zhongmubao.api.dto.response.sign.list.PageSignGiftViewModel;
import com.zhongmubao.api.dto.response.sign.packagelist.PageSignPackageModel;
import com.zhongmubao.api.dto.response.sign.packagelist.SignPackageViewModel;
import com.zhongmubao.api.config.entity.SignGift;
import com.zhongmubao.api.entity.*;
import com.zhongmubao.api.exception.ApiException;
import com.zhongmubao.api.mongo.dao.CustomerNoticMongoDao;
import com.zhongmubao.api.mongo.dao.ShareCardMongoDao;
import com.zhongmubao.api.mongo.entity.*;
import com.zhongmubao.api.mongo.entity.base.PageModel;
import com.zhongmubao.api.service.BaseService;
import com.zhongmubao.api.service.SignService;
import com.zhongmubao.api.util.*;
import com.zhongmubao.api.util.redis.RedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.zhongmubao.api.config.enmu.SignGiftType.*;


/***
 * 客户服务类
 * @author 孙阿龙
 */
@Service
public class SignServiceImpl extends BaseService implements SignService {

    private final ExtRedPackageDao extRedPackageDao;
    private final ShareCardMongoDao shareCardMongoDao;
    private final CustomerAddressDao customerAddressDao;
    private final RedisCache redisCache;
    private final SheepOrderDao sheepOrderDao;
    private final ExtActivityRecordDao activityRecordDao;
    private final CustomerNoticMongoDao customerNoticMongoDao;

    @Autowired
    public SignServiceImpl(ExtRedPackageDao extRedPackageDao, ShareCardMongoDao shareCardMongoDao, CustomerAddressDao customerAddressDao, RedisCache redisCache, SheepOrderDao sheepOrderDao, ExtActivityRecordDao activityRecordDao,CustomerNoticMongoDao customerNoticMongoDao) {
        this.extRedPackageDao = extRedPackageDao;
        this.shareCardMongoDao = shareCardMongoDao;
        this.customerAddressDao = customerAddressDao;
        this.redisCache = redisCache;
        this.sheepOrderDao = sheepOrderDao;
        this.activityRecordDao = activityRecordDao;
        this.customerNoticMongoDao = customerNoticMongoDao;
    }

    //region 签到相关

    @Override
    public SignModel sign(Customer customer) throws Exception {
        int customerId = customer.getId();
        String signInfo;
        Date now = new Date();
        Date dayBegin = DateUtil.dayBegin();
        Date dayEnd = DateUtil.dayEnd();
        //红包过期时间
        Date expTime = DateUtil.addDay(now, Constants.DAY_SHARE_REDPACKAGE_EXP_DAY);
        //签到时间分界点
        Date boundaryTime = DateUtil.addHours(dayBegin, 10);
        String dayShareType = RedPackageType.DAY_SHARE.getName();
        List<Integer> giftDayList = Arrays.asList(7, 14, 21, 28);
        ShareCardMongo shareCard = null;
        SignGiftViewModel signGiftViewModel = null;
        SignGiftAddressViewModel signGiftAddressViewModel = null;
        Date monthBegin = DateUtil.monthFirstDay();
        Date monthEnd = DateUtil.monthLastDay();

        RedisLock lock = new RedisLock(redisCache.redisHelper.redisTemplate, Constants.LOCK_SIGN_KEY + customerId, 10000, 20000);
        try {
            if (lock.lock()) {
                //region 逻辑

                //分享天数
                int shareDayCount = customerId == 4194 ? 6 : extRedPackageDao.countExtRedPackageByCustomerIdAndBeginTimeAndEndTimeAndType(customerId, monthBegin, monthEnd, dayShareType);

                //region 验证
                boolean todayIsShare = customerId == 4194 ? false : redisCache.getCustomerIsShare(customerId);

                if (todayIsShare) {
                    return new SignModel(shareDayCount, "0.00", null, null, true, false);
                }

                //判断这个月分享了多少次
                int monthMaxShare = Constants.MONTH_MAX_SHARE;

                if (shareDayCount > monthMaxShare) {
                    throw new ApiException(ResultStatus.MAX_MONTH_SHAY);
                }
                shareDayCount = shareDayCount + 1;
                //endregion

                //region 每日分享送红包
                double price = redisCache.getShareRandom(MathUtil.random(1, 10000));

                int signCount = extRedPackageDao.countExtRedPackageByBeginTimeAndEndTimeAndType(dayBegin, dayEnd, dayShareType);
                signCount = signCount == 0 ? 1 : signCount;
                if (now.getTime() <= boundaryTime.getTime()) {
                    signInfo = String.format("早起的鸟儿有虫吃，您是今日第%d位签到牧场主", signCount);
                } else {
                    int ltCount = extRedPackageDao.countLtPriceExtRedPackageByBeginTimeAndEndTimeAndType(dayBegin, dayEnd, dayShareType, price);
                    double dProportion = (double) ltCount / signCount;
                    int beatProportion = (int) (dProportion * 100);
                    signInfo = String.format("您今天的RP值击败了全宇宙%d%%的牧场主", beatProportion);
                }

                //添加红包
                sendRedPackage(customer, dayShareType, price, expTime, 1,"");

                //设置今天已分享
                redisCache.saveCustomerIsShare(customerId);
                //endregion

                //region 礼物
                if (giftDayList.contains(shareDayCount)) {
                    // 随机获取神秘礼物
                    int randomIndex = MathUtil.random(0, Constants.SIGN_GIFT_LIST.size() - 1);

                    SignGiftRedPackageViewModel signGiftRedPackageViewModel = null;

                    double giftPrice = 0;
                    // 得到具体礼物类型
                    SignGift signGift = Constants.SIGN_GIFT_LIST.get(randomIndex);

                    // 神秘礼物
                    if (signGift.getType().equals(SECRET_GIFT)) {
                        //判断30天内是否买羊
                        Date buySheepBeginTime = DateUtil.addDay(now, -30);
                        int buySheepCount = sheepOrderDao.countSheepOrderByCustomerIdAndBeginTimeAndEndTimeAndState(customerId, buySheepBeginTime, now, Constants.SHEEP_IN_THE_BAR_STATE);
                        if (buySheepCount <= 0) {
                            // 没买羊默认奖励红包
                            signGift = Constants.SIGN_GIFT_LIST.get(0);
                        } else {
                            signGiftAddressViewModel = formartAddress(customerId);
                        }
                    }
                    // 如果中奖是红包
                    double redPackagePrice;
                    if (signGift.getType().equals(RED_PACKAGE)) {
                        signGiftRedPackageViewModel = new SignGiftRedPackageViewModel();
                        redPackagePrice = MathUtil.random(10, 200) * 0.01;
                        signGiftRedPackageViewModel.setPrice(DoubleUtil.toFixed(redPackagePrice, "0.00"));
                        //添加红包
                        sendRedPackage(customer, dayShareType, redPackagePrice, expTime, 1,"00");
                        giftPrice = redPackagePrice;
                    }

                    // 如果中奖的是话费卡
                    int telephoneMoney = 0;
                    if (signGift.getType().equals(TELEPHONE_CARD)) {
                        // 得出话费金额
                        telephoneMoney = signGift.getCount();
                        // 话费卡设置count=1，让循环一次
                        signGift.setCount(1);
                        giftPrice = telephoneMoney;
                    }

                    // 如果是神秘礼物或者话费卡，状态为未领取
                    String status;
                    if (signGift.getType().equals(SECRET_GIFT) || signGift.getType().equals(TELEPHONE_CARD)) {
                        status = ShareCardState.UNCLAIMED.getName();
                    } else {
                        status = ShareCardState.RECEIVED.getName();
                    }

                    //添加礼物到Mongo
                    for (int i = 0; i < signGift.getCount(); i++) {
                        Date mongoNow = DateUtil.formatMongo(now);
                        shareCard = new ShareCardMongo();
                        shareCard.setCustomerId(customerId);
                        shareCard.setTitle(formatGiftTitle(signGift.getType(), 1, giftPrice));
                        shareCard.setType(signGift.getType().getName());
                        shareCard.setCount(signGift.getType().equals(TELEPHONE_CARD) ? telephoneMoney : 1);
                        shareCard.setCreated(mongoNow);
                        shareCard.setExceed(DateUtil.addDay(mongoNow, 30));
                        shareCard.setStatus(status);
                        shareCard.setDelete(false);
                        shareCardMongoDao.add(shareCard);
                    }

                    if (null != shareCard) {
                        signGiftViewModel = new SignGiftViewModel();
                        signGiftViewModel.setId(shareCard.id);
                        signGiftViewModel.setType(shareCard.getType());
                        signGiftViewModel.setCount(signGift.getCount());
                        signGiftViewModel.setSignGiftRedPackage(signGiftRedPackageViewModel);
                        signGiftViewModel.setSignGiftAddress(signGiftAddressViewModel);
                        signGiftViewModel.setTitle(formatGiftShortTitle(signGift.getType(), shareCard.getCount(), giftPrice));
                        signGiftViewModel.setUnit(formatGiftUnit(signGift.getType()));
                        signGiftViewModel.setSignGiftCharge(new SignGiftCharge(Integer.toString(telephoneMoney), customer.getAccount()));
                    }

                }

                //活动统一入口
                signActivity(customer);
                //endregion
                return new SignModel(shareDayCount, DoubleUtil.toFixed(price, "0.00"), signInfo, signGiftViewModel, true, true);

                //endregion
            }
        } finally {
            lock.unlock();
        }
        throw new ApiException(ResultStatus.FAIL);
    }

    private void signActivity(Customer customer) throws Exception {
        //判断活动期间是否送过，送过就不送，Redis里取。
        if(!redisCache.hasSinglesDay(customer.getId())){
            //进行送话费
            String orderId = "SD"+customer.getAccount()+""+System.currentTimeMillis();
            Recharge.submit(customer.getAccount(),2,orderId);
            //公告添加数据进行弹层
            CustomerNoticMongo customerNoticMongo = new CustomerNoticMongo();
            customerNoticMongo.setCustomerId(customer.getId());
            customerNoticMongo.setContent("");
            customerNoticMongo.setTitle("");
            customerNoticMongo.setBeginTime(DateUtil.formatMongo(new Date()));
            customerNoticMongo.setEndTime(DateUtil.formatMongo(new Date()));
            customerNoticMongo.setCreateTime(DateUtil.formatMongo(new Date()));

            customerNoticMongoDao.save(customerNoticMongo);
            redisCache.saveSinglesDay(customer.getId());
        }
    }

    @Override
    public PageSignGiftModel pageGift(Customer customer, PageSignGiftRequestModel model) throws Exception {
        if (null == model) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        PageModel<ShareCardMongo> pager = new PageModel<>();
        pager.setPageNo(model.getPageIndex());
        pager = shareCardMongoDao.pager(customer.getId(), true, pager);

        List<PageSignGiftViewModel> list = pager.getDatas().stream()
                .map(en -> new PageSignGiftViewModel(
                        en.id,
                        en.getTitle(),
                        en.getCount(),
                        en.getStatus(),
                        en.getType(),
                        DateUtil.format(DateUtil.addHours(en.getCreated(), -8), "yyyy.MM.dd")))
                .collect(Collectors.toList());
        return new PageSignGiftModel(pager.getTotalPages(), list, formartAddress(customer.getId()), customer.getPhone());
    }

    @Override
    public MyGiftCardModel myGiftCard(int customerId) throws Exception {
        int delayedCardCount = shareCardMongoDao.countByCustomerIdAndType(customerId, SignGiftType.DELAYED_CARD.getName());
        int mergeCardCount = shareCardMongoDao.countByCustomerIdAndType(customerId, SignGiftType.MERGE_CARD.getName());
        return new MyGiftCardModel(delayedCardCount, mergeCardCount);
    }

    @Override
    public void megreCard(Customer customer, MegreCardRequestModel model) throws Exception {
        int customerId = customer.getId();
        int packageCount = 2;
        if (null == model) {
            throw new ApiException(ResultStatus.RED_PACKAGE_NOT_EXIT);
        }
        List<Integer> ids = model.getPackageIds();
        if (null == ids || ids.size() != packageCount) {
            throw new ApiException(ResultStatus.RED_PACKAGE_NOT_EXIT);
        }
        ShareCardMongo shareCardMongo = shareCardMongoDao.getByCustomerIdAndType(customerId, SignGiftType.MERGE_CARD.getName());
        if (null == shareCardMongo) {
            throw new ApiException(ResultStatus.MERGE_CARD_NOT_EXIT);
        }
        List<ExtRedPackage> packageList = extRedPackageDao.getEffectiveExtRedPackageByCustomerIdAndIds(customerId, ids);
        if (packageList.size() != packageCount) {
            throw new ApiException(ResultStatus.RED_PACKAGE_NOT_EXIT);
        }
        double totalPrice = packageList.stream().mapToDouble(ExtRedPackage::getPrice).sum();

        ExtRedPackage firstPackage = packageList.get(0);
        extRedPackageDao.updateExtRedPackageIsUsedByCustomerIdAndIds(customerId, ids);
        sendRedPackage(customer, RedPackageType.MERGE_CARD.getName(), totalPrice, firstPackage.getExpTime(), 1,"");

        shareCardMongo.setDelete(true);
        shareCardMongoDao.save(shareCardMongo);
    }

    @Override
    public void delayedCard(int customerId, DelayedCardRequestModel model) throws Exception {
        if (null == model) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        int packageId = model.getPackageId();
        ShareCardMongo shareCardMongo = shareCardMongoDao.getByCustomerIdAndType(customerId, SignGiftType.DELAYED_CARD.getName());
        if (null == shareCardMongo) {
            throw new ApiException(ResultStatus.DELAYED_CARD_NOT_EXIT);
        }
        ExtRedPackage extRedPackage = extRedPackageDao.getEffectiveExtRedPackageByCustomerIdAndId(customerId, packageId);
        if (null == extRedPackage) {
            throw new ApiException(ResultStatus.RED_PACKAGE_NOT_EXIT);
        }
        int addDay = Constants.DELAYED_CARD_DAY;
        extRedPackageDao.updateExtRedPackageExpTimeByCustomerIdAndId(customerId, packageId, DateUtil.addDay(extRedPackage.getExpTime(), addDay));

        shareCardMongo.setDelete(true);
        shareCardMongoDao.save(shareCardMongo);
    }

    @Override
    public PageSignPackageModel pageSignPackage(int customerId, PageSignPackageRequestModel model) {
        PageHelper.startPage(model.getPageIndex(), Constants.PAGE_SIZE);
        Page<ExtRedPackage> page = extRedPackageDao.pageEffectiveExtRedPackageByCustomerIdOrderByType(customerId, model.getType());
        int pages = page.getPages();
        List<SignPackageViewModel> list = page.getResult().stream().map(
                en -> new SignPackageViewModel(
                        en.getId(),
                        DoubleUtil.toFixed(en.getPrice(), "0.00"),
                        DateUtil.format(en.getExpTime(), Constants.DATE_FORMAT),
                        DateUtil.subDateOfDay(en.getExpTime(), new Date()) < 0 ? 0 : DateUtil.subDateOfDay(en.getExpTime(), new Date())))
                .collect(Collectors.toList());
        PageHelper.clearPage();
        int cardCount = model.getPageIndex() <= 1 ? shareCardMongoDao.countByCustomerIdAndType(customerId, model.getType()) : 0;
        return new PageSignPackageModel(pages, cardCount, list);
    }

    @Override
    public void recevieSecretGift(int customerId, RrcevieSecretGiftRequestModel model) throws Exception {
        if (null == model) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }

        if (StringUtil.isNullOrEmpty(model.getName())) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }

        if (StringUtil.isNullOrEmpty(model.getPhone())) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }

        if (StringUtil.isNullOrEmpty(model.getAddress())) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }

        String type = SignGiftType.SECRET_GIFT.getName();
        int activityId = Activity.SECRET_GIFT_ID.getName();

        ShareCardMongo shareCard = shareCardMongoDao.getByCustomerIdAndIdAndType(customerId, model.getGiftId(), type);
        if (null == shareCard || shareCard.getStatus().equals(ShareCardState.RECEIVED.getName())) {
            throw new ApiException(ResultStatus.SECRET_GIFT_NOT_EXIT);
        }
        shareCard.setStatus(ShareCardState.RECEIVED.getName());
        shareCardMongoDao.save(shareCard);
        String info = model.getName() + " " + model.getPhone() + " " + model.getAddress();
        ExtActivityRecord activityRecord = new ExtActivityRecord(
                customerId,
                activityId,
                model.getName(),
                model.getPhone(),
                "领神秘礼物活动",
                new Date(),
                false,
                info,
                ActivityState.Audited.getName(),
                ActivityState.Audited.getName());
        activityRecordDao.insertExtActivityRecord(activityRecord);
    }

    @Override
    public void receiveRechargeGift(Customer customer, ReceiveRechargeGiftRequestModel model) throws Exception {
        if (null == model) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        String type = SignGiftType.TELEPHONE_CARD.getName();
        int activityId = Activity.TELEPHONE_CARD_ID.getName();

        ShareCardMongo shareCard = shareCardMongoDao.getByCustomerIdAndIdAndType(customer.getId(), model.getGiftId(), type);
        if (null == shareCard || shareCard.getStatus().equals(ShareCardState.RECEIVED.getName())) {
            throw new ApiException(ResultStatus.TELEPHONE_CARD_NOT_EXIT);
        }
        if (!RegExpMatcher.matcherMobile(model.getPhone())) {
            throw new ApiException(ResultStatus.INVALID_PHONE_ERROR);
        }

        // 1、充值话费（先充值再修改领取状态和添加领取记录，客户至上）
        Recharge.submit(model.getPhone(), shareCard.getCount(), model.getGiftId());

        // 2、修改话费卡状态为已领取
        shareCard.setStatus(ShareCardState.RECEIVED.getName());
        shareCardMongoDao.save(shareCard);

        // 3、添加领取活动记录
        String info = "用户" + customer.getName() + "给手机号" + model.getPhone() + "使用充值卡：" + model.getGiftId();
        ExtActivityRecord activityRecord = new ExtActivityRecord(
                customer.getId(),
                activityId,
                customer.getName(),
                model.getPhone(),
                "领取话费充值卡活动",
                new Date(),
                false,
                info,
                ActivityState.Audited.getName(),
                ActivityState.Audited.getName());
        activityRecordDao.insertExtActivityRecord(activityRecord);
    }

    private String formatGiftTitle(SignGiftType type, int count, double price) {
        switch (type) {
            case RED_PACKAGE:
                return "收益红包" + DoubleUtil.toFixed(price, "0.00") + "元";
            case SECRET_GIFT:
                return "神秘礼包" + count + "份";
            case DELAYED_CARD:
                return "延时卡" + count + "张";
            case MERGE_CARD:
                return "合并卡" + count + "张";
            case TELEPHONE_CARD:
                return (int) price + "元话费充值卡";
            default:
                return "";
        }
    }

    private String formatGiftShortTitle(SignGiftType type, int count, double price) {
        switch (type) {
            case RED_PACKAGE:
                return DoubleUtil.toFixed(price, "0.00");
            case SECRET_GIFT:
                return "神秘礼包";
            case DELAYED_CARD:
                return "延时卡";
            case MERGE_CARD:
                return "合并卡";
            case TELEPHONE_CARD:
                return (int) price + "元话费充值卡";
            default:
                return "";
        }
    }

    private String formatGiftUnit(SignGiftType type) {
        switch (type) {
            case RED_PACKAGE:
                return "个";
            case SECRET_GIFT:
                return "份";
            case DELAYED_CARD:
            case MERGE_CARD:
            case TELEPHONE_CARD:
                return "张";
            default:
                return "";
        }
    }

    private SignGiftAddressViewModel formartAddress(int customerId) {
        SignGiftAddressViewModel signGiftAddressViewModel = null;
        CustomerAddress customerAddress = customerAddressDao.getCustomerAddressByCustomerId(customerId);
        if (null != customerAddress) {
            signGiftAddressViewModel = new SignGiftAddressViewModel();
            signGiftAddressViewModel.setName(customerAddress.getName());
            signGiftAddressViewModel.setPhone(customerAddress.getPhone());
            signGiftAddressViewModel.setAddress(
                    StringUtil.nullOrEmptyReturnDefault(customerAddress.getProvinceName()) +
                            StringUtil.nullOrEmptyReturnDefault(customerAddress.getCityName()) +
                            StringUtil.nullOrEmptyReturnDefault(customerAddress.getCountyName()) +
                            StringUtil.nullOrEmptyReturnDefault(customerAddress.getAddress()));
        }
        return signGiftAddressViewModel;
    }
    //endregion
}
