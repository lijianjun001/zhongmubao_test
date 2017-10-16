package com.zhongmubao.api.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhongmubao.api.cache.RedisCache;
import com.zhongmubao.api.config.Constants;
import com.zhongmubao.api.config.ResultStatus;
import com.zhongmubao.api.config.enmu.ProjectSaleState;
import com.zhongmubao.api.config.enmu.ProjectType;
import com.zhongmubao.api.config.enmu.SheepOrderState;
import com.zhongmubao.api.dao.CustomerSinaDao;
import com.zhongmubao.api.dao.ExtActivityRecordDao;
import com.zhongmubao.api.dao.SheepOrderDao;
import com.zhongmubao.api.dao.SheepProjectDao;
import com.zhongmubao.api.dto.Request.OnlyPrimaryIdRequestModel;
import com.zhongmubao.api.dto.Request.Sheep.SheepOrderRequestModel;
import com.zhongmubao.api.dto.Response.Index.*;
import com.zhongmubao.api.dto.Response.Sheep.PageSheepOrderModel;
import com.zhongmubao.api.dto.Response.Sheep.PageSheepOrderViewModel;
import com.zhongmubao.api.dto.Response.Sheep.SheepOrderDetailModel;
import com.zhongmubao.api.dto.Response.Sheep.SheepVendorViewModel;
import com.zhongmubao.api.entity.Customer;
import com.zhongmubao.api.entity.CustomerSina;
import com.zhongmubao.api.entity.SheepProjectIndex;
import com.zhongmubao.api.entity.SheepVendor;
import com.zhongmubao.api.entity.ext.PastureDetailExtModel;
import com.zhongmubao.api.entity.ext.SheepOrderCountAndMinCreated;
import com.zhongmubao.api.entity.ext.SheepOrderDetailInfo;
import com.zhongmubao.api.entity.ext.SheepOrderInfo;
import com.zhongmubao.api.exception.ApiException;
import com.zhongmubao.api.mongo.dao.ExtBannerMongoDao;
import com.zhongmubao.api.mongo.entity.base.PageModel;
import com.zhongmubao.api.service.SheepService;
import com.zhongmubao.api.util.ApiUtil;
import com.zhongmubao.api.util.DateUtil;
import com.zhongmubao.api.util.SerializeUtil;
import com.zhongmubao.api.util.StringUtil;
import com.zhongmubao.api.util.common.SheepVendorAttrs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SheepServiceImpl implements SheepService {

    //region 注入
    private final ExtBannerMongoDao extBannerMongoDao;
    private final SheepProjectDao sheepProjectDao;
    private final SheepOrderDao sheepOrderDao;
    private final ExtActivityRecordDao extActivityRecordDao;
    private final CustomerSinaDao customerSinaDao;
    private final RedisCache redisCache;

    @Autowired
    public SheepServiceImpl(RedisCache redisCache, CustomerSinaDao customerSinaDao, ExtActivityRecordDao extActivityRecordDao, SheepOrderDao sheepOrderDao, SheepProjectDao sheepProjectDao, ExtBannerMongoDao extBannerMongoDao) {
        this.redisCache = redisCache;
        this.customerSinaDao = customerSinaDao;
        this.extActivityRecordDao = extActivityRecordDao;
        this.sheepOrderDao = sheepOrderDao;
        this.sheepProjectDao = sheepProjectDao;
        this.extBannerMongoDao = extBannerMongoDao;
    }


    //endregion
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
        List<BannerViewModel> bannerList = extBannerMongoDao.Pager(new PageModel<>()).getDatas().stream()
                .map(en -> new BannerViewModel("", en.getTitle(), en.getLink(), ApiUtil.formartImg(en.getImgUrl())))
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

        projectIndexList = list.stream().filter(en -> !en.getType().equals(ProjectType.NEW_PEOPLE_120) && !en.getType().equals(ProjectType.NEW_PEOPLE_7)).collect(Collectors.toList());
        newPeopleProjectIndexList = list.stream().filter(en -> en.getType().equals(ProjectType.NEW_PEOPLE_120) || en.getType().equals(ProjectType.NEW_PEOPLE_7)).collect(Collectors.toList());

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
                    if (null == extActivityRecordDao.getExtActivityRecordrByCustomerIdAndActivityId(customerId, Constants.Activity_ID_XIN_SHOU)) {
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

        List<String> states = new LinkedList<String>();
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
     * 买羊订单详情
     *
     * @param customerId 当前用户
     * @param model      订单主键
     * @return 订单列表详情
     * @throws Exception
     * @author 米立林
     */
    @Override
    public SheepOrderDetailModel GetDetailByIdAndCustomerId(int customerId, OnlyPrimaryIdRequestModel model) throws Exception {
        if (null == model || model.getId() <= 0) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        // 获取订单详情
        SheepOrderDetailInfo detailInfo = sheepOrderDao.GetDetailByIdAndCustomerId(customerId, model.getId());
        if (null == detailInfo) {
            return new SheepOrderDetailModel();
        }

        SheepOrderDetailModel detailModel = new SheepOrderDetailModel(
                detailInfo.getId(),
                detailInfo.getProjectId(),
                detailInfo.getCustomerId(),
                detailInfo.getCode(),
                detailInfo.getCount(),
                detailInfo.getTotalAmount(),
                detailInfo.getDeductibleAmount(),
                detailInfo.getPaymentAmount(),
                detailInfo.getState(),
                detailInfo.getRedemAmount(),
                DateUtil.formart(detailInfo.getCreated(), "yyyy-MM-dd HH:mm:ss"),
                DateUtil.formart(detailInfo.getPaymentTime(), "yyyy-MM-dd HH:mm:ss"),
                DateUtil.subDateOfDay(detailInfo.getRedeemTime(), new Date()),
                DateUtil.formart(detailInfo.getRedeemTime(), "yyyy-MM-dd HH:mm:ss"),
                detailInfo.getRedPackageAmount(),
                "0"   // todo:totalIncome 收益总额
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
    public SheepVendorViewModel GetPastureDetailById(int customerId, OnlyPrimaryIdRequestModel model) throws Exception {
        if (null == model || model.getId() <= 0) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        // 获取牧场详情
        PastureDetailExtModel detailInfo = sheepProjectDao.GetPastureDetailById(model.getId());
        if (null == detailInfo) {
            return new SheepVendorViewModel();
        }

        // 解析Attrs
        // 收益兑换方式 todo
        String incomeConversionMethod = "";
        // 产品简介 Attr1
        String productIntroduction = "";
        // 支付方式 todo
        String payMethod = "";
        // 出栏时间（赎回时间-1天）
        Date slaughterTime = DateUtil.addDay(detailInfo.getRedemTime(), -1);
        // 执照号 Attr3
        String licenseNo = "";
        // 企业地址 Attr4
        String enterpriseAddress = "";
        // 营业范围 Attr5
        String businessScope = "";
        // 牧场介绍 Attr6
        String pastureIntroduction = "";

        // 获取牧场信息
        SheepVendorAttrs attrs = SerializeUtil.deSerialize(detailInfo.getAttrs(), SheepVendorAttrs.class);
        if (null != attrs) {
            productIntroduction = attrs.Attr1;
            licenseNo = attrs.Attr3;
            enterpriseAddress = attrs.Attr4;
            businessScope = attrs.Attr5;
            pastureIntroduction = attrs.Attr6;
        }

        SheepVendorViewModel detailModel = new SheepVendorViewModel(
                detailInfo.getId(),
                detailInfo.getTitle(),
                detailInfo.getPrice(),
                detailInfo.getPeriod(),
                incomeConversionMethod,
                productIntroduction,
                payMethod,
                DateUtil.formart(detailInfo.getEffectiveTime(), "yyyy-MM-dd HH:mm:ss"),
                DateUtil.formart(slaughterTime, "yyyy-MM-dd HH:mm:ss"),
                DateUtil.formart(detailInfo.getRedemTime(), "yyyy-MM-dd HH:mm:ss"),
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
                DateUtil.formartDefault(projectIndex.getEffectiveTime()),
                DateUtil.formartDefault(projectIndex.getBeginTime()),
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

}
