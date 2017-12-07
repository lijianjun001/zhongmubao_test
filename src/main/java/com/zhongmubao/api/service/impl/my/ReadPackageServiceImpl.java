package com.zhongmubao.api.service.impl.my;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhongmubao.api.config.Constants;
import com.zhongmubao.api.config.ResultStatus;
import com.zhongmubao.api.config.enmu.RedPackageGroupType;
import com.zhongmubao.api.config.enmu.RedPackageSortType;
import com.zhongmubao.api.dao.ExtRedPackageDao;
import com.zhongmubao.api.dto.request.my.readpackage.ReadPackageGroupRequestModel;
import com.zhongmubao.api.dto.request.my.readpackage.ReadPackageListRequestModel;
import com.zhongmubao.api.dto.response.my.readpackage.*;
import com.zhongmubao.api.entity.Customer;
import com.zhongmubao.api.entity.ExtRedPackage;
import com.zhongmubao.api.exception.ApiException;
import com.zhongmubao.api.mongo.entity.base.PageModel;
import com.zhongmubao.api.service.my.ReadPackageService;
import com.zhongmubao.api.util.DateUtil;
import com.zhongmubao.api.util.DoubleUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 我的红包
 *
 * @author 孙阿龙
 */
@Service
public class ReadPackageServiceImpl implements ReadPackageService {
    public final ExtRedPackageDao extRedPackageDao;

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
        List<ExtRedPackage> list = extRedPackageDao.getByCustomerIdOrderByType(customer.getId(), model.getSortType().getName());
        // 8元红包
        List<ExtRedPackage> eightGroup = list.stream().filter(en -> en.getPrice() == 8).collect(Collectors.toList());
        if (eightGroup != null && eightGroup.size() > 0) {
            groupModelList.add(redPacketGroupCalc(eightGroup));
            list.removeAll(eightGroup);
        }
        // 5元红包
        List<ExtRedPackage> fiveGroup = list.stream().filter(en -> en.getPrice() == 5).collect(Collectors.toList());
        if (fiveGroup != null && fiveGroup.size() > 0) {
            groupModelList.add(redPacketGroupCalc(fiveGroup));
            list.removeAll(fiveGroup);
        }
        // 2元红包
        List<ExtRedPackage> twoGroup = list.stream().filter(en -> en.getPrice() == 2).collect(Collectors.toList());
        if (twoGroup != null && twoGroup.size() > 0) {
            groupModelList.add(redPacketGroupCalc(twoGroup));
            list.removeAll(twoGroup);
        }
        // 20元红包
        List<ExtRedPackage> twentyGroup = list.stream().filter(en -> en.getPrice() == 20).collect(Collectors.toList());
        if (twentyGroup != null && twentyGroup.size() > 0) {
            groupModelList.add(redPacketGroupCalc(twentyGroup));
            list.removeAll(twentyGroup);
        }
        // 零钱红包
        if (list != null && list.size() > 0) {
            ReadPackageGroupModel looseRadPacket = redPacketGroupCalc(list);
            looseRadPacket.setPrice("零钱红包");
            groupModelList.add(looseRadPacket);
        }


        viewModel.setList(groupModelList);
        return viewModel;
    }

    /**
     * 红包分组计算
     *
     * @param groupRedPacket
     * @return
     */
    private ReadPackageGroupModel redPacketGroupCalc(List<ExtRedPackage> groupRedPacket) {
        ReadPackageGroupModel groupModel = new ReadPackageGroupModel();
        groupModel.setCount(groupRedPacket.size());
        DoubleSummaryStatistics statsTotalPrice = groupRedPacket.stream().mapToDouble((x) -> x.getPrice()).summaryStatistics();
        groupModel.setTotalPrice(Double.toString(statsTotalPrice.getSum()));
        groupModel.setNewCount(Integer.parseInt(String.valueOf(groupRedPacket.stream().filter(m -> m.getIsNew() == 1).count())));

        Collections.sort(groupRedPacket, (o1, o2) -> (o1.getExpTime().compareTo(o2.getExpTime())));
        ExtRedPackage extRedPackage = groupRedPacket.stream().findFirst().get();
        groupModel.setFirstExpTime(DateUtil.formatDefault(extRedPackage.getExpTime()));
        groupModel.setPrice(Double.toString(extRedPackage.getPrice()));
        groupModel.setType(extRedPackage.getType());

        String remark = "仅可购买120天及以上长期羊使用";
        List<ReadPackageModel> eightViewGroup = groupRedPacket.stream().map(
                en -> new ReadPackageModel(
                        en.getId(),
                        DoubleUtil.toFixed(en.getPrice(), Constants.Price_FORMAT),
                        en.getSheepDay() == 120 ? remark : "",
                        Constants.redpackettypestr(en.getType()),
                        en.getIsNew() == 1,
                        DateUtil.format(en.getExpTime(), Constants.DATE_FORMAT)
                )).collect(Collectors.toList());

        groupModel.setPreLoadList((ArrayList<ReadPackageModel>) eightViewGroup);
        groupModel.setPreLoadPageIndex(1);

        return groupModel;
    }

    @Override
    public ReadPackageListViewModel readPackageList(Customer customer, ReadPackageListRequestModel model) throws Exception {
        if (model == null || model.getGroupType() == null) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }

        PageHelper.startPage(model.getPageIndex(), Constants.PAGE_SIZE);
        Page<ExtRedPackage> pager = extRedPackageDao.pageEffectiveByCustomerIdAndPrice(customer.getId(), model.getGroupType().getName());

        String remark = "仅可购买120天及以上长期羊使用";
        List<ReadPackageModel> list = pager.stream()
                .map(en -> new ReadPackageModel(
                        en.getId(),
                        DoubleUtil.toFixed(en.getPrice(), Constants.Price_FORMAT),
                        en.getSheepDay() == 120 ? remark : "",
                        Constants.redpackettypestr(en.getType()),
                        en.getIsNew() == 1,
                        DateUtil.format(en.getExpTime(), Constants.DATE_FORMAT)
                )).collect(Collectors.toList());

        return new ReadPackageListViewModel(pager.getPages(), (ArrayList<ReadPackageModel>) list);
    }

    @Override
    public RedPackageExpiredViewModel readPackageExpired(Customer customer, ReadPackageGroupRequestModel model) throws Exception {
        if (model == null) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        if (model.getSortType() == null) {
            model.setSortType(RedPackageSortType.ExpTime);
        }
        PageHelper.startPage(model.getPageIndex(), Constants.PAGE_SIZE);
        Page<ExtRedPackage> pager = extRedPackageDao.pageEffectiveByCustomerIdOrderByType(customer.getId(), model.getSortType().getName());

        String remark = "仅可购买120天及以上长期羊使用";
        List<ReadPackageModel> list = pager.stream()
                .map(en -> new ReadPackageModel(
                        en.getId(),
                        DoubleUtil.toFixed(en.getPrice(), Constants.Price_FORMAT),
                        en.getSheepDay() == 120 ? remark : "",
                        Constants.redpackettypestr(en.getType()),
                        en.getIsNew() == 1,
                        DateUtil.format(en.getExpTime(), Constants.DATE_FORMAT)
                )).collect(Collectors.toList());

        return new RedPackageExpiredViewModel(pager.getPages(), (ArrayList<ReadPackageModel>) list);
    }
}
