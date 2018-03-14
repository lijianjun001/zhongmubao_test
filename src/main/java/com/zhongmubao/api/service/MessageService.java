package com.zhongmubao.api.service;

import com.zhongmubao.api.dto.request.message.CustomermessageRequestModel;
import com.zhongmubao.api.dto.response.message.CustomerMessageListViewModel;
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
    CustomerMessageListViewModel messageCenter(Customer customer) throws Exception;

    /**
     * 消息列表
     *
     * @param customer 当前用户
     * @param model    请求参数
     * @return CustomerMessageListViewModel
     * @throws Exception
     */
    CustomerMessageListViewModel messageList(Customer customer, CustomermessageRequestModel model) throws Exception;
}
