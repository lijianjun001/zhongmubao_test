package com.zhongmubao.api.service.impl;

import com.zhongmubao.api.dao.SheepOrderDao;
import com.zhongmubao.api.dto.request.sheep.room.*;
import com.zhongmubao.api.dto.response.sheep.room.*;
import com.zhongmubao.api.dto.response.sheep.room.list.*;
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
    public SheepRoomViewModel room(Customer customer, SheepRoomRequestModel model) throws Exception {
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

    @Override
    public SheepRoomOrdersViewModel orders(Customer customer, SheepRoomOrdersRequestModel model) throws Exception {
        SheepRoomOrdersViewModel sheepRoomOrdersViewModel = new SheepRoomOrdersViewModel();
        sheepRoomOrdersViewModel.setTitle("测试100期");
        sheepRoomOrdersViewModel.setTotalCount(10);
        List<SheepRoomOrdersItemModel> sheepRoomOrdersItemModels = new ArrayList<>();
        SheepRoomOrdersItemModel sheepRoomOrdersItemModel = new SheepRoomOrdersItemModel();
        sheepRoomOrdersItemModel.setOrderCode("00000");
        sheepRoomOrdersItemModel.setCount(10);
        sheepRoomOrdersItemModels.add(sheepRoomOrdersItemModel);
        sheepRoomOrdersViewModel.setList(sheepRoomOrdersItemModels);


        return sheepRoomOrdersViewModel;
    }

    @Override
    public SheepRoomBreedProgressViewModel breedProgress(SheepRoomBreedProgressRequestModel model) throws Exception {
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
}
