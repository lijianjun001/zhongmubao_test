package com.zhongmubao.api.service.impl;

import com.zhongmubao.api.dao.SheepOrderDao;
import com.zhongmubao.api.dto.request.sheep.room.SheepRoomRequestModel;
import com.zhongmubao.api.dto.response.sheep.room.SheepRoomViewModel;
import com.zhongmubao.api.dto.response.sheep.room.list.SheepRoomItemModel;
import com.zhongmubao.api.dto.response.sheep.room.list.SheepRoomModel;
import com.zhongmubao.api.entity.Customer;
import com.zhongmubao.api.service.BaseService;
import com.zhongmubao.api.service.SheepRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 我的羊圈
 *
 * @author xy
 */
@Service
public class SheepRoomServiceImpl extends BaseService implements SheepRoomService {

    private final SheepOrderDao sheepOrderDao;

    @Autowired
    public SheepRoomServiceImpl(SheepOrderDao sheepOrderDao) {
        this.sheepOrderDao = sheepOrderDao;
    }

    @Override
    public SheepRoomViewModel mySheepRoom(Customer customer, SheepRoomRequestModel model) throws Exception {
        SheepRoomViewModel sheepRoomViewModel = new SheepRoomViewModel();
        sheepRoomViewModel.setCustomerLevel(1);
        sheepRoomViewModel.setHasOrder(true);
        sheepRoomViewModel.setTotalSheepCount(10);
        SheepRoomModel sheepRoomModel = new SheepRoomModel();
        sheepRoomModel.setTotalPage(10);
        sheepRoomModel.setTotalSheepCount(10);
        List<SheepRoomItemModel> sheepRoomItemModels = new ArrayList<SheepRoomItemModel>();
        SheepRoomItemModel sheepRoomItemModel = new SheepRoomItemModel();
        sheepRoomItemModel.setProjectId(100);
        sheepRoomItemModel.setTitle("测试100期");
        sheepRoomItemModel.setBreedProgress("养殖中");
        sheepRoomItemModel.setBreedProgressImg("http://192.168.31.210:10210/weixin/images/foldstate3.png?养殖中图片");
        sheepRoomItemModel.setCount(10);
        sheepRoomItemModel.setVenderId(24);
        sheepRoomItemModel.setVendor("力农羊业");
        sheepRoomItemModel.setSheepPhoto("http://192.168.31.210:10210/weixin/images/foldstate3.png?羊只头像图片");
        sheepRoomItemModel.setSurplusDay(80);
        sheepRoomItemModel.setBeginTime("2017.09.19");
        sheepRoomItemModel.setEndTime("2018.01.19");
        sheepRoomItemModels.add(sheepRoomItemModel);
        sheepRoomModel.setList(sheepRoomItemModels);
        sheepRoomViewModel.setSheepRoomModel(sheepRoomModel);

        return sheepRoomViewModel;
    }
}
