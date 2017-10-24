package com.zhongmubao.api.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhongmubao.api.authorization.manager.TokenManager;
import com.zhongmubao.api.cache.RedisCache;
import com.zhongmubao.api.components.recharge.Recharge;
import com.zhongmubao.api.config.Constants;
import com.zhongmubao.api.config.ResultStatus;
import com.zhongmubao.api.config.enmu.*;
import com.zhongmubao.api.dao.*;
import com.zhongmubao.api.dto.request.sign.*;
import com.zhongmubao.api.dto.response.sign.*;
import com.zhongmubao.api.dto.response.sign.signList.PageSignGiftModel;
import com.zhongmubao.api.dto.response.sign.signList.PageSignGiftViewModel;
import com.zhongmubao.api.dto.response.sign.signPackageList.PageSignPackageModel;
import com.zhongmubao.api.dto.response.sign.signPackageList.SignPackageViewModel;
import com.zhongmubao.api.config.entity.SignGift;
import com.zhongmubao.api.entity.*;
import com.zhongmubao.api.exception.ApiException;
import com.zhongmubao.api.mongo.dao.ShareCardMongoDao;
import com.zhongmubao.api.mongo.entity.*;
import com.zhongmubao.api.mongo.entity.base.PageModel;
import com.zhongmubao.api.service.BaseService;
import com.zhongmubao.api.service.CustomerService;
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
public class CustomerServiceImpl extends BaseService implements CustomerService {

    private final CustomerDao customerDao;
    private final ExtRedPackageDao extRedPackageDao;
    private final ShareCardMongoDao shareCardMongoDao;
    private final CustomerAddressDao customerAddressDao;
    private final TokenManager tokenManager;
    private final RedisCache redisCache;
    private final SheepOrderDao sheepOrderDao;
    private final ExtActivityRecordDao activityRecordDao;

    @Autowired
    public CustomerServiceImpl(CustomerDao customerDao, ExtRedPackageDao extRedPackageDao, ShareCardMongoDao shareCardMongoDao, CustomerAddressDao customerAddressDao, TokenManager tokenManager, RedisCache redisCache, SheepOrderDao sheepOrderDao, ExtActivityRecordDao activityRecordDao) {
        this.customerDao = customerDao;
        this.extRedPackageDao = extRedPackageDao;
        this.shareCardMongoDao = shareCardMongoDao;
        this.customerAddressDao = customerAddressDao;
        this.tokenManager = tokenManager;
        this.redisCache = redisCache;
        this.sheepOrderDao = sheepOrderDao;
        this.activityRecordDao = activityRecordDao;
    }

    //region 签到相关

    /***
     * 签到
     * @param customer 客户id
     * @return PageSignGiftModel
     * @throws Exception
     */
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
                // 分享天数
                int shareDayCount = extRedPackageDao.countExtRedPackageByCustomerIdAndBeginTimeAndEndTimeAndType(customerId, monthBegin, monthEnd, dayShareType);

                //region 验证
                boolean todayIsShare = extRedPackageDao.countExtRedPackageByCustomerIdAndBeginTimeAndEndTimeAndType(customerId, dayBegin, dayEnd, dayShareType) > 0;

                if (todayIsShare) {
                    return new com.zhongmubao.api.dto.response.sign.SignModel(shareDayCount, "0.00", null, null, todayIsShare, false);
                }

                //判断这个月分享了多少次
                int monthMaxShare = Constants.MONTH_MAX_SHARE;

                if (shareDayCount > monthMaxShare) {
                    throw new ApiException("本月分享达到最大分享数,请下月再来");
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
                sendRedPackage(customer, dayShareType, price, expTime, 1);

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
                        Date buySheepEndTime = now;
                        int buySheepCount = sheepOrderDao.countSheepOrderByCustomerIdAndBeginTimeAndEndTimeAndState(customerId, buySheepBeginTime, buySheepEndTime, Constants.SHEEP_IN_THE_BAR_STATE);
                        if (buySheepCount <= 0) {
                            // 没买羊默认奖励红包
                            signGift = Constants.SIGN_GIFT_LIST.get(0);
                        } else {
                            signGiftAddressViewModel = formartAddress(customerId);
                        }
                    }
                    // 如果中奖是红包
                    double redPackagePrice = 0;
                    if (signGift.getType().equals(RED_PACKAGE)) {
                        signGiftRedPackageViewModel = new SignGiftRedPackageViewModel();
                        redPackagePrice = MathUtil.random(10, 200) * 0.01;
                        signGiftRedPackageViewModel.setPrice(DoubleUtil.toFixed(redPackagePrice, "0.00"));
                        //添加红包
                        sendRedPackage(customer, dayShareType, redPackagePrice, expTime, 1);
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
                //设置今天已分享
                redisCache.saveCustomerIsShare(customerId);

                //endregion
                return new SignModel(shareDayCount, DoubleUtil.toFixed(price, "0.00"), signInfo, signGiftViewModel, todayIsShare, true);

                //endregion
            }
        } finally {
            lock.unlock();
        }
        throw new ApiException(ResultStatus.FAIL);
    }

    /***
     * 客户中奖记录
     * @param customer 当前客户
     * @param model 请求参数对象
     * @return PageSignGiftModel
     */
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
                        DateUtil.format(en.getCreated(), "yyyy.MM.dd")))
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
        double totalPrice = packageList.stream().mapToDouble(en -> en.getPrice()).sum();

        ExtRedPackage firstPackage = packageList.get(0);
        extRedPackageDao.updateExtRedPackageIsUsedByCustomerIdAndIds(customerId, ids);
        sendRedPackage(customer, RedPackageType.MERGE_CARD.getName(), totalPrice, firstPackage.getExpTime(), 1);

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

    /**
     * 领取神秘卡
     *
     * @param customerId 用户id
     * @param model      请求参数
     * @throws Exception
     * @author 孙阿龙
     */
    @Override
    public void recevieSecretGift(int customerId, RrcevieSecretGiftRequestModel model) throws Exception {
        if (null == model) {
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
        //(int customerId, int extActivityId, String name, String phone, String remark, Date created, boolean deleted, String info, String state, String auditReason)
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

    /**
     * 领取话费充值卡
     *
     * @param customer 当前用户
     * @param model    请求参数
     * @throws Exception
     * @author 米立林 2017-10-10
     */
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
