package com.zhongmubao.api.service.impl.my;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhongmubao.api.config.Constants;
import com.zhongmubao.api.config.ResultStatus;
import com.zhongmubao.api.config.enmu.RedPackageGroupType;
import com.zhongmubao.api.config.enmu.RedPackageSortType;
import com.zhongmubao.api.config.enmu.RedPackageState;
import com.zhongmubao.api.dao.ExtRedPackageDao;
import com.zhongmubao.api.dto.request.my.redpackage.DetailRequestModel;
import com.zhongmubao.api.dto.request.my.redpackage.GroupRequestModel;
import com.zhongmubao.api.dto.request.my.redpackage.HistoryRequestModel;
import com.zhongmubao.api.dto.request.my.redpackage.ListRequestModel;
import com.zhongmubao.api.dto.response.my.redpackage.RedPackageModel;
import com.zhongmubao.api.dto.response.my.redpackage.*;
import com.zhongmubao.api.entity.Customer;
import com.zhongmubao.api.entity.ExtRedPackage;
import com.zhongmubao.api.entity.ext.ExtRedPackageGroup;
import com.zhongmubao.api.exception.ApiException;
import com.zhongmubao.api.service.my.ReadPackageService;
import com.zhongmubao.api.util.DateUtil;
import com.zhongmubao.api.util.DoubleUtil;
import com.zhongmubao.api.util.RegExpMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 我的红包
 *
 * @author 孙阿龙
 */
@Service
public class ReadPackageServiceImpl implements ReadPackageService {

    private final ExtRedPackageDao extRedPackageDao;

    @Autowired
    public ReadPackageServiceImpl(ExtRedPackageDao extRedPackageDao) {
        this.extRedPackageDao = extRedPackageDao;
    }

    @Override
    public GroupViewModel readPackageGroup(Customer customer, GroupRequestModel model) throws Exception {
        if (model == null) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        // 分组只按金额排序
        model.setSortType(RedPackageSortType.PRICE);

        GroupViewModel viewModel = new GroupViewModel();
        ArrayList<GroupModel> groupModelList = new ArrayList<>();
        List<ExtRedPackageGroup> list = extRedPackageDao.getByCustomerIdGroupByPrice(customer.getId(), model.getSortType().getName());
        boolean isPreLoad = false;
        // 8元红包
        List<ExtRedPackageGroup> eightGroup = list.stream().filter(en -> en.getPrice() == 8).collect(Collectors.toList());
        if (eightGroup != null && eightGroup.size() > 0) {
            GroupModel redPacket = redPacketGroupCalc(customer, eightGroup, RedPackageGroupType.EIGHT, isPreLoad, model.getSortType());
//            if (redPacket.getPreLoadList().size() > 1) {
//                isPreLoad = false;
//            }
            groupModelList.add(redPacket);
            list.removeAll(eightGroup);
        }
        // 5元红包
        List<ExtRedPackageGroup> fiveGroup = list.stream().filter(en -> en.getPrice() == 5).collect(Collectors.toList());
        if (fiveGroup != null && fiveGroup.size() > 0) {
            GroupModel redPacket = redPacketGroupCalc(customer, fiveGroup, RedPackageGroupType.FIVE, isPreLoad, model.getSortType());
//            if (isPreLoad && redPacket.getPreLoadList() != null && redPacket.getPreLoadList().size() > 1) {
//                isPreLoad = false;
//            }
            groupModelList.add(redPacket);
            list.removeAll(fiveGroup);
        }
        // 2元红包
        List<ExtRedPackageGroup> twoGroup = list.stream().filter(en -> en.getPrice() == 2).collect(Collectors.toList());
        if (twoGroup != null && twoGroup.size() > 0) {
            GroupModel redPacket = redPacketGroupCalc(customer, twoGroup, RedPackageGroupType.TWO, isPreLoad, model.getSortType());
//            if (isPreLoad && redPacket.getPreLoadList() != null && redPacket.getPreLoadList().size() > 1) {
//                isPreLoad = false;
//            }
            groupModelList.add(redPacket);
            list.removeAll(twoGroup);
        }
        // 零钱红包
        if (list.size() > 0) {
            GroupModel looseRadPacket = redPacketGroupCalc(customer, list, RedPackageGroupType.OTHER, isPreLoad, model.getSortType());
            groupModelList.add(looseRadPacket);
        }

        viewModel.setList(groupModelList);
        return viewModel;
    }

    /**
     * 红包分组计算
     *
     * @param groupRedPacket 分组列表
     * @param groupType      红包分组类型
     * @param isPreLoad      是否预加载
     * @return GroupModel
     */
    private GroupModel redPacketGroupCalc(Customer customer, List<ExtRedPackageGroup> groupRedPacket, RedPackageGroupType groupType, boolean isPreLoad, RedPackageSortType sotType) {

        String remark = "仅可购买120天及以上长期羊使用";
        GroupModel groupModel = new GroupModel();
        groupModel.setGroupType(groupType.getName());
        if (groupType == RedPackageGroupType.OTHER) {
            IntSummaryStatistics statsTotalCount = groupRedPacket.stream().mapToInt(ExtRedPackageGroup::getTotalCount).summaryStatistics();
            groupModel.setCount(Integer.parseInt(String.valueOf(statsTotalCount.getSum())));

            DoubleSummaryStatistics statsTotalPrice = groupRedPacket.stream().mapToDouble(ExtRedPackageGroup::getTotalPrice).summaryStatistics();
            groupModel.setTotalPrice(RegExpMatcher.matcherPrice(DoubleUtil.toFixed(statsTotalPrice.getSum(), Constants.PRICE_FORMAT)));

            IntSummaryStatistics statsTotalNewCount = groupRedPacket.stream().mapToInt(ExtRedPackageGroup::getNewCount).summaryStatistics();
            groupModel.setNewCount(Integer.parseInt(String.valueOf(statsTotalNewCount.getSum())));

            groupRedPacket.sort((o1, o2) -> (o1.getExpTime().compareTo(o2.getExpTime())));
            ExtRedPackageGroup redPacket = groupRedPacket.stream().findFirst().isPresent() ? groupRedPacket.stream().findFirst().get() : new ExtRedPackageGroup();
            String title = "零钱红包";
            groupModel.setPrice(title);
            groupModel.setType(redPacket.getType());
            groupModel.setFirstExpTime(DateUtil.format(redPacket.getExpTime(), Constants.DATE_FORMAT_DOT));
        } else {
            ExtRedPackageGroup redPacket = groupRedPacket.stream().findFirst().get();
            groupModel.setCount(redPacket.getTotalCount());
            groupModel.setType(redPacket.getType());
            groupModel.setNewCount(redPacket.getNewCount());
            groupModel.setPrice(DoubleUtil.toFixed(redPacket.getPrice(), Constants.PRICE_FORMAT));
            groupModel.setFirstExpTime(DateUtil.format(redPacket.getExpTime(), Constants.DATE_FORMAT_DOT));
            groupModel.setTotalPrice(RegExpMatcher.matcherPrice(DoubleUtil.toFixed(redPacket.getTotalPrice(), Constants.PRICE_FORMAT)));
            if (groupType == RedPackageGroupType.EIGHT) {
                groupModel.setRemark(remark);
            }
        }

        if (isPreLoad || groupModel.getCount() == 1) {
            PageHelper.startPage(1, Constants.PAGE_SIZE);
            double price = 0;
            if (groupType == RedPackageGroupType.OTHER) {
                price = 0;
            } else if (groupType == RedPackageGroupType.TWO) {
                price = 2;
            } else if (groupType == RedPackageGroupType.FIVE) {
                price = 5;
            } else if (groupType == RedPackageGroupType.EIGHT) {
                price = 8;
            }
            Page<ExtRedPackage> pager = extRedPackageDao.pageEffectiveByCustomerIdAndPrice(customer.getId(), price);
            Date now = new Date();
            List<RedPackageModel> packageModels = formatRedpackageModel(pager);

            if (packageModels.size() == 1) {
                groupModel.setRedPackageModel(packageModels.get(0));
            }
            groupModel.setPreLoadTotalPage(pager.getPages());

            PageHelper.clearPage();
            groupModel.setPreLoadList((ArrayList<RedPackageModel>) packageModels);
        }

        return groupModel;
    }

    @Override
    public ListViewModel readPackageGroupList(Customer customer, ListRequestModel model) throws Exception {
        if (model == null || model.getGroupType() == null) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        if (model.getSortType() == null) {
            model.setSortType(RedPackageSortType.EXPTIME);
        }

        PageHelper.startPage(model.getPageIndex(), Constants.PAGE_SIZE);
        double price = 0;
        if (model.getGroupType() == RedPackageGroupType.OTHER) {
            price = 0;
        } else if (model.getGroupType() == RedPackageGroupType.TWO) {
            price = 2;
        } else if (model.getGroupType() == RedPackageGroupType.FIVE) {
            price = 5;
        } else if (model.getGroupType() == RedPackageGroupType.EIGHT) {
            price = 8;
        }
        Page<ExtRedPackage> pager = extRedPackageDao.pageEffectiveByCustomerIdAndPrice(customer.getId(), price);

        List<RedPackageModel> list = formatRedpackageModel(pager);

        // 更新是否最新红包
        for (RedPackageModel item : list) {
            if (null != item && item.isNew()) {
                extRedPackageDao.updateIsNewByCustomerIdAndId(customer.getId(), item.getId(), 0);
            }
        }
        int totalPages = pager.getPages();
        PageHelper.clearPage();

        return new ListViewModel(totalPages, (ArrayList<RedPackageModel>) list);
    }

    @Override
    public HistoryViewModel readPackageHistory(Customer customer, HistoryRequestModel model) throws Exception {
        if (model == null) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        if (model.getSortType() == null) {
            model.setSortType(RedPackageSortType.EXPTIME);
        }

        Date now = new Date();
        PageHelper.startPage(model.getPageIndex(), Constants.PAGE_SIZE);
        String created = DateUtil.format(DateUtil.addDay(now, -30), Constants.DATE_TIME_FORMAT);
        String expTime = DateUtil.format(now, Constants.DATE_TIME_FORMAT);
        Page<ExtRedPackage> pager;

        if (model.getSortType() == RedPackageSortType.EXPTIME) {
            if (model.isLoadEarlier()) {
                pager = extRedPackageDao.pageEffectiveEarlierHistoryByCustomerIdOrderByType(customer.getId(), created, expTime, model.getSortType().getName());
            } else {
                pager = extRedPackageDao.pageEffectiveHistoryByCustomerIdOrderByType(customer.getId(), created, expTime, model.getSortType().getName());
            }
        } else {
            pager = extRedPackageDao.pageEffectiveHistoryByCustomerIdOrderByPrice(customer.getId(), expTime);
        }

        String remark = "仅可购买120天及以上长期羊使用";
        List<RedPackageModel> list = formatRedpackageModel(pager);

        PageHelper.clearPage();
        return new HistoryViewModel(pager.getPages(), (ArrayList<RedPackageModel>) list);
    }

    @Override
    public DetailViewModel readPackageDetail(Customer customer, DetailRequestModel model) throws Exception {
        if (model == null || model.getId() <= 0) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        ExtRedPackage extRedPackage = extRedPackageDao.getById(model.getId());
        if (null == extRedPackage) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }

        String item1 = "每个红包只能使用一次";
        String item2 = "该红包仅可购买120天及以上长期羊使用";
        ArrayList<String> remarks = new ArrayList<>();
        remarks.add(item1);
        int value = 5;
        if (extRedPackage.getPrice() > value) {
            remarks.add(item2);
        }
        Date now = new Date();
        RedPackageState redPackageState = extRedPackage.isUsed() ? RedPackageState.USRD : RedPackageState.UNUSED;
        if (redPackageState == RedPackageState.UNUSED && extRedPackage.getExpTime().getTime() < now.getTime()) {
            redPackageState = RedPackageState.EXPIRED;
        }
        DetailViewModel viewModel = new DetailViewModel(
                extRedPackage.getType(),
                Constants.redpackettypestr(extRedPackage.getType()),
                DoubleUtil.toFixed(extRedPackage.getPrice(), Constants.PRICE_FORMAT),
                DateUtil.format(extRedPackage.getCreated(), Constants.DATE_FORMAT_DOT),
                DateUtil.format(extRedPackage.getExpTime(), Constants.DATE_FORMAT_DOT),
                remarks,
                redPackageState.getName()
        );

        if (extRedPackage.getIsNew() == 1) {
            extRedPackageDao.updateIsNewByCustomerIdAndId(customer.getId(), extRedPackage.getId(), 0);
        }
        return viewModel;
    }

    @Override
    public ListViewModel readPackageList(Customer customer, ListRequestModel model) throws Exception {
        if (model == null) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        if (model.getSortType() == null) {
            model.setSortType(RedPackageSortType.EXPTIME);
        }

        PageHelper.startPage(model.getPageIndex(), Constants.PAGE_SIZE);
        Page<ExtRedPackage> pager = extRedPackageDao.pageEffectiveExtRedPackageByCustomerIdOrderByType(customer.getId(), model.getSortType().getName());
        List<RedPackageModel> list = formatRedpackageModel(pager);

        PageHelper.clearPage();
        return new ListViewModel(pager.getPages(), (ArrayList<RedPackageModel>) list);
    }

    /**
     * 格式化红包数据实体
     *
     * @param pager extRedPackage数据
     * @return List<RedPackageModel>
     */
    private List<RedPackageModel> formatRedpackageModel(Page<ExtRedPackage> pager) {
        Date now = new Date();
        String remark = "仅可购买120天及以上长期羊使用";
        return pager.stream()
                .map(en -> new RedPackageModel(
                        en.getId(),
                        DoubleUtil.toFixed(en.getPrice(), Constants.PRICE_FORMAT),
                        en.getPrice() > 5 ? remark : "",
                        en.getType(),
                        Constants.redpackettypestr(en.getType()),
                        en.getIsNew() == 1,
                        DateUtil.format(en.getExpTime(), Constants.DATE_FORMAT_DOT),
                        en.isUsed() ? RedPackageState.USRD.getName() :
                                (DateUtil.subDateOfDay(now, en.getExpTime()) >= 0 ? RedPackageState.EXPIRED.getName() : RedPackageState.UNUSED.getName())
                )).collect(Collectors.toList());
    }
}
