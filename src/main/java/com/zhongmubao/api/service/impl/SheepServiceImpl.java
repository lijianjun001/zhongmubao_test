package com.zhongmubao.api.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhongmubao.api.cache.RedisCache;
import com.zhongmubao.api.config.Constants;
import com.zhongmubao.api.config.ResultStatus;
import com.zhongmubao.api.config.enmu.*;
import com.zhongmubao.api.dao.*;
import com.zhongmubao.api.dto.request.OnlyPrimaryIdRequestModel;
import com.zhongmubao.api.dto.request.PageIndexRequestModel;
import com.zhongmubao.api.dto.request.ProjectPlanRequestModel;
import com.zhongmubao.api.dto.request.sheep.MySheepRoomRequestModel;
import com.zhongmubao.api.dto.request.sheep.SheepOrderRequestModel;
import com.zhongmubao.api.dto.request.SystemMonitorRequestModel;
import com.zhongmubao.api.dto.response.customer.AutoRedeemModel;
import com.zhongmubao.api.dto.response.index.*;
import com.zhongmubao.api.dto.response.sheep.*;
import com.zhongmubao.api.dto.response.sheepfold.SheepProjectOrdersModel;
import com.zhongmubao.api.dto.response.sheepfold.SheepProjectOrdersViewModel;
import com.zhongmubao.api.entity.*;
import com.zhongmubao.api.entity.ext.*;
import com.zhongmubao.api.exception.ApiException;
import com.zhongmubao.api.mongo.dao.CustomerOrderLogMongoDao;
import com.zhongmubao.api.mongo.dao.ExtBannerMongoDao;
import com.zhongmubao.api.mongo.dao.SheepStageMongoDao;
import com.zhongmubao.api.mongo.entity.CustomerOrderLogMongo;
import com.zhongmubao.api.mongo.entity.SheepStageMongo;
import com.zhongmubao.api.mongo.entity.base.PageModel;
import com.zhongmubao.api.service.BaseService;
import com.zhongmubao.api.service.SheepService;
import com.zhongmubao.api.util.*;
import com.zhongmubao.api.dto.common.SheepVendorAttrs;
import com.zhongmubao.api.dto.CurrentSheepProjectState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SheepServiceImpl extends BaseService implements SheepService {

    //region 注入
    private final ExtBannerMongoDao extBannerMongoDao;
    private final SheepStageMongoDao sheepStageMongoDao;
    private final SheepProjectDao sheepProjectDao;
    private final SheepProjectPlanDao sheepProjectPlanDao;
    private final SheepOrderDao sheepOrderDao;
    private final SheepLevelDao levelDao;
    private final ExtActivityRecordDao extActivityRecordDao;
    private final CustomerSinaDao customerSinaDao;
    private final RedisCache redisCache;
    private final SheepVendorDao sheepVendorDao;
    private final SheepLevelDao sheepLevelDao;
    private final CustomerOrderLogMongoDao customerOrderLogMongoDao;
    private final SheepPhotoDao sheepPhotoDao;
    private final CustomerDao customerDao;

    @Autowired
    public SheepServiceImpl(RedisCache redisCache, CustomerSinaDao customerSinaDao, ExtActivityRecordDao extActivityRecordDao, SheepOrderDao sheepOrderDao, SheepProjectDao sheepProjectDao, ExtBannerMongoDao extBannerMongoDao, SheepProjectPlanDao sheepProjectPlanDao, SheepStageMongoDao sheepStageMongoDao, SheepLevelDao levelDao, SheepVendorDao sheepVendorDao, SheepLevelDao sheepLevelDao, CustomerOrderLogMongoDao customerOrderLogMongoDao, SheepPhotoDao sheepPhotoDao, CustomerDao customerDao) {
        this.redisCache = redisCache;
        this.customerSinaDao = customerSinaDao;
        this.extActivityRecordDao = extActivityRecordDao;
        this.sheepOrderDao = sheepOrderDao;
        this.sheepProjectDao = sheepProjectDao;
        this.extBannerMongoDao = extBannerMongoDao;
        this.sheepProjectPlanDao = sheepProjectPlanDao;
        this.sheepStageMongoDao = sheepStageMongoDao;
        this.levelDao = levelDao;
        this.sheepVendorDao = sheepVendorDao;
        this.sheepLevelDao = sheepLevelDao;
        this.customerOrderLogMongoDao = customerOrderLogMongoDao;
        this.sheepPhotoDao = sheepPhotoDao;
        this.customerDao = customerDao;
    }


    //endregion

    //region 首页
    @Override
    public IndexModel index(Customer customer) throws Exception {
        //region 字段
        List<SheepVendor> sheepVendorList = redisCache.vendorList();
        Date beginTime = DateUtil.dayBegin();
        Date endTime = DateUtil.dayEnd();
        Date now = new Date();
        CustomerIndexModel customerIndexModel = null;
        List<ProjectViewModel> projectIndexList;
        List<ProjectViewModel> newPeopleProjectIndexList;
        boolean isShowNewProject = true;
        NewPeopleProjectViewModel newPeopleProjectViewModel = new NewPeopleProjectViewModel();
        //endregion

        //region 首页轮播
        List<BannerViewModel> bannerList = extBannerMongoDao.pager(new PageModel<>()).getDatas().stream()
                .map(en -> new BannerViewModel("", en.getTitle(), en.getLink(), ApiUtil.formatImg(en.getImgUrl())))
                .collect(Collectors.toList());
        //endregion

        //region 首页标
        List<SheepProjectIndex> projectList = sheepProjectDao.getIndexSheepProject(Constants.PAID_STATE, Constants.NO_PAY_STATE, beginTime);
        List<SheepProjectIndex> todaySheepList = projectList.stream().filter(en -> en.getBeginTime().getTime() < endTime.getTime()).collect(Collectors.toList());
        //抢羊
        List<SheepProjectIndex> buySheepList = todaySheepList.stream().filter(en -> en.getCount() - en.getNoPayCount() - en.getPaidCount() > 0 && en.getBeginTime().getTime() <= now.getTime()).collect(Collectors.toList());
        //预售
        List<SheepProjectIndex> preSheepList = projectList.stream().filter(en -> en.getBeginTime().getTime() > now.getTime()).collect(Collectors.toList());
        //捡漏
        List<SheepProjectIndex> leakSheepList = todaySheepList.stream().filter(en -> en.getCount() - en.getPaidCount() > 0 && en.getCount() - en.getPaidCount() - en.getNoPayCount() <= 0).collect(Collectors.toList());
        //售罄
        List<SheepProjectIndex> soldSheepList = todaySheepList.stream().filter(en -> en.getCount() - en.getPaidCount() <= 0).collect(Collectors.toList());

        List<ProjectViewModel> list = new ArrayList<>();
        list.addAll(buySheepList.stream().map(en -> formartProject(en, ProjectSaleState.BUY_SHEEP, sheepVendorList)).collect(Collectors.toList()));
        list.addAll(preSheepList.stream().map(en -> formartProject(en, ProjectSaleState.PRE_SHEEP, sheepVendorList)).collect(Collectors.toList()));
        list.addAll(leakSheepList.stream().map(en -> formartProject(en, ProjectSaleState.LEAK_SHEEP, sheepVendorList)).collect(Collectors.toList()));
        list.addAll(soldSheepList.stream().map(en -> formartProject(en, ProjectSaleState.SOLD_SHEEP, sheepVendorList)).collect(Collectors.toList()));

        projectIndexList = list.stream().filter(en -> !en.getType().equals(ProjectType.NEW_PEOPLE_120.getName()) && !en.getType().equals(ProjectType.NEW_PEOPLE_7.getName())).collect(Collectors.toList());
        newPeopleProjectIndexList = list.stream().filter(en -> en.getType().equals(ProjectType.NEW_PEOPLE_120.getName()) || en.getType().equals(ProjectType.NEW_PEOPLE_7.getName())).collect(Collectors.toList());

        newPeopleProjectViewModel.setProjectList(newPeopleProjectIndexList);
        //endregion

        //region 能获取到客户的情况
        if (null != customer) {
            //region 客户信息
            int customerId = customer.getId();
            CustomerSina customerSina = customerSinaDao.getCustomerSinaByCustomerId(customerId);

            int sheepCount = sheepOrderDao.sumSheepOrderCountByCustomerAndStates(customerId, Constants.SHEEP_IN_THE_BAR_STATE);
            boolean isRealName = customerSina != null && customerSina.getIsRealName();
            boolean isSetRedeemPwd = StringUtil.isNullOrEmpty(customer.getRedeemPassword());
            boolean todayIsShare = redisCache.getCustomerIsShare(customerId);
            customerIndexModel = new CustomerIndexModel(0, sheepCount, isRealName, isSetRedeemPwd, todayIsShare);
            //endregion
            //region 新手标
            if (customer.getCreated().getTime() > DateUtil.strToDate("2016-10-03 00:00:00").getTime()) {
                if (redisCache.getNewPeopleProjectIsShow(customerId)) {
                    //如果肖恩没有领取过
                    if (null == extActivityRecordDao.getExtActivityRecordrByCustomerIdAndActivityId(customerId, Constants.ACTIVITY_ID_XIN_SHOU)) {
                        newPeopleProjectViewModel = newPeopleProject(customer);
                        newPeopleProjectViewModel.setProjectList(newPeopleProjectIndexList);
                        isShowNewProject = !(newPeopleProjectViewModel.isExped7() || newPeopleProjectViewModel.isExped120());

                        if (!isShowNewProject) {//如果不显示，则记录到redis里
                            redisCache.saveNewPeopleProjectIsShow(customerId);
                        }
                    }
                }
            }
            //endregion
        }
        //endregion

        return new IndexModel(
                bannerList,
                projectIndexList,
                customerIndexModel,
                newPeopleProjectViewModel,
                isShowNewProject);
    }

    private NewPeopleProjectViewModel newPeopleProject(Customer customer) {
        Date now = new Date();
        int customerId = customer.getId();
        boolean exped120 = false;
        boolean exped7 = false;
        boolean isBuyed120 = false;
        boolean isBuyed7 = false;
        boolean canReceiveXiaoEn = false;
        boolean canBuy120 = false;
        boolean canBuy7 = false;
        int surplusDay7 = 0;
        SheepOrderCountAndMinCreated sheepOrderCountAndMinCreated120 = sheepOrderDao.getSheepOrderCountAndMinCreatedByCustomerAndStatesAndProjectType(customerId, Constants.SHEEP_IN_THE_BAR_STATE_AND_REDEMING_ANDREDEMED, ProjectType.NEW_PEOPLE_120.getName());
        SheepOrderCountAndMinCreated sheepOrderCountAndMinCreated7 = sheepOrderDao.getSheepOrderCountAndMinCreatedByCustomerAndStatesAndProjectType(customerId, Constants.SHEEP_IN_THE_BAR_STATE_AND_REDEMING_ANDREDEMED, ProjectType.NEW_PEOPLE_7.getName());
        //06
        if (sheepOrderCountAndMinCreated120.getCount() > 0) {
            //已购买可以领取肖恩
            canReceiveXiaoEn = true;
        } else {
            if (sheepOrderCountAndMinCreated7.getCount() > 0) {
                Date created7 = DateUtil.strToDate(sheepOrderCountAndMinCreated7.getCreated());
                Date endTime120Exp = DateUtil.addDay(created7, Constants.EXP_120_NEW_PROJECT);
                if (endTime120Exp.getTime() < now.getTime()) {
                    exped120 = true;
                }
                canBuy120 = true;
            } else {
                canBuy120 = true;
            }
        }
        //04
        if (sheepOrderCountAndMinCreated7.getCount() > 0) {
            isBuyed7 = true;
        } else {
            Date endTime7Exp = DateUtil.addDay(customer.getCreated(), Constants.EXP_7_NEW_PROJECT);
            if (endTime7Exp.getTime() < now.getTime()) {
                //已过期
                exped7 = true;
                surplusDay7 = 30; //TODO
            } else {
                canBuy7 = true;
            }
        }
        return new NewPeopleProjectViewModel(exped120, exped7, isBuyed120, isBuyed7, canBuy120, canBuy7, canReceiveXiaoEn, surplusDay7);
    }

    private ProjectViewModel formartProject(SheepProjectIndex projectIndex, ProjectSaleState saleState, List<SheepVendor> sheepVendorList) {
        //region 字段
        Date now = new Date();
        Date projectBeginTime = projectIndex.getBeginTime();
        String daoJiShiLeiXing = "01";
        String daoJiShiStr = "";
        String rate = StringUtil.subZeroAndDot(projectIndex.getRate().toString());
        String rateImg = "";
        //endregion

        //region 倒计时时间
        long countdown = DateUtil.subDateOfSecond(projectIndex.getBeginTime(), now);
        int day = DateUtil.subDateOfDay(now, projectBeginTime);
        if (day <= 0) {
            long hour = DateUtil.subDateOfHour(projectBeginTime, now);
            if (hour >= 2) {
                daoJiShiStr = "今天" + DateUtil.hourOfDate(projectBeginTime) + "点开标";
            } else {
                daoJiShiLeiXing = "00";
            }
        } else {
            if (day == 1) {
                daoJiShiStr = "明天" + DateUtil.hourOfDate(projectBeginTime) + "点开标";
            } else if (day == 2) {
                daoJiShiStr = "后天" + DateUtil.hourOfDate(projectBeginTime) + "点开标";
            } else {
                daoJiShiStr = DateUtil.monthOfDate(projectBeginTime) + "月" + DateUtil.dateOfDate(projectBeginTime) + "号" + DateUtil.hourOfDate(projectBeginTime) + "点开标";
            }
        }
        //endregion

        //region 新手标图标
        switch (rate) {
            case "9":
                rateImg = "https://s.emubao.com/weixin/images/rate9.png";
                break;
            case "12":
                rateImg = "https://s.emubao.com/weixin/images/rate12.png";
                break;
            case "12.5":
                rateImg = "https://s.emubao.com/weixin/images/rate125.png";
                break;
            case "13.5":
                rateImg = "https://s.emubao.com/weixin/images/rate135.png";
                break;
        }
        //endregion

        return new ProjectViewModel(
                projectIndex.getId(),
                projectIndex.getTitle(),
                projectIndex.getPrice(),
                rate,
                rateImg,
                DateUtil.formatDefault(projectIndex.getEffectiveTime()),
                DateUtil.formatDefault(projectIndex.getBeginTime()),
                projectIndex.getPeriod(),
                saleState.getName(),
                countdown,
                projectIndex.getType(),
                sheepVendorList.stream().filter(en -> en.getId() == projectIndex.getVendorId()).limit(1).findFirst().get().getShorthand(),
                daoJiShiLeiXing,
                daoJiShiStr,
                projectIndex.getPurchaseCount()
        );
    }

    //endregion

    // region 买羊订单

    /**
     * 获取买羊订单
     *
     * @param customerId 当前用户
     * @param model      订单状态
     * @return 订单列表
     * @throws Exception
     * @author 米立林
     */
    @Override
    public PageSheepOrderModel pageSheepOrder(int customerId, SheepOrderRequestModel model) throws Exception {
        if (null == model) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        PageHelper.startPage(model.getPageIndex(), Constants.PAGE_SIZE);

        List<String> states = new LinkedList<>();
        // 判断状态是否为已付款
        if (SheepOrderState.PAYMENTED.getName().equals(model.getState())) {
            states = Constants.SHEEP_IN_THE_BAR_STATE_AND_REDEMING_ANDREDEMED;
        } else {
            states.add(model.getState());
        }
        Page<SheepOrderInfo> page = sheepOrderDao.pageSheepOrderByCustomerId(customerId, states);

        int pages = page.getPages();
        List<PageSheepOrderViewModel> list = page.getResult().stream().map(
                en -> new PageSheepOrderViewModel(
                        en.getId(),
                        en.getCustomerId(),
                        en.getCode(),
                        en.getCount(),
                        Double.toString(en.getTotalAmount()),
                        en.getTitle(),
                        en.getName(),
                        en.getPhoto()
                ))
                .collect(Collectors.toList());
        PageHelper.clearPage();
        return new PageSheepOrderModel(pages, list);
    }

    /**
     * 获取已售羊只订单（包括收益金额）
     *
     * @param customerId 当前用户
     * @param model      订单状态
     * @return 订单列表
     * @throws Exception
     * @author 米立林 2017-09-30
     */
    @Override
    public PageSheepOrderEarningsModel pageSheepOrderEarnings(int customerId, SheepOrderRequestModel model) throws Exception {
        if (null == model) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        PageHelper.startPage(model.getPageIndex(), Constants.PAGE_SIZE);

        List<String> states = new LinkedList<>();
        // 判断状态是否为已付款
        if (SheepOrderState.PAYMENTED.getName().equals(model.getState())) {
            states = Constants.SHEEP_IN_THE_BAR_STATE_AND_REDEMING_ANDREDEMED;
        } else {
            states.add(model.getState());
        }
        Page<SheepOrderEarnings> page = sheepOrderDao.pageSheepOrderByCustomerIdAndState(customerId, states);

        int pages = page.getPages();
        List<PageOrderEarningsViewModel> list = page.getResult().stream().map(
                en -> new PageOrderEarningsViewModel(
                        en.getId(),
                        en.getCustomerId(),
                        en.getTitle(),
                        en.getCount(),
                        en.getPayableAmount(),
                        en.getDeductibleAmount(),
                        en.getRedPackageAmount(),
                        DoubleUtil.toFixed(en.getCount() * calcProfitEx(en.getPrice(), en.getRate(), en.getPeriod()) + en.getRedPackageAmount(), "0.00"),
                        DateUtil.format(en.getEffectiveTime(), Constants.DATE_TIME_FORMAT),
                        DateUtil.format(DateUtil.addDay(en.getRedemTime(), -1), Constants.DATE_TIME_FORMAT)
                ))
                .collect(Collectors.toList());
        PageHelper.clearPage();
        return new PageSheepOrderEarningsModel(pages, list);
    }

    /**
     * 买羊订单详情
     *
     * @param customerId 当前用户
     * @param model      SheepOrder主键
     * @return 订单列表详情
     * @throws Exception
     * @author 米立林
     */
    @Override
    public SheepOrderDetailModel sheepOrderDetail(int customerId, OnlyPrimaryIdRequestModel model) throws Exception {
        if (null == model || model.getId() <= 0) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        // 获取订单详情
        SheepOrderDetailInfo detailInfo = sheepOrderDao.getDetailByIdAndCustomerId(customerId, model.getId());
        if (null == detailInfo) {
            return new SheepOrderDetailModel();
        }

        String sheepIncome = DoubleUtil.toFixed(detailInfo.getCount() * calcProfitEx(detailInfo.getPrice(), detailInfo.getRate(), detailInfo.getPeriod()), "0.00");
        SheepOrderDetailModel detailModel = new SheepOrderDetailModel(
                detailInfo.getId(),
                detailInfo.getProjectId(),
                detailInfo.getCustomerId(),
                detailInfo.getTitle(),
                detailInfo.getCode(),
                detailInfo.getCount(),
                detailInfo.getTotalAmount(),
                detailInfo.getDeductibleAmount(),
                detailInfo.getPaymentAmount(),
                detailInfo.getState(),
                detailInfo.getRedemAmount(),
                DateUtil.format(detailInfo.getCreated(), Constants.DATE_TIME_FORMAT),
                DateUtil.format(detailInfo.getPaymentTime(), Constants.DATE_TIME_FORMAT),
                DateUtil.subDateOfDay(detailInfo.getRedeemTime(), new Date()),
                DateUtil.format(detailInfo.getRedeemTime(), Constants.DATE_TIME_FORMAT),
                sheepIncome,
                detailInfo.getRedPackageAmount(),
                DoubleUtil.toFixed(Double.valueOf(sheepIncome) + Double.valueOf(detailInfo.getRedPackageAmount()), "0.00")
        );

        return detailModel;
    }

    /**
     * @param customerId 当前用户
     * @param model      SheepProject主键
     * @return 牧场详情
     * @throws Exception
     * @author 米立林
     */
    @Override
    public SheepVendorViewModel pastureDetail(int customerId, OnlyPrimaryIdRequestModel model) throws Exception {
        if (null == model || model.getId() <= 0) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        // 获取牧场详情
        PastureDetailExtModel detailInfo = sheepProjectDao.getPastureDetailById(model.getId());
        if (null == detailInfo) {
            return new SheepVendorViewModel();
        }

        // 解析Attrs
        // 收益兑换方式 todo
        String incomeConversionMethod = "";
        // 产品简介 attr1
        String productIntroduction = "";
        // 支付方式 todo
        String payMethod = "";
        // 出栏时间（赎回时间-1天）
        Date slaughterTime = DateUtil.addDay(detailInfo.getRedemTime(), -1);
        // 执照号 attr3
        String licenseNo = "";
        // 企业地址 attr4
        String enterpriseAddress = "";
        // 营业范围 attr5
        String businessScope = "";
        // 牧场介绍 attr6
        String pastureIntroduction = "";

        // 获取牧场信息
        SheepVendorAttrs attrs = SerializeUtil.deSerialize(detailInfo.getAttrs(), SheepVendorAttrs.class);
        if (null != attrs) {
            productIntroduction = attrs.attr1;
            licenseNo = attrs.attr3;
            enterpriseAddress = attrs.attr4;
            businessScope = attrs.attr5;
            pastureIntroduction = attrs.attr6;
        }

        SheepVendorViewModel detailModel = new SheepVendorViewModel(
                detailInfo.getId(),
                detailInfo.getTitle(),
                detailInfo.getPrice(),
                detailInfo.getPeriod(),
                incomeConversionMethod,
                productIntroduction,
                payMethod,
                DateUtil.format(detailInfo.getEffectiveTime(), Constants.DATE_TIME_FORMAT),
                DateUtil.format(slaughterTime, Constants.DATE_TIME_FORMAT),
                DateUtil.format(detailInfo.getRedemTime(), Constants.DATE_TIME_FORMAT),
                detailInfo.getName(),
                licenseNo,
                enterpriseAddress,
                businessScope,
                pastureIntroduction,
                detailInfo.getPics()
        );

        return detailModel;
    }
    // endregion

    /**
     * @param customerId 当前用户
     * @param model      SheepProjectPlan主键
     * @return 获取养标计划
     * @throws Exception
     * @author 米立林
     */
    @Override
    public SheepProjectPlanViewModel sheepProjectPlan(int customerId, OnlyPrimaryIdRequestModel model) throws Exception {
        if (null == model || model.getId() <= 0) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        // 获取订单详情
        SheepProjectPlan plan = sheepProjectPlanDao.getSheepProjectPlanById(model.getId());
        if (null == plan) {
            return new SheepProjectPlanViewModel();
        }

        SheepProjectPlanViewModel detailModel = new SheepProjectPlanViewModel(
                plan.getId(),
                plan.getTime(),
                plan.getInfo(),
                DateUtil.format(plan.getCreateTime(), Constants.DATE_TIME_FORMAT)
        );

        return detailModel;
    }

    /**
     * @param customerId 当前用户
     * @param model      projectId
     * @return 养殖流程
     * @throws Exception
     * @author 米立林 2017-09-27
     */
    @Override
    public PageSheepStageModel sheepStage(int customerId, OnlyPrimaryIdRequestModel model) throws Exception {
        if (null == model || model.getId() <= 0) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        SheepProject sheepProject = sheepProjectDao.getSheepProjectById(model.getId());
        if (null == sheepProject) {
            throw new ApiException(ResultStatus.PARAMETER_ERROR);
        }

        // 判断类型 羊只1 Or 店铺2
        int type = SheepStageType.SHEEP.getName();
        if (sheepProject.getType().equals(ProjectType.NORMAL.getName()) || sheepProject.getType().equals(ProjectType.NEW_PEOPLE_120.getName())) {
            type = SheepStageType.SHEEP.getName();
        } else if (sheepProject.getType().equals(ProjectType.SLAUGHTER.getName()) || sheepProject.getType().equals(ProjectType.NEW_PEOPLE_7.getName())) {
            type = SheepStageType.SHOP.getName();
        }
        // 根据周期获取养殖流程
        List<SheepStageMongo> stages = sheepStageMongoDao.getListByPeriod(sheepProject.getPeriod(), type);

        // 已养殖天数
        int curStageDay = DateUtil.subDateOfDay(new Date(), sheepProject.getEffectiveTime());
        int pages = stages.size();

        // 是否没找到当前养殖进度
        boolean isNotFind = true;
        List<SheepStageViewModel> list = new ArrayList<>();
        for (int i = 0; i < pages; i++) {
            SheepStageViewModel viewModel = new SheepStageViewModel();
            SheepStageMongo tmp = stages.get(i);
            viewModel.setName(tmp.getName());
            viewModel.setPeriod(tmp.getPeriod());
            if (isNotFind) {
                if (i == pages - 1) {
                    // 如果是最后一个，则直接选中
                    isNotFind = false;
                    viewModel.setIcon(tmp.getSelectIcon());
                    viewModel.setIsSelect(1);
                } else {
                    // 计算养殖进度
                    if (curStageDay > tmp.getDay()) {
                        // 大于养殖天数时，选择选中图标
                        viewModel.setIcon(tmp.getSelectIcon());
                        SheepStageMongo tempStage = stages.get(i + 1);
                        if (curStageDay < tempStage.getDay()) {
                            // 大于并小于下一进度，选中
                            isNotFind = false;
                            viewModel.setIsSelect(1);
                        } else {
                            viewModel.setIsSelect(0);
                        }
                    } else {
                        viewModel.setIcon(tmp.getIcon());
                        viewModel.setIsSelect(0);
                    }
                }
            } else {
                viewModel.setIcon(tmp.getIcon());
                viewModel.setIsSelect(0);
            }
            list.add(viewModel);
        }

        return new PageSheepStageModel(pages, list);
    }

    /**
     * 我的羊圈
     * * @param customerId 当前用户id
     *
     * @return
     * @throws Exception
     * @author 米立林
     */
    @Override
    public MySheepfoldModel mySheepfold(int customerId, PageIndexRequestModel requestModel) throws Exception {
        if (customerId <= 0) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }

        MySheepfoldModel mySheepfoldModel = new MySheepfoldModel();
        List<SheepOrderInfoViewModel> list = new ArrayList<>();

        // 1、获取在栏中羊只订单
        PageHelper.startPage(requestModel.getPageIndex(), Constants.PAGE_SIZE);
        Page<SheepOrderInfo> sheepOrderInfos = sheepOrderDao.pageSheepOrderByCustomerIdGroupByProjectId(customerId, Constants.SHEEP_IN_THE_BAR_STATE);

        int count = 0;
        for (SheepOrderInfo info : sheepOrderInfos) {
            // 累计羊只数
            count += info.getCount();
            // 当前养殖状态
            CurrentSheepProjectState projectState = calcCurrentSheepProjectState(info);
            SheepOrderInfoViewModel orderInfo = new SheepOrderInfoViewModel(
                    info.getTitle(),
                    DateUtil.format(info.getEffectiveTime(), Constants.DATE_TIME_FORMAT),
                    DateUtil.format(DateUtil.addDay(info.getRedemTime(), -1), Constants.DATE_TIME_FORMAT),
                    info.getCount(),
                    DateUtil.subDateOfDay(info.getRedemTime(), new Date()),
                    info.getShorthand(),
                    projectState.getDayType(),
                    projectState.getDayTypeInt()
            );
            list.add(orderInfo);
        }
        PageHelper.clearPage();

        mySheepfoldModel.setSheepOrderInfoList(list);
        mySheepfoldModel.setSheepCount(count);
        if (count == 0) {
            mySheepfoldModel.setLevel(0);
            mySheepfoldModel.setLevelName("游民");
        } else {
            List<SheepLevel> sheepLevels = redisCache.getCustomerLevel();
            int finalCount = count;
            SheepLevel level = sheepLevels.stream().filter(en -> en.getSheepCount() >= finalCount).findFirst().get();
            // 获取等级信息
            mySheepfoldModel.setLevel(level.getLevel());
            mySheepfoldModel.setLevelName(level.getRemark());
        }

        return mySheepfoldModel;
    }

    /**
     * 牧场监控
     *
     * @param customerId 当前用户id
     * @param model      请求参数
     * @return 牧场监控
     * @throws Exception
     * @author 米立林 2017-10-17
     */
    @Override
    public PastureMonitorModel pastureMonitor(int customerId, SystemMonitorRequestModel model) throws Exception {
        if (customerId <= 0 || model.getId() <= 0) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        SheepProject sheepProject = sheepProjectDao.getSheepProjectById(model.getId());
        List<SystemMonitor> monitors = redisCache.getSystemMonitor();
        if (null == sheepProject || null == monitors) {
            throw new ApiException("未查到该记录");
        }
        List<PastureSheepErBiaoModel> sheepErBiaoModels = new LinkedList<>();
        // 羊耳标
        List<SheepOrderInfo> sheepOrderInfos = sheepOrderDao.getOrderByCustomerIdAndProjectIdAndState(customerId, model.getId(), Constants.SHEEP_IN_THE_BAR_STATE_AND_REDEMING_ANDREDEMED);
        for (int i = 0; i < sheepOrderInfos.size(); i++) {
            SheepOrderInfo order = sheepOrderInfos.get(i);
            // 通过sheepOrderId获取photos
            List<SheepPhoto> sheepPhotos = sheepPhotoDao.getPhotoByOrderId(order.getId());
            IntSummaryStatistics stats = sheepPhotos.stream().mapToInt((x) -> x.getId()).summaryStatistics();
            sheepErBiaoModels.add(new PastureSheepErBiaoModel(
                    order.getCode(),
                    stats.getMin(),
                    stats.getMax(),
                    stats.getMin() + "-" + stats.getMax(),
                    formatPhoto(sheepPhotos.get(sheepPhotos.size() - 1).getPhoto())
            ));

        }

        // 视频监控
        String type;
        switch (sheepProject.getVendorId()) {
            case 21:
                type = "00";
                break;
            case 22:
                type = "02";
                break;
            case 24:
                type = "01";
                break;
            case 25:
                type = "03";
                break;
            case 28:
                type = "04";
                break;
            default:
                type = "00";
                break;
        }
        String finalType = type;
        List<SystemMonitor> currentMonitors = monitors.stream().filter(en -> en.getType().equals(finalType)).collect(Collectors.toList());
        if (null == currentMonitors || currentMonitors.size() <= 0) {
            // 设备已离线
            throw new ApiException(ResultStatus.DEVICE_OFFLINE);
        }
        SystemMonitor monitor = currentMonitors.get(MathUtil.random(0, currentMonitors.size() - 1));
        String videoUrl = ((model.getPlatform() == Platform.ANDROID || model.getPlatform() == Platform.IOS) ? "http:" : "") + "//www.iermu.com/svideo/" + monitor.getShareId() + "/" + monitor.getUKey();

        return new PastureMonitorModel(videoUrl, sheepErBiaoModels);
    }

    /**
     * 我的羊圈 -- 可赎回
     *
     * @param customerId 当前用户id
     * @param model      SheepProject主键
     * @return 可赎回订单信息
     * @author 米立林 2017-10-23
     */
    @Override
    public SheepRedeemableViewModel sheepRedeemableProperty(int customerId, OnlyPrimaryIdRequestModel model) throws Exception {
        if (null == model || model.getId() <= 0) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        Customer customer = customerDao.getCustomerById(customerId);
        // 可赎回订单详情
        SheepOrderInfo detailInfo = sheepProjectDao.getSheepProjectByIdAndCustomerId(model.getId(), customerId, Constants.SHEEP_IN_THE_BAR_STATE_AND_REDEMING_ANDREDEMED);
        if (null == customer || null == detailInfo) {
            return new SheepRedeemableViewModel();
        }
        boolean hasRedeemPassword = true;
        if (StringUtil.isNullOrEmpty(customer.getRedeemPassword())) {
            hasRedeemPassword = false;
        }
        AutoRedeemModel autoRedeemModel = new AutoRedeemModel(
                hasRedeemPassword,
                customer.getIsAutoRedeem()
        );
        String projectType = detailInfo.getType() == ProjectType.SLAUGHTER.getName() || detailInfo.getType() == ProjectType.NEW_PEOPLE_7.getName() ? ProjectType.SLAUGHTER.getName() : ProjectType.NORMAL.getName();

        String sheepIncome = DoubleUtil.toFixed(detailInfo.getCount() * calcProfitEx(detailInfo.getPrice(), detailInfo.getRate(), detailInfo.getPeriod()), "0.00");
        SheepRedeemableViewModel detailModel = new SheepRedeemableViewModel(
                detailInfo.getId(),
                detailInfo.getCount(),
                sheepIncome,
                DoubleUtil.toFixed(detailInfo.getRedPackageAmount(), "0.00"),
                DateUtil.format(detailInfo.getRedemTime(), Constants.DATE_FORMAT),
                detailInfo.getTitle(),
                projectType,
                autoRedeemModel
        );

        return detailModel;
    }

    /**
     * 我的羊圈 -- 羊标订单列表
     *
     * @param customerId 当前用户id
     * @param model      SheepProject主键
     * @return
     * @throws Exception
     * @author 米立林 2017-10-20
     */
    @Override
    public SheepProjectOrdersModel mySheepfoldSheepProjectOrders(int customerId, OnlyPrimaryIdRequestModel model) throws Exception {
        if (null == model || model.getId() <= 0) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        SheepProjectOrdersModel sheepProjectOrdersModel = new SheepProjectOrdersModel();

        // 获取订单详情
        List<SheepOrderInfo> sheepOrderList = sheepOrderDao.getOrderByCustomerIdAndProjectIdAndState(customerId, model.getId(), Constants.SHEEP_IN_THE_BAR_STATE_AND_REDEMING_ANDREDEMED);
        if (null == sheepOrderList) {
            return sheepProjectOrdersModel;
        }

        List<SheepProjectOrdersViewModel> list = sheepOrderList.stream().map(
                en -> new SheepProjectOrdersViewModel(
                        en.getId(),
                        en.getCode(),
                        en.getCount()
                ))
                .collect(Collectors.toList());

        // 羊只总数
        IntSummaryStatistics stats = sheepOrderList.stream().mapToInt((x) -> x.getCount()).summaryStatistics();
        sheepProjectOrdersModel.setSheepTotalCount((int) stats.getSum());
        sheepProjectOrdersModel.setProjectTitle(sheepOrderList.get(0).getTitle());
        sheepProjectOrdersModel.setList(list);
        return sheepProjectOrdersModel;
    }

    /**
     * @param customerId 当前用户id
     * @param model      SheepProject主键
     * @return 我的羊圈 -- 羊只有保险
     * @throws Exception
     * @author 米立林 2017-10-20
     */
    @Override
    public SheepProjectInsurance mySheepfoldProjectInsurance(int customerId, OnlyPrimaryIdRequestModel model) throws Exception {
        if (null == model) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        SheepProject sheepProject = sheepProjectDao.getSheepProjectById(model.getId());
        if (null == sheepProject) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        SheepProjectInsurance sheepProjectInsurance = new SheepProjectInsurance();
        List<SheepVendor> sheepVendorList = redisCache.vendorList();
        SheepVendor sheepVendor = sheepVendorList.stream().filter(en -> en.getId() == sheepProject.getVendorId()).findFirst().get();
        if (null == sheepVendor) {
            throw new ApiException(ResultStatus.FAIL);
        }
        sheepProjectInsurance.setVendorId(sheepVendor.getId());
        sheepProjectInsurance.setInsuranceCompanyUrl(sheepVendor.getInsuranceCompanyUrl());
        sheepProjectInsurance.setVendorName(sheepVendor.getName());
        sheepProjectInsurance.setInsuranceCompany(sheepVendor.getInsuranceCompany());

        return sheepProjectInsurance;
    }

    /**
     * @param model
     * @return ProjectPlanModel
     * @throws Exception
     * @author xy
     */
    @Override
    public ProjectPlanModel projectPlan(ProjectPlanRequestModel model) throws Exception {

        if (model == null) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }

        SheepProjectPlan sheepProjectPlan = this.sheepProjectPlanDao.lastSheepProjectPlan(model.getId());
        if (sheepProjectPlan == null) {
            throw new ApiException(ResultStatus.DATA_QUERY_FAILED);
        }
        return new ProjectPlanModel(sheepProjectPlan.getTime(), sheepProjectPlan.getInfo());
    }


    /**
     * 我的羊圈
     *
     * @param customerId
     * @param model      pageIndex 页码  projectType 选择类型  默认 ""
     * @return MySheepFoldListViewModel
     * @throws Exception
     * @author xy
     */
    @Override
    public MySheepRoomViewModel mySheepRoom(int customerId, MySheepRoomRequestModel model) throws Exception {
        if (customerId <= 0) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        if (model == null) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        if (model.getPageIndex() <= 0) {
            model.setPageIndex(1);
        }
        if (model.getProjectType() == null || model.getProjectType().equals("")) {
            model.setProjectType("");
        }
        MySheepRoomViewModel viewModel = new MySheepRoomViewModel();
        List<MySheepFoldViewModel> mySheepFoldViewModelList = new ArrayList<>();

        PageHelper.startPage(model.getPageIndex(), Constants.PAGE_SIZE);

        List<MySheepFoldItem> mySheepFoldItems = sheepOrderDao.mySheepFoldList(customerId, Constants.SHEEP_IN_THE_BAR_STATE, model.getProjectType());

        PageInfo<MySheepFoldItem> info = new PageInfo<>(mySheepFoldItems);
        int totalPage = new Long(info.getTotal()).intValue();
        PageHelper.clearPage();

        //改入Redis
        List<SheepVendor> sheepVendors = sheepVendorDao.getSheepVendorList();
        Map<Integer, String> sheepVendorMap = new HashMap<>(0);
        if (sheepVendors != null) {
            for (SheepVendor item : sheepVendors) {
                sheepVendorMap.put(item.getId(), item.getShorthand());
            }
        }
        for (MySheepFoldItem item : mySheepFoldItems) {

            // 当前养殖状态
            CurrentSheepProjectState projectState = calcCurrentSheepProjectState(item);
            MySheepFoldViewModel mySheepFoldViewModel = new MySheepFoldViewModel();
            mySheepFoldViewModel.setProjectId(item.getId());
            mySheepFoldViewModel.setDayType(projectState.getDayType());
            mySheepFoldViewModel.setDayTypeInt(projectState.getDayTypeInt());
            mySheepFoldViewModel.setTitle(item.getTitle());
            mySheepFoldViewModel.setCount(item.getOrderSheepCount());
            mySheepFoldViewModel.setVenderId(item.getVenderId());
            mySheepFoldViewModel.setType(item.getType());
            mySheepFoldViewModel.setLeftDays(DateUtil.subDateOfDay(item.getRedemTime(), new Date()));
            mySheepFoldViewModel.setRedemTime(DateUtil.format(item.getRedemTime(), "yyyy.MM.dd"));
            mySheepFoldViewModel.setEffectiveTime(DateUtil.format(item.getEffectiveTime(), "yyyy.MM.dd"));
            mySheepFoldViewModel.setBeginTime(DateUtil.format(item.getBeginTime(), "yyyy.MM.dd"));
            mySheepFoldViewModel.setPeriod(item.getPeriod());

            mySheepFoldViewModel.setType((ProjectType.SLAUGHTER.equals(item.getType()) || ProjectType.NEW_PEOPLE_7.equals(item.getType())) ? ProjectType.SLAUGHTER.getName() : ProjectType.NORMAL.getName());
            //改入Redis
            String sheepVendor = sheepVendorMap.get(mySheepFoldViewModel.getVenderId());
            mySheepFoldViewModel.setVendor(sheepVendor == null ? "力农羊业" : sheepVendor);

            if (ProjectType.SLAUGHTER.equals(mySheepFoldViewModel.getType())) {
                int index = mySheepFoldViewModelList.size() - 1;
                MySheepFoldViewModel last = mySheepFoldViewModelList.get(index);
                if (last != null) {
                    if (!ProjectType.SLAUGHTER.equals(last.getType())) {
                        last.setShowBottom(true);
                        mySheepFoldViewModelList.set(index, last);
                    }
                }
            }
            mySheepFoldViewModelList.add(mySheepFoldViewModel);
        }
        if (model.getPageIndex() == 1) {
            int sheepCount = sheepOrderDao.mySheepFoldListSumCount(customerId, Constants.SHEEP_IN_THE_BAR_STATE, ProjectType.NORMAL.getName());
            int shopCount = sheepOrderDao.mySheepFoldListSumCount(customerId, Constants.SHEEP_IN_THE_BAR_STATE, ProjectType.SLAUGHTER.getName());
            viewModel.setSheepCount(sheepCount);
            viewModel.setShopCount(shopCount);

            int sheepTotalCount = sheepOrderDao.mySheepFoldSheepTotalCount(customerId, Constants.SHEEP_IN_THE_BAR_STATE);
            int level = sheepLevelDao.getLevelBySheepCount(sheepTotalCount);
            boolean isNewOrders = false;
            CustomerOrderLogMongo customerOrderLogMongo = customerOrderLogMongoDao.get(new Query(Criteria.where("customerId").is(customerId)));
            if (customerOrderLogMongo != null) {
                isNewOrders = true;
                customerOrderLogMongoDao.delete(customerOrderLogMongo);
            }
            MySheepRoomHeadViewModel headModel = new MySheepRoomHeadViewModel();
            headModel.setSheepTotalCount(sheepTotalCount);
            headModel.setLevel(sheepTotalCount <= 0 ? 0 : level);
            headModel.setNewOrders(isNewOrders);

            viewModel.setHead(headModel);
        }
        viewModel.setList(mySheepFoldViewModelList);
        viewModel.setTotalPage(totalPage);
        return viewModel;
    }

    /**
     * 我的羊圈 已出售羊只 列表
     *
     * @param customerId
     * @return MySheepFoldHeadViewModel
     * @throws Exception
     * @author xy
     */
    @Override
    public MySheepRoomRedeemedViewModel mySheepRoomRedeemed(int customerId, MySheepRoomRequestModel model) throws Exception {
        if (customerId <= 0) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        if (model == null) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        if (model.getPageIndex() <= 0) {
            model.setPageIndex(1);
        }
        if (model.getProjectType() == null || model.getProjectType().equals("")) {
            model.setProjectType("");
        }
        MySheepRoomRedeemedViewModel returnModel = new MySheepRoomRedeemedViewModel();

        List<MySheepRoomProjectViewModel> list = new ArrayList<>();

        PageHelper.startPage(model.getPageIndex(), Constants.PAGE_SIZE);
        List<MySheepFoldRedeemedItem> mySheepFoldRedeemedItems = sheepOrderDao.mySheepFoldSheepRedeemedList(customerId, model.getProjectType());
        PageInfo<MySheepFoldRedeemedItem> info = new PageInfo<>(mySheepFoldRedeemedItems);
        int totalPage = new Long(info.getTotal()).intValue();
        PageHelper.clearPage();

        for (MySheepFoldRedeemedItem item : mySheepFoldRedeemedItems) {
            MySheepRoomProjectViewModel mySheepRoomRedeemedViewModel = new MySheepRoomProjectViewModel();
            mySheepRoomRedeemedViewModel.setId(item.getId());
            mySheepRoomRedeemedViewModel.setType((ProjectType.SLAUGHTER.equals(item.getType()) || ProjectType.NEW_PEOPLE_7.equals(item.getType())) ? ProjectType.SLAUGHTER.getName() : ProjectType.NORMAL.getName());
            mySheepRoomRedeemedViewModel.setCount(item.getOrderSheepCount());
            mySheepRoomRedeemedViewModel.setRedemTime(DateUtil.format(item.getRedemTime(), "yyyy.MM.dd"));
            mySheepRoomRedeemedViewModel.setEffectiveTime(DateUtil.format(item.getEffectiveTime(), "yyyy.MM.dd"));
            mySheepRoomRedeemedViewModel.setBeginTime(DateUtil.format(item.getBeginTime(), "yyyy.MM.dd"));
            mySheepRoomRedeemedViewModel.setVenderId(item.getVendorId());
            mySheepRoomRedeemedViewModel.setOrderCode(item.getOrderCode());
            mySheepRoomRedeemedViewModel.setTitle(item.getTitle());
            mySheepRoomRedeemedViewModel.setDeductibleAmount(DoubleUtil.toFixed (item.getDeductibleAmount(),"%.2f"));
            mySheepRoomRedeemedViewModel.setPaymentAmount(DoubleUtil.toFixed ( item.getPaymentAmount(),"%.2f"));
            mySheepRoomRedeemedViewModel.setRedemAmount(DoubleUtil.toFixed (item.getRedemAmount(),"%.2f"));
            mySheepRoomRedeemedViewModel.setRedPrice(DoubleUtil.toFixed (item.getRedPrice(),"%.2f"));

            list.add(mySheepRoomRedeemedViewModel);
        }
        if (model.getPageIndex() == 1) {
            int sheepTotalCount = sheepOrderDao.mySheepFoldSheepTotalCount(customerId, Constants.SHEEP_IN_THE_BAR_STATE);
            int sheepRedeemedTotalCount = sheepOrderDao.mySheepFoldSheepTotalCount(customerId, Constants.REDEMING);
            int level = sheepLevelDao.getLevelBySheepCount(sheepTotalCount);
            MySheepRoomHeadViewModel headModel = new MySheepRoomHeadViewModel();
            headModel.setSheepTotalCount(sheepRedeemedTotalCount);
            headModel.setLevel(sheepTotalCount <= 0 ? 0 : level);
            returnModel.setHead(headModel);
        }

        returnModel.setTotalPage(totalPage);
        returnModel.setList(list);
        return returnModel;
    }

    /**
     * 计算当前订单状态
     *
     * @param project
     * @return CurrentSheepProjectState
     * @author 米立林 2017-10-17
     */
    private CurrentSheepProjectState calcCurrentSheepProjectState(SheepOrderInfo project) {
        CurrentSheepProjectState projectState = new CurrentSheepProjectState();
        Date now = new Date();
        // 判断是否为商铺订单
        if (project.getType().equals(ProjectType.SLAUGHTER.getName()) || project.getType().equals(ProjectType.NEW_PEOPLE_7)) {
            if (project.getState().equals(SheepOrderState.REDEEMABLE.getName())) {
                projectState.setDayTypeInt(7);
                projectState.setDayType("可赎回");
            } else if (project.getState().equals(SheepOrderState.PAYMENTED.getName())) {
                projectState.setDayTypeInt(0);
                projectState.setDayType("采购羊只");
            } else if (project.getState().equals(SheepOrderState.HAS_INTOBAR.getName())) {
                Date outDate = DateUtil.addDay(project.getRedemTime(), -1);
                int day = DateUtil.subDateOfDay(outDate, now);
                switch (project.getPeriod()) {
                    case 30:
                        //region 30Day
                        if (28 >= day && day >= 25 && day > 0) {
                            projectState.setDayTypeInt(1);
                            projectState.setDayType("物流");
                        } else if (24 >= day && day >= 20 && day > 0) {
                            projectState.setDayTypeInt(2);
                            projectState.setDayType("运输");
                        } else if (19 >= day && day >= 15 && day > 0) {
                            projectState.setDayTypeInt(3);
                            projectState.setDayType("仓储入库");
                        } else if (14 >= day && day >= 5 && day > 0) {
                            projectState.setDayTypeInt(4);
                            projectState.setDayType("羊肉分销");
                        } else if (4 >= day && day >= 1 && day > 0) {
                            projectState.setDayTypeInt(5);
                            projectState.setDayType("批发");
                        } else if (day <= 0) {
                            projectState.setDayTypeInt(6);
                            projectState.setDayType("结算回款");
                        }
                        break;
                    //endregion
                    case 40:
                        //region 40Day
                        if (40 >= day && day >= 35 && day > 0) {
                            projectState.setDayTypeInt(0);
                            projectState.setDayType("采购羊只");
                        } else if (34 >= day && day >= 25 && day > 0) {
                            projectState.setDayTypeInt(1);
                            projectState.setDayType("物流");
                        } else if (24 >= day && day >= 20 && day > 0) {
                            projectState.setDayTypeInt(2);
                            projectState.setDayType("运输");
                        } else if (19 >= day && day >= 15 && day > 0) {
                            projectState.setDayTypeInt(3);
                            projectState.setDayType("仓储入库");
                        } else if (14 >= day && day >= 5 && day > 0) {
                            projectState.setDayTypeInt(4);
                            projectState.setDayType("羊肉分销");
                        } else if (4 >= day && day >= 1 && day > 0) {
                            projectState.setDayTypeInt(5);
                            projectState.setDayType("批发");
                        } else if (day <= 0) {
                            projectState.setDayTypeInt(6);
                            projectState.setDayType("结算回款");
                        }
                        break;
                    //endregion
                    case 50:
                        //region 50Day
                        if (50 >= day && day >= 35 && day > 0) {
                            projectState.setDayTypeInt(0);
                            projectState.setDayType("采购羊只");
                        } else if (34 >= day && day >= 25 && day > 0) {
                            projectState.setDayTypeInt(1);
                            projectState.setDayType("物流");
                        } else if (24 >= day && day >= 20 && day > 0) {
                            projectState.setDayTypeInt(2);
                            projectState.setDayType("运输");
                        } else if (19 >= day && day >= 15 && day > 0) {
                            projectState.setDayTypeInt(3);
                            projectState.setDayType("仓储入库");
                        } else if (14 >= day && day >= 5 && day > 0) {
                            projectState.setDayTypeInt(4);
                            projectState.setDayType("羊肉分销");
                        } else if (4 >= day && day >= 1 && day > 0) {
                            projectState.setDayTypeInt(5);
                            projectState.setDayType("批发");
                        } else if (day <= 0) {
                            projectState.setDayTypeInt(6);
                            projectState.setDayType("结算回款");
                        }
                        break;
                    //endregion
                    case 60:
                        //region 60Day
                        if (60 >= day && day >= 45 && day > 0) {
                            projectState.setDayTypeInt(0);
                            projectState.setDayType("采购羊只");
                        } else if (44 >= day && day >= 35 && day > 0) {
                            projectState.setDayTypeInt(1);
                            projectState.setDayType("物流");
                        } else if (34 >= day && day >= 25 && day > 0) {
                            projectState.setDayTypeInt(2);
                            projectState.setDayType("运输");
                        } else if (24 >= day && day >= 15 && day > 0) {
                            projectState.setDayTypeInt(3);
                            projectState.setDayType("仓储入库");
                        } else if (14 >= day && day >= 5 && day > 0) {
                            projectState.setDayTypeInt(4);
                            projectState.setDayType("羊肉分销");
                        } else if (4 >= day && day >= 1 && day > 0) {
                            projectState.setDayTypeInt(5);
                            projectState.setDayType("批发");
                        } else if (day <= 0) {
                            projectState.setDayTypeInt(6);
                            projectState.setDayType("结算回款");
                        }
                        break;
                    //endregion
                    case 90:
                        //region 90Day
                        if (90 >= day && day >= 70 && day > 0) {
                            projectState.setDayTypeInt(0);
                            projectState.setDayType("采购羊只");
                        } else if (69 >= day && day >= 60 && day > 0) {
                            projectState.setDayTypeInt(1);
                            projectState.setDayType("物流");
                        } else if (59 >= day && day >= 50 && day > 0) {
                            projectState.setDayTypeInt(2);
                            projectState.setDayType("运输");
                        } else if (49 >= day && day >= 30 && day > 0) {
                            projectState.setDayTypeInt(3);
                            projectState.setDayType("仓储入库");
                        } else if (29 >= day && day >= 15 && day > 0) {
                            projectState.setDayTypeInt(4);
                            projectState.setDayType("羊肉分销");
                        } else if (14 >= day && day >= 1 && day > 0) {
                            projectState.setDayTypeInt(5);
                            projectState.setDayType("批发");
                        } else if (day <= 0) {
                            projectState.setDayTypeInt(6);
                            projectState.setDayType("结算回款");
                        }
                        break;
                    //endregion
                    case 120:
                        //region 120Day
                        if (120 >= day && day >= 100 && day > 0) {
                            projectState.setDayTypeInt(0);
                            projectState.setDayType("采购羊只");
                        } else if (99 >= day && day >= 90 && day > 0) {
                            projectState.setDayTypeInt(1);
                            projectState.setDayType("物流");
                        } else if (89 >= day && day >= 80 && day > 0) {
                            projectState.setDayTypeInt(2);
                            projectState.setDayType("运输");
                        } else if (79 >= day && day >= 60 && day > 0) {
                            projectState.setDayTypeInt(3);
                            projectState.setDayType("仓储入库");
                        } else if (59 >= day && day >= 15 && day > 0) {
                            projectState.setDayTypeInt(4);
                            projectState.setDayType("羊肉分销");
                        } else if (14 >= day && day >= 1 && day > 0) {
                            projectState.setDayTypeInt(5);
                            projectState.setDayType("批发");
                        } else if (day <= 0) {
                            projectState.setDayTypeInt(6);
                            projectState.setDayType("结算回款");
                        }
                        break;
                    default:
                        projectState.setDayTypeInt(6);
                        projectState.setDayType("结算回款");
                        break;

                    //endregion
                }
            } else {
                projectState.setDayTypeInt(6);
                projectState.setDayType("结算回款");
            }
        } else {
            // 羊只订单
            //region 120Day
            if (project.getState().equals(SheepOrderState.REDEEMABLE.getName())) {
                projectState.setDayTypeInt(8);
                projectState.setDayType("可赎回");
            } else if (project.getState().equals(SheepOrderState.PAYMENTED.getName())) {
                projectState.setDayTypeInt(0);
                projectState.setDayType("待入栏");
            } else if (project.getState().equals(SheepOrderState.HAS_INTOBAR.getName())) {
                Date outDate = DateUtil.addDay(project.getRedemTime(), -1);
                int day = DateUtil.subDateOfDay(outDate, now);
                if (day >= 110 && day > 0) {
                    projectState.setDayTypeInt(1);
                    projectState.setDayType("养殖中1");
                } else if (109 >= day && day >= 90 && day > 0) {
                    projectState.setDayTypeInt(2);
                    projectState.setDayType("养殖中2");
                } else if (89 >= day && day >= 60 && day > 0) {
                    projectState.setDayTypeInt(3);
                    projectState.setDayType("养殖中3");
                } else if (59 >= day && day >= 40 && day > 0) {
                    projectState.setDayTypeInt(4);
                    projectState.setDayType("养殖中4");
                } else if (39 >= day && day >= 10 && day > 0) {
                    projectState.setDayTypeInt(5);
                    projectState.setDayType("养殖中5");
                } else if (9 >= day && day >= 1 && day > 0) {
                    projectState.setDayTypeInt(6);
                    projectState.setDayType("养殖中6");
                } else if (day <= 0) {
                    projectState.setDayTypeInt(7);
                    projectState.setDayType("已出栏");
                }
            } else {
                projectState.setDayTypeInt(7);
                projectState.setDayType("已出栏");
            }
            //endregion
        }

        return projectState;
    }

    private CurrentSheepProjectState calcCurrentSheepProjectState(MySheepFoldItem project) {
        CurrentSheepProjectState projectState = new CurrentSheepProjectState();
        Date now = new Date();
        // 判断是否为商铺订单
        if (project.getType().equals(ProjectType.SLAUGHTER.getName()) || project.getType().equals(ProjectType.NEW_PEOPLE_7)) {
            if (project.getOrderState().equals(SheepOrderState.REDEEMABLE.getName())) {
                projectState.setDayTypeInt(7);
                projectState.setDayType("可赎回");
            } else if (project.getOrderState().equals(SheepOrderState.PAYMENTED.getName())) {
                projectState.setDayTypeInt(0);
                projectState.setDayType("采购羊只");
            } else if (project.getOrderState().equals(SheepOrderState.HAS_INTOBAR.getName())) {
                Date outDate = DateUtil.addDay(project.getRedemTime(), -1);
                int day = DateUtil.subDateOfDay(outDate, now);
                switch (project.getPeriod()) {
                    case 30:
                        //region 30Day
                        if (28 >= day && day >= 25 && day > 0) {
                            projectState.setDayTypeInt(1);
                            projectState.setDayType("物流");
                        } else if (24 >= day && day >= 20 && day > 0) {
                            projectState.setDayTypeInt(2);
                            projectState.setDayType("运输");
                        } else if (19 >= day && day >= 15 && day > 0) {
                            projectState.setDayTypeInt(3);
                            projectState.setDayType("仓储入库");
                        } else if (14 >= day && day >= 5 && day > 0) {
                            projectState.setDayTypeInt(4);
                            projectState.setDayType("羊肉分销");
                        } else if (4 >= day && day >= 1 && day > 0) {
                            projectState.setDayTypeInt(5);
                            projectState.setDayType("批发");
                        } else if (day <= 0) {
                            projectState.setDayTypeInt(6);
                            projectState.setDayType("结算回款");
                        }
                        break;
                    //endregion
                    case 40:
                        //region 40Day
                        if (40 >= day && day >= 35 && day > 0) {
                            projectState.setDayTypeInt(0);
                            projectState.setDayType("采购羊只");
                        } else if (34 >= day && day >= 25 && day > 0) {
                            projectState.setDayTypeInt(1);
                            projectState.setDayType("物流");
                        } else if (24 >= day && day >= 20 && day > 0) {
                            projectState.setDayTypeInt(2);
                            projectState.setDayType("运输");
                        } else if (19 >= day && day >= 15 && day > 0) {
                            projectState.setDayTypeInt(3);
                            projectState.setDayType("仓储入库");
                        } else if (14 >= day && day >= 5 && day > 0) {
                            projectState.setDayTypeInt(4);
                            projectState.setDayType("羊肉分销");
                        } else if (4 >= day && day >= 1 && day > 0) {
                            projectState.setDayTypeInt(5);
                            projectState.setDayType("批发");
                        } else if (day <= 0) {
                            projectState.setDayTypeInt(6);
                            projectState.setDayType("结算回款");
                        }
                        break;
                    //endregion
                    case 50:
                        //region 50Day
                        if (50 >= day && day >= 35 && day > 0) {
                            projectState.setDayTypeInt(0);
                            projectState.setDayType("采购羊只");
                        } else if (34 >= day && day >= 25 && day > 0) {
                            projectState.setDayTypeInt(1);
                            projectState.setDayType("物流");
                        } else if (24 >= day && day >= 20 && day > 0) {
                            projectState.setDayTypeInt(2);
                            projectState.setDayType("运输");
                        } else if (19 >= day && day >= 15 && day > 0) {
                            projectState.setDayTypeInt(3);
                            projectState.setDayType("仓储入库");
                        } else if (14 >= day && day >= 5 && day > 0) {
                            projectState.setDayTypeInt(4);
                            projectState.setDayType("羊肉分销");
                        } else if (4 >= day && day >= 1 && day > 0) {
                            projectState.setDayTypeInt(5);
                            projectState.setDayType("批发");
                        } else if (day <= 0) {
                            projectState.setDayTypeInt(6);
                            projectState.setDayType("结算回款");
                        }
                        break;
                    //endregion
                    case 60:
                        //region 60Day
                        if (60 >= day && day >= 45 && day > 0) {
                            projectState.setDayTypeInt(0);
                            projectState.setDayType("采购羊只");
                        } else if (44 >= day && day >= 35 && day > 0) {
                            projectState.setDayTypeInt(1);
                            projectState.setDayType("物流");
                        } else if (34 >= day && day >= 25 && day > 0) {
                            projectState.setDayTypeInt(2);
                            projectState.setDayType("运输");
                        } else if (24 >= day && day >= 15 && day > 0) {
                            projectState.setDayTypeInt(3);
                            projectState.setDayType("仓储入库");
                        } else if (14 >= day && day >= 5 && day > 0) {
                            projectState.setDayTypeInt(4);
                            projectState.setDayType("羊肉分销");
                        } else if (4 >= day && day >= 1 && day > 0) {
                            projectState.setDayTypeInt(5);
                            projectState.setDayType("批发");
                        } else if (day <= 0) {
                            projectState.setDayTypeInt(6);
                            projectState.setDayType("结算回款");
                        }
                        break;
                    //endregion
                    case 90:
                        //region 90Day
                        if (90 >= day && day >= 70 && day > 0) {
                            projectState.setDayTypeInt(0);
                            projectState.setDayType("采购羊只");
                        } else if (69 >= day && day >= 60 && day > 0) {
                            projectState.setDayTypeInt(1);
                            projectState.setDayType("物流");
                        } else if (59 >= day && day >= 50 && day > 0) {
                            projectState.setDayTypeInt(2);
                            projectState.setDayType("运输");
                        } else if (49 >= day && day >= 30 && day > 0) {
                            projectState.setDayTypeInt(3);
                            projectState.setDayType("仓储入库");
                        } else if (29 >= day && day >= 15 && day > 0) {
                            projectState.setDayTypeInt(4);
                            projectState.setDayType("羊肉分销");
                        } else if (14 >= day && day >= 1 && day > 0) {
                            projectState.setDayTypeInt(5);
                            projectState.setDayType("批发");
                        } else if (day <= 0) {
                            projectState.setDayTypeInt(6);
                            projectState.setDayType("结算回款");
                        }
                        break;
                    //endregion
                    case 120:
                        //region 120Day
                        if (120 >= day && day >= 100 && day > 0) {
                            projectState.setDayTypeInt(0);
                            projectState.setDayType("采购羊只");
                        } else if (99 >= day && day >= 90 && day > 0) {
                            projectState.setDayTypeInt(1);
                            projectState.setDayType("物流");
                        } else if (89 >= day && day >= 80 && day > 0) {
                            projectState.setDayTypeInt(2);
                            projectState.setDayType("运输");
                        } else if (79 >= day && day >= 60 && day > 0) {
                            projectState.setDayTypeInt(3);
                            projectState.setDayType("仓储入库");
                        } else if (59 >= day && day >= 15 && day > 0) {
                            projectState.setDayTypeInt(4);
                            projectState.setDayType("羊肉分销");
                        } else if (14 >= day && day >= 1 && day > 0) {
                            projectState.setDayTypeInt(5);
                            projectState.setDayType("批发");
                        } else if (day <= 0) {
                            projectState.setDayTypeInt(6);
                            projectState.setDayType("结算回款");
                        }
                        break;
                    //endregion
                }
            } else {
                projectState.setDayTypeInt(6);
                projectState.setDayType("结算回款");
            }
        } else {
            // 羊只订单
            //region 120Day
            if (project.getOrderState().equals(SheepOrderState.REDEEMABLE.getName())) {
                projectState.setDayTypeInt(8);
                projectState.setDayType("可赎回");
            } else if (project.getOrderState().equals(SheepOrderState.PAYMENTED.getName())) {
                projectState.setDayTypeInt(0);
                projectState.setDayType("待入栏");
            } else if (project.getOrderState().equals(SheepOrderState.HAS_INTOBAR.getName())) {
                Date outDate = DateUtil.addDay(project.getRedemTime(), -1);
                int day = DateUtil.subDateOfDay(outDate, now);
                if (day >= 110 && day > 0) {
                    projectState.setDayTypeInt(1);
                    projectState.setDayType("养殖中1");
                } else if (109 >= day && day >= 90 && day > 0) {
                    projectState.setDayTypeInt(2);
                    projectState.setDayType("养殖中2");
                } else if (89 >= day && day >= 60 && day > 0) {
                    projectState.setDayTypeInt(3);
                    projectState.setDayType("养殖中3");
                } else if (59 >= day && day >= 40 && day > 0) {
                    projectState.setDayTypeInt(4);
                    projectState.setDayType("养殖中4");
                } else if (39 >= day && day >= 10 && day > 0) {
                    projectState.setDayTypeInt(5);
                    projectState.setDayType("养殖中5");
                } else if (9 >= day && day >= 1 && day > 0) {
                    projectState.setDayTypeInt(6);
                    projectState.setDayType("养殖中6");
                } else if (day <= 0) {
                    projectState.setDayTypeInt(7);
                    projectState.setDayType("已出栏");
                }
            } else {
                projectState.setDayTypeInt(7);
                projectState.setDayType("已出栏");
            }
            //endregion
        }

        return projectState;
    }


}
