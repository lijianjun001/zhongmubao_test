package com.zhongmubao.api.service.impl;

import com.zhongmubao.api.config.Constants;
import com.zhongmubao.api.dao.SheepOrderDao;
import com.zhongmubao.api.dao.SheepProjectDao;
import com.zhongmubao.api.dto.response.my.MyPastureViewModel;
import com.zhongmubao.api.entity.Customer;
import com.zhongmubao.api.service.SheepService;
import com.zhongmubao.api.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Shepp服务
 *
 * @author 孙阿龙
 */
@Service
public class SheepServiceImpl extends BaseService implements SheepService {

    private final SheepOrderDao sheepOrderDao;
    private final SheepProjectDao sheepProjectdao;

    @Autowired
    public SheepServiceImpl(SheepOrderDao sheepOrderDao, SheepProjectDao sheepProjectdao) {
        this.sheepOrderDao = sheepOrderDao;
        this.sheepProjectdao = sheepProjectdao;
    }

    @Override
    public MyPastureViewModel miniappsMyPasture(Customer customer) throws Exception {
        // 1、在栏羊只
        int totalCount = sheepOrderDao.sumSheepOrderCountByCustomerIdAndState(customer.getId(), Constants.SHEEP_IN_THE_BAR_STATE);

        // 2、最早赎回
        Date firstRedeemTime = sheepProjectdao.firstRedeem(customer.getId(), Constants.SHEEP_IN_THE_BAR_STATE);
        String firstRedeem = DateUtil.format(firstRedeemTime, Constants.DATETIME_FORMAT_MONTH_DAY);

        // 3、最晚赎回
        Date finallyRedeemTime = sheepProjectdao.finallyRedeem(customer.getId(), Constants.SHEEP_IN_THE_BAR_STATE);
        String finallyRedeem = DateUtil.format(finallyRedeemTime, Constants.DATETIME_FORMAT_MONTH_DAY);

        MyPastureViewModel viewModel = new MyPastureViewModel();
        viewModel.setInbarCount(totalCount);
        viewModel.setFirstRedeemDate(firstRedeem);
        viewModel.setFinallyRedeemDate(finallyRedeem);
        return viewModel;
    }
}
