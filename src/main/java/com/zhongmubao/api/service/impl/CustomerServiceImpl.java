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
import com.zhongmubao.api.dto.Request.OnlyPrimaryIdRequestModel;
import com.zhongmubao.api.dto.Request.*;
import com.zhongmubao.api.dto.Request.Address.CustomerAddressRequestModel;
import com.zhongmubao.api.dto.Request.Address.UpdateCustomerAddressRequestModel;
import com.zhongmubao.api.dto.Request.customer.AutoRedeemRequestModel;
import com.zhongmubao.api.dto.Request.customer.ResetPasswordRequestModel;
import com.zhongmubao.api.dto.Response.Address.CustomerAddressResponseModel;
import com.zhongmubao.api.dto.Response.Address.CustomerAddressViewModel;
import com.zhongmubao.api.dto.Response.Ext.PageExtRedPackageModel;
import com.zhongmubao.api.dto.Response.Ext.PageExtRedPackageViewModel;
import com.zhongmubao.api.dto.Response.Sign.*;
import com.zhongmubao.api.dto.Response.Sign.SignList.PageSignGiftModel;
import com.zhongmubao.api.dto.Response.Sign.SignList.PageSignGiftViewModel;
import com.zhongmubao.api.dto.Response.Sign.SignPackageList.PageSignPackageModel;
import com.zhongmubao.api.dto.Response.Sign.SignPackageList.SignPackageViewModel;
import com.zhongmubao.api.dto.SignGift;
import com.zhongmubao.api.entity.*;
import com.zhongmubao.api.exception.ApiException;
import com.zhongmubao.api.mongo.dao.ShareCardMongoDao;
import com.zhongmubao.api.mongo.dao.SystemSMSLogMongoDao;
import com.zhongmubao.api.mongo.entity.ShareCardMongo;
import com.zhongmubao.api.mongo.entity.SystemSMSLogMongo;
import com.zhongmubao.api.mongo.entity.base.PageModel;
import com.zhongmubao.api.service.BaseService;
import com.zhongmubao.api.service.CustomerService;
import com.zhongmubao.api.util.*;
import com.zhongmubao.api.util.redis.RedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.zhongmubao.api.config.enmu.SignGiftType.*;


@Service
public class CustomerServiceImpl extends BaseService implements CustomerService {

    private final CustomerDao customerDao;
    private final ExtRedPackageDao extRedPackageDao;
    private final SheepOrderDao sheepOrderDao;
    private final ShareCardMongoDao shareCardMongoDao;
    private final CustomerAddressDao customerAddressDao;
    private final ExtActivityRecordDao activityRecordDao;
    private final TokenManager tokenManager;
    private final RedisCache redisCache;
    private final SystemSMSLogMongoDao systemSMSLogMongoDao;

    @Autowired
    public CustomerServiceImpl(CustomerDao customerDao, ExtRedPackageDao extRedPackageDao, SheepOrderDao sheepOrderDao, ShareCardMongoDao shareCardMongoDao, CustomerAddressDao customerAddressDao, ExtActivityRecordDao activityRecordDao, TokenManager tokenManager, RedisCache redisCache, SystemSMSLogMongoDao systemSMSLogMongoDao) {
        this.customerDao = customerDao;
        this.extRedPackageDao = extRedPackageDao;
        this.sheepOrderDao = sheepOrderDao;
        this.shareCardMongoDao = shareCardMongoDao;
        this.customerAddressDao = customerAddressDao;
        this.activityRecordDao = activityRecordDao;
        this.tokenManager = tokenManager;
        this.redisCache = redisCache;
        this.systemSMSLogMongoDao = systemSMSLogMongoDao;
    }

    @Override
    public String login(String account, String password, String platform) throws Exception {

        //SystemTokenMongo systemToken = systemTokenMongoDao.getByCustomerIdAndPlatform(4194,"00");

        Customer customer = customerDao.getCustomerByAccountAndPassword(account, password);
        if (customer == null) {
            throw new Exception("登录失败");
        }
        Date now = new Date();
        customer.setAccount(null);
        customer.setPassword(null);
        customerDao.addCustomer(customer);
        int id = customerDao.insertCustomer(
                "234",
                "234",
                "234",
                "234",
                "234",
                "234",
                "234",
                "234",
                "00",
                "234",
                "3",
                0,
                false,
                0,
                "00",
                "",
                "",
                false,
                now,
                now,
                "",
                false,
                0,
                true,
                true
        );
        return tokenManager.createToken(customer.getId(), platform).getToken();
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
        Date expTime = DateUtil.addDay(now, Constants.DAY_SHARE_REDPACKAGE_EXP_DAY); //红包过期时间
        Date boundaryTime = DateUtil.addHours(dayBegin, 10); //签到时间分界点
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
                boolean todayIsShare = extRedPackageDao.countExtRedPackageByCustomerIdAndBeginTimeAndEndTimeAndType(customerId, dayBegin, dayEnd, dayShareType) > 0;// redisCache.getCustomerIsShare(customerId);
                // todo:去掉测试账号代码
                if (todayIsShare && customer.getId() != 4194) {
                    return new com.zhongmubao.api.dto.Response.Sign.SignModel(shareDayCount, "0.00", null, null, todayIsShare, false);
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

                //region 礼物 todo:去掉测试账号代码
                if (giftDayList.contains(shareDayCount) || customer.getId() == 4194) {
                    // 随机获取神秘礼物
                    int randomIndex = MathUtil.random(0, Constants.SIGN_GIFT_LIST.size());
                    if (customer.getId() == 4194) {
                        randomIndex = shareDayCount / 2 == 0 ? 8 : 9;
                    }
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
                    signGiftViewModel.setTelephoneCharge(telephoneMoney);
                }

                redisCache.saveCustomerIsShare(customerId);//设置今天已分享

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
     * @param customerId 客户id
     * @param model 请求参数对象
     * @return PageSignGiftModel
     */
    @Override
    public PageSignGiftModel pageGift(int customerId, PageSignGiftRequestModel model) throws Exception {
        if (null == model) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        PageModel<ShareCardMongo> pager = new PageModel<>();
        pager.setPageNo(model.getPageIndex());
        pager = shareCardMongoDao.pager(customerId, true, pager);

        List<PageSignGiftViewModel> list = pager.getDatas().stream()
                .map(en -> new PageSignGiftViewModel(
                        en.id,
                        en.getTitle(),
                        en.getCount(),
                        en.getStatus(),
                        en.getType(),
                        DateUtil.format(en.getCreated(), "yyyy.MM.dd")))
                .collect(Collectors.toList());
        return new PageSignGiftModel(pager.getTotalPages(), list, formartAddress(customerId));
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
        if (null == model) {
            throw new ApiException(ResultStatus.RED_PACKAGE_NOT_EXIT);
        }
        List<Integer> ids = model.getPackageIds();
        if (null == ids || ids.size() != 2) {
            throw new ApiException(ResultStatus.RED_PACKAGE_NOT_EXIT);
        }
        ShareCardMongo shareCardMongo = shareCardMongoDao.getByCustomerIdAndType(customerId, SignGiftType.MERGE_CARD.getName());
        if (null == shareCardMongo) {
            throw new ApiException(ResultStatus.MERGE_CARD_NOT_EXIT);
        }
        List<ExtRedPackage> packageList = extRedPackageDao.getEffectiveExtRedPackageByCustomerIdAndIds(customerId, ids);
        if (packageList.size() != 2) {
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
                        DateUtil.format(en.getExpTime(), "yyyy-MM-dd"),
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

        // 1、充值话费（先充值再修改领取状态和添加领取记录，客户至上）
        Recharge.submit(model.getPhone(), shareCard.getCount());

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
                return (int)price + "元话费充值卡";
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
                return (int)price + "元话费充值卡";
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

    // region Customer RedPackage

    /***
     * 获取用户红包
     * @param customerId 客户id
     * @param model 请求参数对象
     * @author 米立林
     * @return PageExtRedPackageModel
     */
    @Override
    public PageExtRedPackageModel pageExtRedPackage(int customerId, PageExtRedPackageRequestModel model) throws Exception {
        if (null == model || null == model.getSortType()) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }

        PageHelper.startPage(model.getPageIndex(), Constants.PAGE_SIZE);
        Page<ExtRedPackage> page = extRedPackageDao.pageEffectiveExtRedPackageByCustomerId(customerId, model.getSortType().getName());
        int pages = page.getPages();
        List<PageExtRedPackageViewModel> list = page.getResult().stream().map(
                en -> new PageExtRedPackageViewModel(
                        Double.toString(en.getPrice()),
                        DateUtil.format(en.getExpTime(), "yyyy-MM-dd")
                ))
                .collect(Collectors.toList());
        PageHelper.clearPage();
        return new PageExtRedPackageModel(pages, list);
    }
    // endregion

    //region Customer Address

    /***
     * 新增收货地址
     * @param customerId 客户id
     * @param model 请求参数对象
     * @author 米立林
     * @return 受影响的行数
     */
    @Override
    public int addCustomerAddress(int customerId, CustomerAddressRequestModel model) throws Exception {
        if (null == model) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        boolean isNoPass = VerifyCustomerAddress(
                model.getProvinceCode(),
                model.getProvinceName(),
                model.getCityCode(),
                model.getCityName(),
                model.getCountyCode(),
                model.getCountyName(),
                model.getName(),
                model.getPhone());
        if (isNoPass) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }

        CustomerAddress customerAddress = new CustomerAddress(
                model.getCustomerId(),
                model.getProvinceCode(),
                model.getProvinceName(),
                model.getCityCode(),
                model.getCityName(),
                model.getCountyCode(),
                model.getCountyName(),
                model.getAddress(),
                model.getName(),
                model.getPhone(),
                model.getIsDefault(),
                false,
                new Date());
        int sucRows = customerAddressDao.insertCustomerAddress(customerAddress);

        return sucRows;
    }

    /***
     * 删除收货地址
     * @param customerId 客户id
     * @param model 请求参数对象
     * @return 受影响的行数
     */
    @Override
    public int deleteCustomerAddress(int customerId, OnlyPrimaryIdRequestModel model) throws Exception {
        if (null == model) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
//        int sucRows = customerAddressDao.deleteCustomerAddress(model.getId());
        // 此处修改为逻辑删除
        int sucRows = customerAddressDao.logicDeleteByIdAndCustomerId(customerId, model.getId());

        return sucRows;
    }

    /***
     * 更新收货地址
     * @param customerId 客户id
     * @param model 请求参数对象
     * @return 受影响的行数
     */
    @Override
    public int updateCustomerAddress(int customerId, UpdateCustomerAddressRequestModel model) throws Exception {
        if (null == model) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        boolean isNoPass = VerifyCustomerAddress(
                model.getProvinceCode(),
                model.getProvinceName(),
                model.getCityCode(),
                model.getCityName(),
                model.getCountyCode(),
                model.getCountyName(),
                model.getName(),
                model.getPhone());
        if (isNoPass) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }

        // 更新数据
        int sucRows = customerAddressDao.updateCustomerAddressInfo(
                model.getCustomerId(),
                model.getId(),
                model.getProvinceCode(),
                model.getProvinceName(),
                model.getCityCode(),
                model.getCityName(),
                model.getCountyCode(),
                model.getCountyName(),
                model.getAddress(),
                model.getName(),
                model.getPhone(),
                DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss")
        );

        return sucRows;
    }

    /**
     * 获取当前用户的所有收货地址
     *
     * @param customerId
     * @param model
     * @return
     * @throws Exception
     */
    @Override
    public CustomerAddressResponseModel getCustomerAddress(int customerId, OnlyCustomerIdRequestModel model) throws Exception {
        if (null == model) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        List<CustomerAddress> customerAddressList = customerAddressDao.getCustomerAddressListByCustomerId(customerId);
        List<CustomerAddressViewModel> list = customerAddressList.stream().map(
                en -> new CustomerAddressViewModel(
                        en.getId(), en.getCustomerId(),
                        en.getProvinceCode(), en.getProvinceName(),
                        en.getCityCode(), en.getCityName(),
                        en.getCountyCode(), en.getCountyName(),
                        en.getAddress(), en.getName(), en.getPhone(),
                        en.getIsDefault(), en.getDeleted(),
                        DateUtil.format(en.getCreated(), "yyyy-MM-dd"),
                        DateUtil.format(en.getModified(), "yyyy-MM-dd")
                ))
                .collect(Collectors.toList());
        return new CustomerAddressResponseModel(list);
    }
    //endregion

    //region security check

    /**
     * 校验客户地址数据安全性和有效性
     *
     * @return true or false
     * @author 米立林
     */
    private boolean VerifyCustomerAddress(String provinceCode, String provinceName, String cityCode, String cityName,
                                          String countyCode, String countyName, String name, String mobile) {
        boolean isNoPass = false;
        // 非空校验
        if (StringUtil.isNullOrEmpty(provinceCode) || StringUtil.isNullOrEmpty(cityCode)
                || StringUtil.isNullOrEmpty(countyCode) || StringUtil.isNullOrEmpty(name)
                || StringUtil.isNullOrEmpty(mobile)) {
            isNoPass = true;
            return isNoPass;
        }
        // 安全有效校验
        List<SystemDistrict> systemDistrictList = redisCache.getDistrictList();
        SystemDistrict province = systemDistrictList.stream().filter(s -> s.getCode().equals(provinceCode)).findFirst().get();
        SystemDistrict city = systemDistrictList.stream().filter(s -> s.getCode().equals(cityCode)).findFirst().get();
        SystemDistrict county = systemDistrictList.stream().filter(s -> s.getCode().equals(countyCode)).findFirst().get();
        if (!(province.getName().equals(provinceName)
                && city.getName().equals(cityName)
                && county.getName().equals(countyName))) {
            isNoPass = true;
            return isNoPass;
        }

        if (!(RegExpMatcher.MatcherMobile(mobile))) {
            isNoPass = true;
        }
        return isNoPass;
    }

    //endregion

    //region 个人中心--设置

    /**
     * 重置登录密码
     *
     * @param customerId 当前用户
     * @param model      参数请求request
     * @throws Exception
     * @author 米立林 2017-09-30
     */
    @Override
    public void resetPassword(int customerId, ResetPasswordRequestModel model) throws Exception {
        if (null == model) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        // 1、校验用户
        Customer customer = customerDao.getCustomerById(customerId);
        if (null == customer || customer.getPhone() != model.getPhone()) {
            throw new ApiException(ResultStatus.PARAMETER_ERROR);
        }
        // 2、校验验证码类型
        if (SmsType.VERIFICATION.getName() != model.getType()) {
            throw new ApiException(ResultStatus.PARAMETER_ERROR);
        }
        Query query = new Query();
        query.addCriteria(Criteria.where("Type").is(model.getType()));
        query.addCriteria(Criteria.where("Phone").is(model.getPhone()));
        query.addCriteria(Criteria.where("Expired").gt(DateUtil.formatMongo(new Date())));
        SystemSMSLogMongo smsAuthMongo = systemSMSLogMongoDao.getListByPeriod(query);
        if (smsAuthMongo.getCode() != model.getVerificationCode()) {
            throw new ApiException(ResultStatus.PARAMETER_CODE_ERROR);
        }
        // 3、修改登录密码
        customerDao.UpdatePassword(customer.getId(), model.getNewPassword());
        // 4、修改验证码为已过期
        smsAuthMongo.setExpired(DateUtil.formatMongo(new Date()));
        systemSMSLogMongoDao.update(smsAuthMongo);
    }

    /**
     * 重置赎回密码
     *
     * @param customerId 当前用户
     * @param model      参数请求request
     * @throws Exception
     * @author 米立林 2017-09-30
     */
    @Override
    public void resetRedeemPassword(int customerId, ResetPasswordRequestModel model) throws Exception {
        if (null == model) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        // 1、校验用户
        Customer customer = customerDao.getCustomerById(customerId);
        if (null == customer || customer.getPhone() != model.getPhone()) {
            throw new ApiException(ResultStatus.PARAMETER_ERROR);
        }
        // 2、校验验证码类型
        if (SmsType.REDEEM_PWD.getName() != model.getType()) {
            throw new ApiException(ResultStatus.PARAMETER_ERROR);
        }
        Query query = new Query();
        query.addCriteria(Criteria.where("Type").is(model.getType()));
        query.addCriteria(Criteria.where("Phone").is(model.getPhone()));
        query.addCriteria(Criteria.where("Expired").gt(DateUtil.formatMongo(new Date())));
        SystemSMSLogMongo smsAuthMongo = systemSMSLogMongoDao.getListByPeriod(query);
        if (smsAuthMongo.getCode() != model.getVerificationCode()) {
            throw new ApiException(ResultStatus.PARAMETER_CODE_ERROR);
        }
        // 3、修改赎回密码
        customerDao.UpdateRedeemPassword(customer.getId(), model.getNewPassword());
        // 4、修改验证码为已过期
        smsAuthMongo.setExpired(DateUtil.formatMongo(new Date()));
        systemSMSLogMongoDao.update(smsAuthMongo);

    }

    /**
     * 开启/关闭自动赎回
     *
     * @param customerId 当前用户
     * @param model      赎回密码
     * @throws Exception
     */
    @Override
    public boolean autoRedeemAmount(int customerId, AutoRedeemRequestModel model) throws Exception {
        if (null == model) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        boolean isSuccess = false;
        // 1、验证参数
        Customer customer = customerDao.getCustomerById(customerId);
        if (null == customer || StringUtil.isNullOrEmpty(model.getRedeemPassword())) {
            throw new ApiException(ResultStatus.PARAMETER_ERROR);
        }
        // 2、校验用户赎回密码
        if (model.getRedeemPassword().equals(customer.getRedeemPassword())) {
            // 设置自动赎回
            customerDao.UpdateIsAutoRedeem(customerId, model.getIsAutoRedeem());
            isSuccess = true;
        }
        return isSuccess;
    }
    //endregion

}