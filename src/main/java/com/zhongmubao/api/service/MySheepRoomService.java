package com.zhongmubao.api.service;

import com.zhongmubao.api.dto.request.sheep.room.MySheepRoomRequestModel;
import com.zhongmubao.api.dto.response.sheep.room.MySheepRoomViewModel;
import com.zhongmubao.api.entity.Customer;

/**
 * 我的羊圈
 * @author  xy
 * @date 2017/10/25
 */
public interface MySheepRoomService {
    /**
     * 我的羊圈
     * @param customer 用户
     * @param model 请求参数 pageIndex projectType
     * @return MySheepRoomViewModel
     * @throws Exception 异常
     */
    MySheepRoomViewModel mySheepRoom(Customer customer, MySheepRoomRequestModel model) throws Exception;
}
