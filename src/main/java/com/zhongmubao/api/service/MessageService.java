package com.zhongmubao.api.service;

import com.zhongmubao.api.dto.request.message.CustomerMessageDetailRequestModel;
import com.zhongmubao.api.dto.request.message.CustomerMessageListRequestModel;
import com.zhongmubao.api.dto.response.message.*;
import com.zhongmubao.api.entity.Customer;

/**
 * 消息中心接口
 *
 * @author 米立林
 */
public interface MessageService {

    /**
     * 消息中心
     *
     * @param customer 当前用户
     * @return CustomerMessageListViewModel
     * @throws Exception
     */
    CustomerMessageCenterViewModel messageCenter(Customer customer) throws Exception;

    /**
     * 消息列表
     *
     * @param customer 当前用户
     * @param model    请求参数
     * @return CustomerMessageListViewModel
     * @throws Exception
     */
    CustomerMessageListViewModel messageList(Customer customer, CustomerMessageListRequestModel model) throws Exception;

    /**
     * 消息详情
     *
     * @param customer 当前用户
     * @param model    请求参数
     * @return CustomerMessageListViewModel
     * @throws Exception
     */
    CustomerMessageDetailViewModel messageDetail(Customer customer, CustomerMessageDetailRequestModel model) throws Exception;
}
