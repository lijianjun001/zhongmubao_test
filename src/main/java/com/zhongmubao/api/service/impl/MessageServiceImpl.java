package com.zhongmubao.api.service.impl;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhongmubao.api.config.Constants;
import com.zhongmubao.api.config.ResultStatus;
import com.zhongmubao.api.config.enmu.CustomerMessageType;
import com.zhongmubao.api.dto.request.message.CustomerMessageDetailRequestModel;
import com.zhongmubao.api.dto.request.message.CustomerMessageRequestModel;
import com.zhongmubao.api.dto.response.message.*;
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
import com.zhongmubao.api.util.ArrayUtil;
import com.zhongmubao.api.util.DateUtil;
import com.zhongmubao.api.util.SerializeUtil;
import com.zhongmubao.api.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    public MessageServiceImpl(CustomerMessageMongoDao customerMessageMongoDao, CustomerMessageTypeMongoDao customerMessageTypeMongoDao, CustomerMessageTipsMongoDao customerMessageTipsMongoDao) {
        this.customerMessageMongoDao = customerMessageMongoDao;
        this.customerMessageTypeMongoDao = customerMessageTypeMongoDao;
        this.customerMessageTipsMongoDao = customerMessageTipsMongoDao;
    }


    //region
    @Override
    public CustomerMessageCenterViewModel messageCenter(Customer customer) throws Exception {
        if (customer == null) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        Date now = new Date();
        int pagesize = 3;
        String method = "center";
        CustomerMessageCenterViewModel viewModel = new CustomerMessageCenterViewModel();
        List<CustomerMessageMongo> projectMessages = customerMessageMongoDao.getListByLimit(customer.getId(), pagesize, CustomerMessageType.OPEN_PROJECT.getName());
        List<CustomerMessageMongo> personMessages = customerMessageMongoDao.getListByLimit(customer.getId(), pagesize, CustomerMessageType.PERSON_MESSAGE.getName());
        List<CustomerMessageMongo> systemMessages = customerMessageMongoDao.getListByLimit(customer.getId(), pagesize, CustomerMessageType.SYSTEM_MESSAGE.getName());

        if (ArrayUtil.isNull(projectMessages)) {
            String weekSection = DateUtil.getWeekSection();
            Optional<CustomerMessageMongo> optionalCms = projectMessages.stream().filter(m -> m.getTitle().equals(weekSection)).findFirst();
            CustomerMessageMongo project = optionalCms.get();
            String month = DateUtil.monthOfDate(now) + "月";
            projectMessages.clear();
            project.setContent(Constants.STRING_EMPTY);
            projectMessages.add(project);
            viewModel.setProjectList(formatMessage(projectMessages, method));
        }
        if (ArrayUtil.isNull(personMessages)) {
            viewModel.setPersonList(formatMessage(personMessages, method));
        }
        if (ArrayUtil.isNull(systemMessages)) {
            viewModel.setSystemList(formatMessage(systemMessages, method));
        }
        return viewModel;
    }

    @Override
    public CustomerMessageListViewModel messageList(Customer customer, CustomerMessageRequestModel model) throws Exception {
        if (model == null) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        if (model.getPageIndex() <= 0) {
            model.setPageIndex(1);
        }
        String method = "list";
        CustomerMessageListViewModel viewModel = new CustomerMessageListViewModel();
        PageModel<CustomerMessageMongo> pager = new PageModel<>();
        pager.setPageNo(model.getPageIndex());
        pager.setPageSize(Constants.PAGE_SIZE);
        pager = customerMessageMongoDao.pager(customer.getId(), pager, model.getType());
        ArrayList<CustomerMessageModel> list = formatMessage(pager.getDatas(), method);
        viewModel.setList(list);
        viewModel.setTotalPage(pager.getTotalPages());

        return viewModel;
    }

    /**
     * 开标信息专属详情
     *
     * @param id 当前客户
     * @return ProjectMessageListViewModel
     * @throws Exception Exception
     * @author 米立林
     */
    private ProjectMessageListViewModel projectMessageDetail(String id) throws Exception {
        if (StringUtil.isNullOrEmpty(id)) {
            throw new ApiException(ResultStatus.PARAMETER_ERROR);
        }
        Date now = new Date();
        String curDayString = "今日";
        String method = "detail";
        String curPlan = "本周羊标计划";
        String historyPlan = "历史羊标计划";
        String title = Constants.STRING_EMPTY;
        String remark = Constants.STRING_EMPTY;
        List<ProjectWeekInfoModel> list = new ArrayList<>();
        CustomerMessageMongo message = customerMessageMongoDao.getById(id);
        if (CustomerMessageType.OPEN_PROJECT.getName().equals(message.getType())) {
            remark = message.getTitle();
            if (remark.equals(DateUtil.getWeekSection())) {
                title = curPlan;
            } else {
                title = historyPlan;
            }
            List<ProjectDetailModel> projectList = new Gson().fromJson(message.getContent(), new TypeToken<List<ProjectDetailModel>>() {
            }.getType());
            for (ProjectDetailModel plan : projectList) {
                String week = plan.getDay();
                String curWeek = DateUtil.getWeekOfDate(now);
                if (week.contains(curWeek)) {
                    week = curDayString;
                }
                String lambdaWeek = week;
                if (list.stream().anyMatch(m -> m.getWeek().equals(lambdaWeek))) {
                    continue;
                }
                // "00"已售罄 "01"未售罄
                String sellout = "00";
                if (remark.equals(DateUtil.getWeekSection())) {
                    int weekNum = DateUtil.getWeekOfDateNumber(now);
                    if (StringUtil.convartWeekToNumber(plan.getDay()) >= weekNum) {
                        sellout = "01";
                    }
                }
                if (list.stream().noneMatch(m -> m.getWeek().equals(lambdaWeek))) {
                    ProjectWeekInfoModel weekInfo = new ProjectWeekInfoModel();
                    weekInfo.setWeek(week);
                    List<ProjectDetailModel> planList = new ArrayList<>();
                    for (ProjectDetailModel item : projectList) {
                        if (item.getDay().equals(plan.getDay())) {
                            planList.add(item);
                        }
                    }
                    weekInfo.setSellout(sellout);
                    weekInfo.setList(planList);
                    list.add(weekInfo);
                }
            }
        }

        return new ProjectMessageListViewModel(title, remark, list);
    }

    @Override
    public CustomerMessageDetailViewModel messageDetail(Customer customer, CustomerMessageDetailRequestModel model) throws Exception {
        if (model == null) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        CustomerMessageMongo message = customerMessageMongoDao.getById(model.getId());
        if (null == message) {
            return new CustomerMessageDetailViewModel();
        }
        String title = message.getTitle();
        String text = DateUtil.format(message.getCreated(), Constants.DATETIME_FORMAT_CHINA);
        String content = message.getContent();
        if (CustomerMessageType.OPEN_PROJECT.getName().equals(message.getType())) {
            ProjectMessageListViewModel projectViewModel = projectMessageDetail(model.getId());
            title = projectViewModel.getTitle();
            text = projectViewModel.getRemark();
            content = SerializeUtil.serialize(projectViewModel);
        }

        return new CustomerMessageDetailViewModel(title, text, content);
    }

    /**
     * 格式化消息数据
     *
     * @param messages 消息中心列表
     * @param method   方法来源，center or list
     * @return ArrayList<CustomerMessageModel>
     * @throws Exception Exception
     */
    private ArrayList<CustomerMessageModel> formatMessage(List<CustomerMessageMongo> messages, String method) throws Exception {
        ArrayList<CustomerMessageModel> list = new ArrayList<>();
        String defaultMethod = "center";
        for (CustomerMessageMongo message : messages) {
            CustomerMessageModel cusMsg = new CustomerMessageModel();
            cusMsg.setId(message.id);
            String typeName = Constants.STRING_EMPTY;
            String typeIcon = Constants.STRING_EMPTY;
            String tip = Constants.STRING_EMPTY;
            String backColor = Constants.STRING_EMPTY;
            String date = method.equals(defaultMethod) ? DateUtil.format(message.getCreated(), Constants.TIME_HOUR_MINUTE_FORMAT) : DateUtil.format(message.getCreated(), Constants.DATE_TIME_FORMAT);
            CustomerMessageTypeMongo msgType = customerMessageTypeMongoDao.getById(message.getCustomerMessageTypeId());
            if (null != msgType) {
                typeName = msgType.getName();
                typeIcon = msgType.getIcon();
            }
            CustomerMessageTipsMongo msgTips = customerMessageTipsMongoDao.getByIdentification(message.getTipsIdentification());
            if (null != msgTips) {
                tip = msgTips.getName();
                backColor = msgTips.getBackColor();
            }

            cusMsg.setTitle(message.getTitle());
            cusMsg.setContent(message.getContent());
            cusMsg.setType(message.getType());
            cusMsg.setMessageTypeName(typeName);
            cusMsg.setMessageTypeIcon(typeIcon);
            cusMsg.setTips(tip);
            cusMsg.setBackgroundColor(backColor);
            cusMsg.setCreated(date);
            list.add(cusMsg);
        }
        return list;
    }
    //endregion
}