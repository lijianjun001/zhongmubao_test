package com.zhongmubao.api.service.impl;

import com.zhongmubao.api.dao.SheepOrderDao;
import com.zhongmubao.api.dto.request.sheep.room.MySheepRoomRequestModel;
import com.zhongmubao.api.dto.response.sheep.room.MySheepRoomViewModel;
import com.zhongmubao.api.entity.Customer;
import com.zhongmubao.api.service.BaseService;
import com.zhongmubao.api.service.MySheepRoomService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 我的羊圈
 *
 * @author xy
 */
public class MySheepRoomServiceImpl extends BaseService implements MySheepRoomService {

    private final SheepOrderDao sheepOrderDao;

    @Autowired
    public MySheepRoomServiceImpl(SheepOrderDao sheepOrderDao) {
        this.sheepOrderDao = sheepOrderDao;
    }

    @Override
    public MySheepRoomViewModel mySheepRoom(Customer customer, MySheepRoomRequestModel model) throws Exception {
        return new MySheepRoomViewModel();
    }
}
