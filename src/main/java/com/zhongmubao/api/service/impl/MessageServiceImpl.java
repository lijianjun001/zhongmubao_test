package com.zhongmubao.api.service.impl;


import com.zhongmubao.api.config.Constants;
import com.zhongmubao.api.config.ResultStatus;
import com.zhongmubao.api.config.enmu.CustomerMessageType;
import com.zhongmubao.api.dto.request.message.CustomermessageRequestModel;
import com.zhongmubao.api.dto.response.message.CustomerMessageListViewModel;
import com.zhongmubao.api.dto.response.message.CustomerMessageModel;
import com.zhongmubao.api.entity.Customer;
import com.zhongmubao.api.exception.ApiException;
import com.zhongmubao.api.mongo.dao.CustomerMessageMongoDao;
import com.zhongmubao.api.mongo.dao.CustomerMessageTipsMongoDao;
import com.zhongmubao.api.mongo.dao.CustomerMessageTypeMongoDao;
import com.zhongmubao.api.mongo.entity.CustomerMessageMongo;
import com.zhongmubao.api.mongo.entity.CustomerMessageTipsMongo;
import com.zhongmubao.api.mongo.entity.CustomerMessageTypeMongo;
import com.zhongmubao.api.mongo.entity.base.PageModel;
import com.zhongmubao.api.service.MessageService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息中心实现类
 *
 * @author 米立林
 */
@Service
public class MessageServiceImpl extends BaseService implements MessageService {

    private final CustomerMessageMongoDao customerMessageMongoDao;
    private final CustomerMessageTypeMongoDao customerMessageTypeMongoDao;
    private final CustomerMessageTipsMongoDao customerMessageTipsMongoDao;

    public MessageServiceImpl(CustomerMessageMongoDao customerMessageMongoDao, CustomerMessageTypeMongoDao customerMessageTypeMongoDao, CustomerMessageTipsMongoDao customerMessageTipsMongoDao) {
        this.customerMessageMongoDao = customerMessageMongoDao;
        this.customerMessageTypeMongoDao = customerMessageTypeMongoDao;
        this.customerMessageTipsMongoDao = customerMessageTipsMongoDao;
    }

    //region

    @Override
    public CustomerMessageListViewModel messageCenter(Customer customer) throws Exception {
        if (customer == null) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        CustomerMessageListViewModel viewModel = new CustomerMessageListViewModel();
        List<CustomerMessageMongo> projectMessages = customerMessageMongoDao.getListByLimit(customer.getId(), Constants.PAGE_SIZE, CustomerMessageType.OPEN_PROJECT.getName());
        List<CustomerMessageMongo> personMessages = customerMessageMongoDao.getListByLimit(customer.getId(), Constants.PAGE_SIZE, CustomerMessageType.PERSON_MESSAGE.getName());
        List<CustomerMessageMongo> systemMessages = customerMessageMongoDao.getListByLimit(customer.getId(), Constants.PAGE_SIZE, CustomerMessageType.SYSTEM_MESSAGE.getName());
        if (null != projectMessages && projectMessages.size() > 0) {
            viewModel.setProjectList(formatMessage(projectMessages));
        }
        if (null != personMessages && personMessages.size() > 0) {
            viewModel.setPersonList(formatMessage(personMessages));
        }
        if (null != systemMessages && systemMessages.size() > 0) {
            viewModel.setSystemList(formatMessage(systemMessages));
            // 消息中心再判断一下置顶和新
        }
        return viewModel;
    }

    @Override
    public CustomerMessageListViewModel messageList(Customer customer, CustomermessageRequestModel model) throws Exception {
        if (model == null) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        if (model.getPageIndex() <= 0) {
            model.setPageIndex(1);
        }
        CustomerMessageListViewModel viewModel = new CustomerMessageListViewModel();
        PageModel<CustomerMessageMongo> pager = new PageModel<>();
        pager.setPageNo(model.getPageIndex());
        pager.setPageSize(Constants.PAGE_SIZE);
        pager = customerMessageMongoDao.pager(customer.getId(), pager, model.getType());
        ArrayList<CustomerMessageModel> list = formatMessage(pager.getDatas());
        if (CustomerMessageType.OPEN_PROJECT.getName().equals(model.getType())) {
            viewModel.setProjectList(list);
        }
        if (CustomerMessageType.PERSON_MESSAGE.getName().equals(model.getType())) {
            viewModel.setPersonList(list);
        }
        if (CustomerMessageType.SYSTEM_MESSAGE.getName().equals(model.getType())) {
            viewModel.setSystemList(list);
        }
        viewModel.setTotalPage(pager.getPageSize());

        return viewModel;
    }

    /**
     * 格式化消息数据
     *
     * @param messages 消息中心列表
     * @return ArrayList<CustomerMessageModel>
     * @throws Exception Exception
     */
    private ArrayList<CustomerMessageModel> formatMessage(List<CustomerMessageMongo> messages) throws Exception {
        ArrayList<CustomerMessageModel> list = new ArrayList<>();
        for (CustomerMessageMongo message : messages) {
            CustomerMessageModel cusMsg = new CustomerMessageModel();
            String typeName = Constants.STRING_EMPTY;
            String typeIcon = Constants.STRING_EMPTY;
            String tip = Constants.STRING_EMPTY;
            String backColor = Constants.STRING_EMPTY;
            CustomerMessageTypeMongo msgType = customerMessageTypeMongoDao.getById(message.getCustomerMessageTypeId());
            if (null != msgType) {
                typeName = msgType.getName();
                typeIcon = msgType.getIcon();
            }
            CustomerMessageTipsMongo msgTips = customerMessageTipsMongoDao.getById(message.getCustomerMessageTipsId());
            if (null != msgTips) {
                tip = msgTips.getName();
                backColor = msgTips.getBackColor();
            }
            if (CustomerMessageType.SYSTEM_MESSAGE.getName().equals(message.getType())) {
                if (!message.getIsRead()) {
                    tip = Constants.STRING_NEW;
                }
                if (message.getIsTop()) {
                    tip = Constants.STRING_TOP;
                }
            }
            cusMsg.setTitle(message.getTitle());
            cusMsg.setContent(message.getContent());
            cusMsg.setType(message.getType());
            cusMsg.setRead(message.getIsRead());
            cusMsg.setTop(message.getIsTop());
            cusMsg.setMessageTypeName(typeName);
            cusMsg.setMessageTypeIcon(typeIcon);
            cusMsg.setTips(tip);
            cusMsg.setBackColor(backColor);
            list.add(cusMsg);
        }
        return list;
    }
    //endregion
}
