package com.zhongmubao.api.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhongmubao.api.cache.RedisCache;
import com.zhongmubao.api.config.Constants;
import com.zhongmubao.api.config.ResultStatus;
import com.zhongmubao.api.config.enmu.ProjectType;
import com.zhongmubao.api.config.enmu.SheepOrderState;
import com.zhongmubao.api.config.enmu.SheepProjectPeriod;
import com.zhongmubao.api.dao.SheepOrderDao;
import com.zhongmubao.api.dao.SheepProjectDao;
import com.zhongmubao.api.dto.request.sheep.room.*;
import com.zhongmubao.api.dto.response.sheep.room.*;
import com.zhongmubao.api.dto.response.sheep.room.list.*;
import com.zhongmubao.api.entity.Customer;
import com.zhongmubao.api.entity.SheepLevel;
import com.zhongmubao.api.entity.SheepProject;
import com.zhongmubao.api.entity.ext.SheepOrderInfo;
import com.zhongmubao.api.exception.ApiException;
import com.zhongmubao.api.mongo.dao.CustomerOrderLogMongoDao;
import com.zhongmubao.api.mongo.dao.SheepProgressMongoDao;
import com.zhongmubao.api.mongo.dao.SheepStageMongoDao;
import com.zhongmubao.api.mongo.entity.CustomerOrderLogMongo;
import com.zhongmubao.api.mongo.entity.SheepProgressMongo;
import com.zhongmubao.api.mongo.entity.SheepStageMongo;
import com.zhongmubao.api.service.SheepRoomService;
import com.zhongmubao.api.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 我的羊圈
 *
 * @author xy
 */
@Service
public class SheepRoomServiceImpl extends BaseService implements SheepRoomService {

    private final RedisCache redisCache;
    private final SheepOrderDao sheepOrderDao;
    private final SheepProgressMongoDao sheepProgressMongoDao;
    private final CustomerOrderLogMongoDao customerOrderLogMongoDao;
    private final SheepProjectDao sheepProjectDao;
    private final SheepStageMongoDao sheepStageMongoDao;

    @Autowired
    public SheepRoomServiceImpl(RedisCache redisCache, SheepOrderDao sheepOrderDao, SheepProgressMongoDao sheepProgressMongoDao, CustomerOrderLogMongoDao customerOrderLogMongoDao, SheepProjectDao sheepProjectDao, SheepStageMongoDao sheepStageMongoDao) {
        this.redisCache = redisCache;
        this.sheepOrderDao = sheepOrderDao;
        this.sheepProgressMongoDao = sheepProgressMongoDao;
        this.customerOrderLogMongoDao = customerOrderLogMongoDao;
        this.sheepProjectDao = sheepProjectDao;
        this.sheepStageMongoDao = sheepStageMongoDao;
    }

    @Override
    public SheepRoomViewModel room(Customer customer, SheepRoomRequestModel model) throws Exception {

        if (model == null) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        if (model.getPageIndex() <= 0) {
            model.setPageIndex(1);
        }
        if (model.getProjectType() == null || model.getProjectType().equals("")) {
            model.setProjectType("");
        }
        List<SheepRoomItemModel> sheepRoomItemModels = new ArrayList<>();
        PageHelper.startPage(model.getPageIndex(), Constants.PAGE_SIZE);
        Page<SheepOrderInfo> sheepOrderInfos = sheepOrderDao.pageSheepOrderByCustomerIdGroupByProjectId(customer.getId(), Constants.SHEEP_IN_THE_BAR_STATE, model.getProjectType());
        int count = 0;
        for (SheepOrderInfo info : sheepOrderInfos) {
            // 累计羊只数
            count += info.getCount();
            // 当前养殖状态
            SheepProgressMongo projectState = calcCurrentSheepProjectState(info);
            // 剩余天数
            int surplusDay = DateUtil.subDateOfDay(info.getRedeemTime(), new Date()) - 1;
            SheepRoomItemModel orderInfo = new SheepRoomItemModel(
                    info.getProjectId(),
                    info.getVendorId(),
                    info.getTitle(),
                    info.getPhoto(),
                    info.getCount(),
                    DateUtil.format(info.getEffectiveTime(), Constants.DATE_FORMAT),
                    DateUtil.format(info.getRedeemTime(), Constants.DATE_FORMAT),
                    projectState.getName(),
                    projectState.getIcon(),
                    info.getShorthand(),
                    surplusDay
            );
            sheepRoomItemModels.add(orderInfo);
        }
        int page = sheepOrderInfos.getPages();
        PageHelper.clearPage();

        SheepRoomViewModel sheepRoomViewModel = new SheepRoomViewModel();
        List<CustomerOrderLogMongo> customerOrderLogMongos = customerOrderLogMongoDao.getList(customer.getId());
        boolean hasOrder = customerOrderLogMongos.size() > 0;
        sheepRoomViewModel.setHasOrder(hasOrder);

        int totalCount = sheepOrderDao.sumSheepOrderCountByCustomerIdAndState(customer.getId(), Constants.SHEEP_IN_THE_BAR_STATE);
        sheepRoomViewModel.setTotalSheepCount(totalCount);
        if (totalCount == 0) {
            sheepRoomViewModel.setCustomerLevel(0);
            sheepRoomViewModel.setCustomerLevelName("游民");
        } else {
            List<SheepLevel> sheepLevels = redisCache.getCustomerLevel();
            int finalCount = totalCount;
            SheepLevel level = sheepLevels.stream().filter(en -> en.getSheepCount() >= finalCount).findFirst().get();
            // 获取等级信息
            sheepRoomViewModel.setCustomerLevel(level.getLevel());
            sheepRoomViewModel.setCustomerLevelName(level.getRemark());
        }
        SheepRoomModel sheepRoomModel = new SheepRoomModel();
        sheepRoomModel.setTotalPage(page);
        sheepRoomModel.setTotalSheepCount(count);

        sheepRoomModel.setList(sheepRoomItemModels);
        sheepRoomViewModel.setSheepRoomModel(sheepRoomModel);

        return sheepRoomViewModel;
    }

    @Override
    public SheepRoomOrdersViewModel orders(Customer customer, SheepRoomOrdersRequestModel model) throws Exception {
        if (null == model || model.getProjectId() <= 0) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        SheepRoomOrdersViewModel sheepRoomOrdersViewModel = new SheepRoomOrdersViewModel();
        List<SheepOrderInfo> sheepOrderInfos = sheepOrderDao.getOrderByCustomerIdAndProjectIdAndState(customer.getId(), model.getProjectId(), Constants.SHEEP_IN_THE_BAR_STATE);
        if (null == sheepOrderInfos || sheepOrderInfos.size() == 0) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        sheepRoomOrdersViewModel.setTitle(sheepOrderInfos.get(0).getTitle());
        IntSummaryStatistics stats = sheepOrderInfos.stream().mapToInt((x) -> x.getCount()).summaryStatistics();
        sheepRoomOrdersViewModel.setTotalCount((int) stats.getSum());
        List<SheepRoomOrdersItemModel> sheepRoomOrdersItemModels = sheepOrderInfos.stream().map(
                en -> new SheepRoomOrdersItemModel(
                        en.getCode(),
                        en.getCount()
                ))
                .collect(Collectors.toList());
        sheepRoomOrdersViewModel.setList(sheepRoomOrdersItemModels);

        return sheepRoomOrdersViewModel;
    }

    @Override
    public SheepRoomBreedProgressViewModel breedProgress(SheepRoomBreedProgressRequestModel model) throws Exception {
        if (null == model || model.getProjectId() <= 0) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        SheepProject sheepProject = sheepProjectDao.getSheepProjectById(model.getProjectId());
        if (null == sheepProject) {
            throw new ApiException(ResultStatus.PARAMETER_ERROR);
        }
        // 判断类型 羊只00 Or 店铺03
        String type = null;
        if (sheepProject.getType().equals(ProjectType.NORMAL.getName()) || sheepProject.getType().equals(ProjectType.NEW_PEOPLE_120.getName())) {
            type = ProjectType.NORMAL.getName();
        } else if (sheepProject.getType().equals(ProjectType.SLAUGHTER.getName()) || sheepProject.getType().equals(ProjectType.NEW_PEOPLE_7.getName())) {
            type = ProjectType.SLAUGHTER.getName();
        }
        // 获取养殖流程
        List<SheepStageMongo> stages = sheepStageMongoDao.getListByPeriodAndType(sheepProject.getPeriod(), type);

        int curStageDay = DateUtil.subDateOfDay(new Date(), sheepProject.getEffectiveTime());
        int pages = stages.size();

        SheepRoomBreedProgressViewModel sheepRoomBreedProgressViewModel = new SheepRoomBreedProgressViewModel();

        List<SheepRoomBreedProgressItemModel> sheepRoomBreedProgressItemModels = new ArrayList<>();
        SheepRoomBreedProgressItemModel sheepRoomBreedProgressItemModel1 = new SheepRoomBreedProgressItemModel();
        sheepRoomBreedProgressItemModel1.setId(0);
        sheepRoomBreedProgressItemModel1.setText("喂水，喂青草");
        sheepRoomBreedProgressItemModel1.setProgress(1);
        sheepRoomBreedProgressItemModel1.setImg("http://192.168.31.210:10210/weixin/images/foldshui3.png?类型图片");
        sheepRoomBreedProgressItemModels.add(sheepRoomBreedProgressItemModel1);
        SheepRoomBreedProgressItemModel sheepRoomBreedProgressItemModel2 = new SheepRoomBreedProgressItemModel();
        sheepRoomBreedProgressItemModel2.setId(1);
        sheepRoomBreedProgressItemModel2.setText("分群");
        sheepRoomBreedProgressItemModel2.setProgress(2);
        sheepRoomBreedProgressItemModel2.setImg("http://192.168.31.210:10210/weixin/images/foldshui2.png?类型图片");
        sheepRoomBreedProgressItemModels.add(sheepRoomBreedProgressItemModel2);
        SheepRoomBreedProgressItemModel sheepRoomBreedProgressItemModel3 = new SheepRoomBreedProgressItemModel();
        sheepRoomBreedProgressItemModel3.setId(2);
        sheepRoomBreedProgressItemModel3.setText("出栏");
        sheepRoomBreedProgressItemModel3.setProgress(3);
        sheepRoomBreedProgressItemModel3.setImg("http://192.168.31.210:10210/weixin/images/foldshui1.png?类型图片");
        sheepRoomBreedProgressItemModels.add(sheepRoomBreedProgressItemModel3);


        sheepRoomBreedProgressViewModel.setList(sheepRoomBreedProgressItemModels);
        return sheepRoomBreedProgressViewModel;
    }

    @Override
    public SheepRoomInsuranceViewModel insurance(SheepRoomInsuranceRequestModel model) throws Exception {
        SheepRoomInsuranceViewModel sheepRoomInsuranceViewModel = new SheepRoomInsuranceViewModel();
        sheepRoomInsuranceViewModel.setImg("http://s.emubao.com/weixin/images/sntbx1.jpg?保险图片");
        sheepRoomInsuranceViewModel.setTitle("中国人保");
        return sheepRoomInsuranceViewModel;
    }

    @Override
    public SheepRoomEarTagViewModel eartag(Customer customer, SheepRoomEarTagRequestModel model) throws Exception {
        SheepRoomEarTagViewModel sheepRoomEarTagViewModel = new SheepRoomEarTagViewModel();
        sheepRoomEarTagViewModel.setVideoUrl("视频地址");
        List<SheepRoomEarTagItemModel> sheepRoomEarTagItemModels = new ArrayList<>();
        SheepRoomEarTagItemModel sheepRoomEarTagItemModel = new SheepRoomEarTagItemModel();
        sheepRoomEarTagItemModel.setOrderCode("123");
        sheepRoomEarTagItemModel.setBegin("1");
        sheepRoomEarTagItemModel.setEnd("2");
        sheepRoomEarTagItemModel.setPhoto("图片");
        sheepRoomEarTagItemModels.add(sheepRoomEarTagItemModel);
        sheepRoomEarTagViewModel.setList(sheepRoomEarTagItemModels);
        return sheepRoomEarTagViewModel;
    }

    @Override
    public SheepRoomRedeemableViewModel redeemable(Customer customer, SheepRoomRedeemableRequestModel model) throws Exception {
        return null;
    }


    /**
     * 计算当前羊标进度
     *
     * @param project
     * @return CurrentSheepProjectState
     * @author 米立林 2017-10-17
     */
    private SheepProgressMongo calcCurrentSheepProjectState(SheepOrderInfo project) throws Exception {
        SheepProgressMongo sheepProgress = new SheepProgressMongo();
        List<SheepProgressMongo> sheepProgressMongoList;
        // 判断是否为商铺订单
        if (project.getType().equals(ProjectType.SLAUGHTER.getName()) || project.getType().equals(ProjectType.NEW_PEOPLE_7)) {
            // 店铺进度
            sheepProgressMongoList = sheepProgressMongoDao.getList(ProjectType.SLAUGHTER.getName(), project.getPeriod());
        } else {
            // 羊只进度
            int period = project.getPeriod();
            if (period == SheepProjectPeriod.PERIOD_240.getName()) {
                period = SheepProjectPeriod.PERIOD_120.getName();
            }
            sheepProgressMongoList = sheepProgressMongoDao.getList(ProjectType.NORMAL.getName(), period);
        }
        Date now = new Date();
        int day = DateUtil.subDateOfDay(now, project.getEffectiveTime());
        // 优先判断SheepProject状态（可赎回和已付款）
        if (project.getSheepOrderState().equals(SheepOrderState.REDEEMABLE.getName())) {
            sheepProgress = sheepProgressMongoList.get(sheepProgressMongoList.size() - 1);
        } else if (project.getSheepOrderState().equals(SheepOrderState.PAYMENTED.getName())) {
            sheepProgress = sheepProgressMongoList.get(0);
        } else if (project.getSheepOrderState().equals(SheepOrderState.HAS_INTOBAR.getName())) {
            // 240天标，再走一次120天标
            if (project.getPeriod() == SheepProjectPeriod.PERIOD_240.getName() && day >= SheepProjectPeriod.PERIOD_120.getName()) {
                day -= 120;
            }

            for (int i = 0; i < sheepProgressMongoList.size(); i++) {
                if (i == sheepProgressMongoList.size() - 1) {
                    sheepProgress = sheepProgressMongoList.get(sheepProgressMongoList.size() - 1);
                    break;
                }
                if (day >= SheepProjectPeriod.PERIOD_120.getName()) {
                    sheepProgress = sheepProgressMongoList.get(sheepProgressMongoList.size() - 2);
                    break;
                }
                SheepProgressMongo progress = sheepProgressMongoList.get(i);
                SheepProgressMongo nextProgress = sheepProgressMongoList.get(i + 1);
                if (day >= progress.getDay() && day < nextProgress.getDay()) {
                    sheepProgress = progress;
                    break;
                }
            }
        } else {
            // 默认
            sheepProgress = sheepProgressMongoList.get(sheepProgressMongoList.size() - 2);
        }

        return sheepProgress;
    }
}
