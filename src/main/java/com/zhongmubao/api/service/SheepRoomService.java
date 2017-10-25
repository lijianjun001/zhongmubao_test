package com.zhongmubao.api.service;

import com.zhongmubao.api.dto.request.sheep.room.*;
import com.zhongmubao.api.dto.response.sheep.room.*;
import com.zhongmubao.api.entity.Customer;

/**
 * 我的羊圈
 * @author  xy
 * @date 2017/10/25
 */
public interface SheepRoomService {
    /**
     * 我的羊圈
     * @param customer 用户
     * @param model 请求参数 pageIndex projectType
     * @return SheepRoomViewModel
     * @throws Exception 异常
     */
    SheepRoomViewModel sheepRoom(Customer customer, SheepRoomRequestModel model) throws Exception;

    /**
     * 我的羊圈 订单弹框
     * @param customer 用户
     * @param model 请求参数 projectId
     * @return SheepRoomOrdersViewModel
     * @throws Exception 异常
     */
    SheepRoomOrdersViewModel sheepRoomOrders(Customer customer, SheepRoomOrdersRequestModel model) throws Exception;
}
