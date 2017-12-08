package com.zhongmubao.api.service.impl.my;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhongmubao.api.config.Constants;
import com.zhongmubao.api.config.ResultStatus;
import com.zhongmubao.api.config.enmu.RedPackageGroupType;
import com.zhongmubao.api.config.enmu.RedPackageSortType;
import com.zhongmubao.api.config.enmu.RedPackageState;
import com.zhongmubao.api.dao.ExtRedPackageDao;
import com.zhongmubao.api.dto.request.my.readpackage.ReadPackageDetailRequestModel;
import com.zhongmubao.api.dto.request.my.readpackage.ReadPackageGroupRequestModel;
import com.zhongmubao.api.dto.request.my.readpackage.ReadPackageListRequestModel;
import com.zhongmubao.api.dto.response.my.readpackage.*;
import com.zhongmubao.api.entity.Customer;
import com.zhongmubao.api.entity.ExtRedPackage;
import com.zhongmubao.api.entity.ext.ExtRedPackageGroup;
import com.zhongmubao.api.exception.ApiException;
import com.zhongmubao.api.service.my.ReadPackageService;
import com.zhongmubao.api.util.DateUtil;
import com.zhongmubao.api.util.DoubleUtil;
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
    public final ExtRedPackageDao extRedPackageDao;

    @Autowired
    public ReadPackageServiceImpl(ExtRedPackageDao extRedPackageDao) {
        this.extRedPackageDao = extRedPackageDao;
    }

    @Override
    public ReadPackageGroupViewModel readPackageGroup(Customer customer, ReadPackageGroupRequestModel model) throws Exception {
        if (model == null) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        if (model.getSortType() == null) {
            model.setSortType(RedPackageSortType.Price);
        }
        ReadPackageGroupViewModel viewModel = new ReadPackageGroupViewModel();
        ArrayList<ReadPackageGroupModel> groupModelList = new ArrayList<>();
        List<ExtRedPackageGroup> list = extRedPackageDao.getByCustomerIdGroupByPrice(customer.getId());
        boolean isPreLoad = true;
        // 8元红包
        List<ExtRedPackageGroup> eightGroup = list.stream().filter(en -> en.getPrice() == 8).collect(Collectors.toList());
        if (eightGroup != null && eightGroup.size() > 0) {
            ReadPackageGroupModel redPacket = redPacketGroupCalc(customer, eightGroup, RedPackageGroupType.EIGHT, isPreLoad);
            isPreLoad = false;
            groupModelList.add(redPacket);
            list.removeAll(eightGroup);
        }
        // 5元红包
        List<ExtRedPackageGroup> fiveGroup = list.stream().filter(en -> en.getPrice() == 5).collect(Collectors.toList());
        if (fiveGroup != null && fiveGroup.size() > 0) {
            ReadPackageGroupModel redPacket = redPacketGroupCalc(customer, fiveGroup, RedPackageGroupType.FIVE, isPreLoad);
            isPreLoad = false;

            groupModelList.add(redPacket);
            list.removeAll(fiveGroup);
        }
        // 2元红包
        List<ExtRedPackageGroup> twoGroup = list.stream().filter(en -> en.getPrice() == 2).collect(Collectors.toList());
        if (twoGroup != null && twoGroup.size() > 0) {
            ReadPackageGroupModel redPacket = redPacketGroupCalc(customer, twoGroup, RedPackageGroupType.TWO, isPreLoad);
            isPreLoad = false;
            groupModelList.add(redPacket);
            list.removeAll(twoGroup);
        }
        // 零钱红包
        if (list.size() > 0) {
            ReadPackageGroupModel looseRadPacket = redPacketGroupCalc(customer, list, RedPackageGroupType.OTHER, isPreLoad);
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
     * @return
     */
    private ReadPackageGroupModel redPacketGroupCalc(Customer customer, List<ExtRedPackageGroup> groupRedPacket, RedPackageGroupType groupType, boolean isPreLoad) {

        ReadPackageGroupModel groupModel = new ReadPackageGroupModel();
        groupModel.setGroupType(groupType);
        if (groupType == RedPackageGroupType.OTHER) {
            IntSummaryStatistics statsTotalCount = groupRedPacket.stream().mapToInt((x) -> x.getTotalCount()).summaryStatistics();
            groupModel.setCount(Integer.parseInt(String.valueOf(statsTotalCount.getSum())));

            DoubleSummaryStatistics statsTotalPrice = groupRedPacket.stream().mapToDouble((x) -> x.getPrice()).summaryStatistics();
            groupModel.setTotalPrice(Double.toString(statsTotalPrice.getSum()));

            IntSummaryStatistics statsTotalNewCount = groupRedPacket.stream().mapToInt((x) -> x.getNewCount()).summaryStatistics();
            groupModel.setNewCount(Integer.parseInt(String.valueOf(statsTotalNewCount.getSum())));

            Collections.sort(groupRedPacket, (o1, o2) -> (o1.getExpTime().compareTo(o2.getExpTime())));
            ExtRedPackageGroup redPacket = groupRedPacket.stream().findFirst().get();
            groupModel.setPrice("零钱红包");
            groupModel.setType(redPacket.getType());
            groupModel.setFirstExpTime(DateUtil.format(redPacket.getExpTime(), Constants.DATE_FORMAT));
        } else {
            ExtRedPackageGroup redPacket = groupRedPacket.stream().findFirst().get();
            groupModel.setCount(redPacket.getTotalCount());
            groupModel.setType(redPacket.getType());
            groupModel.setNewCount(redPacket.getNewCount());
            groupModel.setPrice(DoubleUtil.toFixed(redPacket.getPrice(), Constants.Price_FORMAT));
            groupModel.setFirstExpTime(DateUtil.format(redPacket.getExpTime(), Constants.DATE_FORMAT));
            groupModel.setTotalPrice(DoubleUtil.toFixed(redPacket.getTotalPrice(), Constants.Price_FORMAT));
        }

        if (isPreLoad) {
            PageHelper.startPage(1, 10);
            Page<ExtRedPackage> pager = extRedPackageDao.pageEffectiveByCustomerIdAndPrice(customer.getId(), groupType.getName());
            Date now = new Date();
            String remark = "仅可购买120天及以上长期羊使用";
            List<ReadPackageModel> eightViewGroup = pager.stream().map(
                    en -> new ReadPackageModel(
                            en.getId(),
                            DoubleUtil.toFixed(en.getPrice(), Constants.Price_FORMAT),
                            en.getPrice() >= 5 ? remark : "",
                            Constants.redpackettypestr(en.getType()),
                            en.getIsNew() == 1,
                            DateUtil.format(en.getExpTime(), Constants.DATE_FORMAT),
                            DateUtil.subDateOfDay(now, en.getExpTime()) > 30,
                            en.isUsed() ? RedPackageState.USRD : RedPackageState.UNUSED
                    )).collect(Collectors.toList());

            groupModel.setPreLoadList((ArrayList<ReadPackageModel>) eightViewGroup);
            groupModel.setPreLoadPageIndex(1);
        }

        return groupModel;
    }

    @Override
    public ReadPackageListViewModel readPackageList(Customer customer, ReadPackageListRequestModel model) throws Exception {
        if (model == null || model.getGroupType() == null) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }

        PageHelper.startPage(model.getPageIndex(), Constants.PAGE_SIZE);
        Page<ExtRedPackage> pager = extRedPackageDao.pageEffectiveByCustomerIdAndPrice(customer.getId(), model.getGroupType().getName());

        Date now = new Date();
        String remark = "仅可购买120天及以上长期羊使用";
        List<ReadPackageModel> list = pager.stream()
                .map(en -> new ReadPackageModel(
                        en.getId(),
                        DoubleUtil.toFixed(en.getPrice(), Constants.Price_FORMAT),
                        en.getPrice() >= 5 ? remark : "",
                        Constants.redpackettypestr(en.getType()),
                        en.getIsNew() == 1,
                        DateUtil.format(en.getExpTime(), Constants.DATE_FORMAT),
                        DateUtil.subDateOfDay(now, en.getExpTime()) > 30,
                        en.isUsed() ? RedPackageState.USRD : RedPackageState.UNUSED
                )).collect(Collectors.toList());

        return new ReadPackageListViewModel(pager.getPages(), (ArrayList<ReadPackageModel>) list);
    }

    @Override
    public RedPackageHistoryViewModel readPackageHistory(Customer customer, ReadPackageGroupRequestModel model) throws Exception {
        if (model == null) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        if (model.getSortType() == null) {
            model.setSortType(RedPackageSortType.ExpTime);
        }
        Date now = new Date();
        PageHelper.startPage(model.getPageIndex(), Constants.PAGE_SIZE);
        Page<ExtRedPackage> pager = extRedPackageDao.pageEffectiveHistoryByCustomerIdOrderByType(customer.getId(), model.getSortType().getName());

        String remark = "仅可购买120天及以上长期羊使用";
        List<ReadPackageModel> list = pager.stream()
                .map(en -> new ReadPackageModel(
                        en.getId(),
                        DoubleUtil.toFixed(en.getPrice(), Constants.Price_FORMAT),
                        en.getPrice() >= 5 ? remark : "",
                        Constants.redpackettypestr(en.getType()),
                        en.getIsNew() == 1,
                        DateUtil.format(en.getExpTime(), Constants.DATE_FORMAT),
                        DateUtil.subDateOfDay(now, en.getExpTime()) > 30,
                        en.isUsed() ? RedPackageState.USRD : (DateUtil.subDateOfDay(now, en.getExpTime()) < 0 ? RedPackageState.EXPIRED : RedPackageState.UNUSED)
                )).collect(Collectors.toList());

        return new RedPackageHistoryViewModel(pager.getPages(), (ArrayList<ReadPackageModel>) list);
    }

    @Override
    public ReadPackageDetailViewModel readPackageDetail(Customer customer, ReadPackageDetailRequestModel model) throws Exception {
        if (model == null || model.getId() <= 0) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        ExtRedPackage extRedPackage = extRedPackageDao.getById(model.getId());
        if (null == extRedPackage) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }

        ArrayList<String> remarks = new ArrayList<>();
        remarks.add("每个红包只能使用一次");
        if (extRedPackage.getPrice() >= 5) {
            remarks.add("该红包仅可购买120天及以上长期羊使用");
        }
        Date now = new Date();
        RedPackageState redPackageState = extRedPackage.isUsed() ? RedPackageState.USRD : RedPackageState.UNUSED;
        if (extRedPackage.getExpTime().getTime() < now.getTime()) {
            redPackageState = RedPackageState.EXPIRED;
        }

        return new ReadPackageDetailViewModel(
                Constants.redpackettypestr(extRedPackage.getType()),
                DoubleUtil.toFixed(extRedPackage.getPrice(), Constants.Price_FORMAT),
                DateUtil.format(extRedPackage.getCreated(), Constants.DATE_FORMAT),
                DateUtil.format(extRedPackage.getExpTime(), Constants.DATE_FORMAT),
                remarks,
                redPackageState
        );
    }
}
