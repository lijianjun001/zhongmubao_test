package com.zhongmubao.api.service.impl;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhongmubao.api.config.Constants;
import com.zhongmubao.api.config.ResultStatus;
import com.zhongmubao.api.config.enmu.CustomerMessageType;
import com.zhongmubao.api.dto.request.message.DetailRequestModel;
import com.zhongmubao.api.dto.request.message.ListRequestModel;
import com.zhongmubao.api.dto.response.message.*;
import com.zhongmubao.api.entity.Customer;
import com.zhongmubao.api.exception.ApiException;
import com.zhongmubao.api.mongo.dao.CustomerMessageMongoDao;
import com.zhongmubao.api.mongo.dao.CustomerMessageReadMongoDao;
import com.zhongmubao.api.mongo.dao.CustomerMessageTipsMongoDao;
import com.zhongmubao.api.mongo.dao.CustomerMessageTypeMongoDao;
import com.zhongmubao.api.mongo.entity.CustomerMessageMongo;
import com.zhongmubao.api.mongo.entity.CustomerMessageReadMongo;
import com.zhongmubao.api.mongo.entity.CustomerMessageTipsMongo;
import com.zhongmubao.api.mongo.entity.CustomerMessageTypeMongo;
import com.zhongmubao.api.mongo.entity.base.PageModel;
import com.zhongmubao.api.service.MessageService;
import com.zhongmubao.api.util.*;
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
    private final CustomerMessageReadMongoDao customerMessageReadMongoDao;

    @Autowired
    public MessageServiceImpl(CustomerMessageMongoDao customerMessageMongoDao, CustomerMessageTypeMongoDao customerMessageTypeMongoDao, CustomerMessageTipsMongoDao customerMessageTipsMongoDao, CustomerMessageReadMongoDao customerMessageReadMongoDao) {
        this.customerMessageMongoDao = customerMessageMongoDao;
        this.customerMessageTypeMongoDao = customerMessageTypeMongoDao;
        this.customerMessageTipsMongoDao = customerMessageTipsMongoDao;
        this.customerMessageReadMongoDao = customerMessageReadMongoDao;
    }


    //region 新消息中心


    @Override
    public NewMessageCountViewModel count(Customer customer) throws Exception {
        int customerId = customer == null ? 0 : customer.getId();
        int newTipsIdentification = 2;
        long count = customerMessageMongoDao.countByCustoemrIdAndIsReadAndTipsIdentification(customerId, false, newTipsIdentification);
        //获取发送给所有人的消息
        List<CustomerMessageMongo> list = customerMessageMongoDao.getListByCustomerId(0);
        for (CustomerMessageMongo message : list) {
            CustomerMessageReadMongo readMongo = customerMessageReadMongoDao.getByCustoemrIdAndMessageId(customerId, message.id);
            if (null == readMongo) {
                count = count + 1;
            }
        }

        return new NewMessageCountViewModel((int) count);
    }

    @Override
    public CenterViewModel center(Customer customer) throws Exception {
        int customerId = customer == null ? 0 : customer.getId();

        Date now = new Date();
        int pagesize = 3;
        String method = "center";
        CenterViewModel viewModel = new CenterViewModel();

        List<CustomerMessageMongo> projectMessages = customerMessageMongoDao.getListByCustomerIdAndTypeLimitSize(customerId, pagesize, CustomerMessageType.OPEN_PROJECT.getName());
        List<CustomerMessageMongo> personMessages = customerMessageMongoDao.getListByCustomerIdAndTypeLimitSize(customerId, pagesize, CustomerMessageType.PERSON_MESSAGE.getName());
        List<CustomerMessageMongo> systemMessages = customerMessageMongoDao.getListByCustomerIdAndTypeLimitSize(customerId, pagesize, CustomerMessageType.SYSTEM_MESSAGE.getName());

        if (ArrayUtil.isNull(projectMessages)) {
            String weekSection = DateUtil.getWeekSection();
            Optional<CustomerMessageMongo> optionalCms = projectMessages.stream().filter(m -> m.getTitle().equals(weekSection)).findFirst();
            if (optionalCms.isPresent()) {
                CustomerMessageMongo project = optionalCms.get();
                projectMessages.clear();
                String curWeekPlan = "本周开标计划";
                project.setContent(Constants.STRING_EMPTY);
                project.setTitle(curWeekPlan);
                projectMessages.add(project);
                viewModel.setProjectList(formatMessage(customer, projectMessages, method));
            }
        }
        if (ArrayUtil.isNull(personMessages)) {
            viewModel.setPersonList(formatMessage(customer, personMessages, method));
        }
        if (ArrayUtil.isNull(systemMessages)) {
            viewModel.setSystemList(formatMessage(customer, systemMessages, method));
        }
        return viewModel;
    }

    @Override
    public ListViewModel list(Customer customer, ListRequestModel model) throws Exception {
        if (model == null) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        if (model.getPageIndex() <= 0) {
            model.setPageIndex(1);
        }
        String method = "list";
        PageModel<CustomerMessageMongo> pager = new PageModel<>();
        pager.setPageNo(model.getPageIndex());
        pager.setPageSize(Constants.PAGE_SIZE);
        pager = customerMessageMongoDao.pager(customer.getId(), pager, model.getType());

        ListViewModel viewModel = new ListViewModel();
        ArrayList<CustomerMessageModel> list = formatMessage(customer, pager.getDatas(), method);
        viewModel.setList(list);
        viewModel.setTotalPage(pager.getTotalPages());

        return viewModel;
    }

    @Override
    public DetailViewModel detail(Customer customer, DetailRequestModel model) throws Exception {
        if (model == null) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        CustomerMessageMongo message = customerMessageMongoDao.getById(model.getId());
        if (null == message) {
            return new DetailViewModel();
        }

        Date now = new Date();
        String title = message.getTitle();
        String text = DateUtil.format(message.getCreated(), Constants.DATETIME_FORMAT_CHINA);
        String platform = model.getPlatform();
        String domain = ApiUtil.getDomainByPlatform(platform);
        String content = message.getContent().replace(Constants.DOMAIN_PLACEHOLDER, domain).replace(Constants.RESOURCE, Constants.RESOURES_ADDRESS_IMAGES).replace(Constants.PLATFROM, platform);
        if (CustomerMessageType.OPEN_PROJECT.getName().equals(message.getType())) {
            ProjectMessageListViewModel projectViewModel = projectMessageDetail(model.getId());
            title = projectViewModel.getTitle();
            text = projectViewModel.getRemark();
            content = SerializeUtil.serialize(projectViewModel);
        }

        //设置消息已读
        setRead(customer, message);

        return new DetailViewModel(title, text, content);
    }


    @Override
    public IndexLayerViewModel indexLayer(Customer customer, String platform) throws Exception {

        int customerId = customer == null ? 0 : customer.getId();

        CustomerMessageTipsMongo customerMessageTipsMongo = customerMessageTipsMongoDao.getByIdentification(7);
        CustomerMessageMongo customerMessageMongo = customerMessageMongoDao.getByCustomerIdAndTipsIdentification(customerId, customerMessageTipsMongo.getIdentification());

        IndexLayerViewModel indexLayerViewModel = new IndexLayerViewModel();
        if (null != customerMessageMongo) {
            String domain = ApiUtil.getDomainByPlatform(platform);
            CustomerMessageModel customerMessageModel = new CustomerMessageModel();
            customerMessageModel.setId(customerMessageMongo.id);
            customerMessageModel.setTitle(customerMessageMongo.getTitle());
            customerMessageModel.setContent(customerMessageMongo.getContent().replace(Constants.DOMAIN_PLACEHOLDER, domain).replace(Constants.RESOURCE, Constants.RESOURES_ADDRESS_IMAGES).replace(Constants.PLATFROM, platform));
            indexLayerViewModel.setCustomerMessageModel(customerMessageModel);
        }

        return indexLayerViewModel;
    }


    @Override
    public void read(Customer customer, String id) throws Exception {
        CustomerMessageMongo message = customerMessageMongoDao.getByCustomerIdAndTipsIdentification(id, customer.getId());
        setRead(customer, message);
    }

    /**
     * 设置消息已读
     *
     * @param customer 客户
     * @param message  message
     * @throws Exception Exception
     */
    private void setRead(Customer customer, CustomerMessageMongo message) throws Exception {
        if (null == message) {
            return;
        }
        // 消息标签，2->表示为新消息,99->表示没有标签
        int newTipsId = 2;
        int noTipsId = 99;
        if (CustomerMessageType.SYSTEM_MESSAGE.getName().equals(message.getType())) {
            CustomerMessageReadMongo readMongo = customerMessageReadMongoDao.getByCustoemrIdAndMessageId(customer.getId(), message.id);
            if (readMongo == null) {
                readMongo = new CustomerMessageReadMongo();
                readMongo.setCustomerId(customer.getId());
                readMongo.setMessageId(message.id);
                readMongo.setCreated(DateUtil.formatMongo(new Date()));
                customerMessageReadMongoDao.add(readMongo);
            }
        } else {
            if (!message.getRead() && message.getCustomerId() == customer.getId()) {
                if (message.getTipsIdentification() == newTipsId) {
                    // 去掉lable[新]
                    message.setTipsIdentification(noTipsId);
                }
                message.setRead(true);
                customerMessageMongoDao.update(message);
            }
        }
    }

    /**
     * 开标消息详情单独处理
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
                if (remark.equals(DateUtil.getWeekSection())) {
                    String curWeek = DateUtil.getWeekOfDate(now);
                    if (week.contains(curWeek)) {
                        week = curDayString;
                    }
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
                } else {
                    if (!message.getRead()) {
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

    /**
     * 格式化消息数据
     *
     * @param messages 消息中心列表
     * @param method   方法来源，center or list
     * @return ArrayList<CustomerMessageModel>
     * @throws Exception Exception
     */
    private ArrayList<CustomerMessageModel> formatMessage(Customer customer, List<CustomerMessageMongo> messages, String method) throws Exception {
        ArrayList<CustomerMessageModel> list = new ArrayList<>();
        String defaultMethod = "center";
        String listMethod = "list";
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

            if (CustomerMessageType.SYSTEM_MESSAGE.getName().equals(message.getType())) {
                CustomerMessageReadMongo readMongo = customerMessageReadMongoDao.getByCustoemrIdAndMessageId(customer.getId(), message.id);
                if (readMongo != null) {
                    tip = Constants.STRING_EMPTY;
                    backColor = Constants.STRING_EMPTY;
                }
            }

            cusMsg.setTitle(message.getTitle());
            cusMsg.setContent(message.getContent());
            if (defaultMethod.equals(method) || listMethod.equals(method)) {
                if (message.getIsInside()) {
                    cusMsg.setContent(Constants.STRING_EMPTY);
                }
            }
            cusMsg.setType(message.getType());
            cusMsg.setInside(message.getIsInside());
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
