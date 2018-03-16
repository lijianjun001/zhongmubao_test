package com.zhongmubao.api.service;

import com.zhongmubao.api.dto.request.message.DetailRequestModel;
import com.zhongmubao.api.dto.request.message.ListRequestModel;
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
     * @return ListViewModel
     * @throws Exception
     */
    CenterViewModel messageCenter(Customer customer) throws Exception;

    /**
     * 消息列表
     *
     * @param customer 当前用户
     * @param model    请求参数
     * @return ListViewModel
     * @throws Exception
     */
    ListViewModel messageList(Customer customer, ListRequestModel model) throws Exception;

    /**
     * 消息详情
     *
     * @param customer 当前用户
     * @param model    请求参数
     * @return ListViewModel
     * @throws Exception
     */
    DetailViewModel messageDetail(Customer customer, DetailRequestModel model) throws Exception;
}
