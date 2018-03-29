package com.zhongmubao.api.service.impl;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhongmubao.api.config.Constants;
import com.zhongmubao.api.config.ResultStatus;
import com.zhongmubao.api.config.enmu.CustomerMessageTips;
import com.zhongmubao.api.config.enmu.CustomerMessageType;
import com.zhongmubao.api.config.enmu.CustomerMessageIcon;
import com.zhongmubao.api.dto.request.message.DetailRequestModel;
import com.zhongmubao.api.dto.request.message.ListRequestModel;
import com.zhongmubao.api.dto.response.message.*;
import com.zhongmubao.api.entity.Customer;
import com.zhongmubao.api.exception.ApiException;
import com.zhongmubao.api.mongo.dao.CustomerMessageMongoDao;
import com.zhongmubao.api.mongo.dao.CustomerMessageReadMongoDao;
import com.zhongmubao.api.mongo.entity.CustomerMessageMongo;
import com.zhongmubao.api.mongo.entity.CustomerMessageReadMongo;
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
    private final CustomerMessageReadMongoDao customerMessageReadMongoDao;

    @Autowired
    public MessageServiceImpl(CustomerMessageMongoDao customerMessageMongoDao, CustomerMessageReadMongoDao customerMessageReadMongoDao) {
        this.customerMessageMongoDao = customerMessageMongoDao;
        this.customerMessageReadMongoDao = customerMessageReadMongoDao;
    }


    //region 新消息中心
    @Override
    public NewMessageCountViewModel count(Customer customer) throws Exception {
        int customerId = customer == null ? 0 : customer.getId();
        long count = customerId > 0 ? customerMessageMongoDao.countByCustomerIdAndIsRead(customerId, false) : 0;
        //获取发送给所有人的消息
        List<CustomerMessageMongo> list = customerMessageMongoDao.getListByCustomerIdIsRead(0, false);
        for (CustomerMessageMongo message : list) {
            //不统计发标公告的历史
            if (message.getType().equals(CustomerMessageType.OPEN_PROJECT.getName()) && message.getTipsId() == CustomerMessageTips.HISTORY.getKey()) {
                continue;
            }
            CustomerMessageReadMongo readMongo = customerMessageReadMongoDao.getByCustomerIdAndMessageId(customerId, message.id);
            if (null == readMongo) {
                count = count + 1;
            }
        }

        return new NewMessageCountViewModel((int) count);
    }

    @Override
    public CenterViewModel center(Customer customer) throws Exception {
        if (customer == null) {
            customer = new Customer();
        }
        int customerId = customer.getId();

        Date now = new Date();
        int defaultPageSize = 3;
        int personPageSize = 3;
        if (customerId > 0) {
            int temp = (int) customerMessageMongoDao.countByCustomerIdAndIsRead(customerId, false);
            personPageSize = temp > personPageSize ? temp : personPageSize;
        }

        String method = "center";
        CenterViewModel viewModel = new CenterViewModel();

        List<CustomerMessageMongo> projectMessages = customerMessageMongoDao.getListByCustomerIdAndTypeLimitSize(customerId, defaultPageSize, CustomerMessageType.OPEN_PROJECT.getName());
        List<CustomerMessageMongo> systemMessages = customerMessageMongoDao.getListByCustomerIdAndTypeLimitSize(customerId, defaultPageSize, CustomerMessageType.SYSTEM_MESSAGE.getName());
        List<CustomerMessageMongo> personMessages = customerMessageMongoDao.getListByCustomerIdAndTypeLimitSize(customerId, personPageSize, CustomerMessageType.PERSON_MESSAGE.getName());

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
        if (ArrayUtil.isNull(systemMessages)) {
            viewModel.setSystemList(formatMessage(customer, systemMessages, method));
        }
        if (ArrayUtil.isNull(personMessages)) {
            viewModel.setPersonList(formatMessage(customer, personMessages, method));
        }
        return viewModel;
    }

    @Override
    public ListViewModel list(Customer customer, ListRequestModel model) throws Exception {
        if (model == null) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        if (customer == null) {
            customer = new Customer();
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
        if (customer == null) {
            customer = new Customer();
        }
        CustomerMessageMongo message = customerMessageMongoDao.getById(model.getId());
        if (null == message) {
            return new DetailViewModel();
        }
        if (message.getCustomerId() > 0 && customer.getId() != message.getCustomerId()) {
            throw new ApiException(ResultStatus.INVALID_NEWSDETAIL_CODE_ERROR);
        }

        Date now = new Date();
        String title = message.getTitle();
        String text = DateUtil.format(message.getCreated(), Constants.DATETIME_FORMAT_CHINA);
        String platform = model.getPlatform();
        String domain = ApiUtil.getDomainByPlatform(platform);
        String content = message.getContent().replace(Constants.DOMAIN_PLACEHOLDER, domain).replace(Constants.RESOURCE, Constants.RESOURES_ADDRESS_IMAGES).replace(Constants.PLATFROM, platform).replace(Constants.MESSAGEID, message.id);
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

        CustomerMessageMongo customerMessageMongo = customerMessageMongoDao.getByCustomerIdAndTipsId(customerId, CustomerMessageTips.HOME_PAGE.getKey());

        IndexLayerViewModel indexLayerViewModel = new IndexLayerViewModel();
        if (null != customerMessageMongo) {
            String domain = ApiUtil.getDomainByPlatform(platform);
            CustomerMessageModel customerMessageModel = new CustomerMessageModel();
            customerMessageModel.setId(customerMessageMongo.id);
            customerMessageModel.setTitle(customerMessageMongo.getTitle());
            customerMessageModel.setContent(customerMessageMongo.getContent().replace(Constants.DOMAIN_PLACEHOLDER, domain).replace(Constants.RESOURCE, Constants.RESOURES_ADDRESS_IMAGES).replace(Constants.PLATFROM, platform).replace(Constants.MESSAGEID, customerMessageMongo.id));
            indexLayerViewModel.setCustomerMessageModel(customerMessageModel);
        }

        return indexLayerViewModel;
    }


    @Override
    public void read(Customer customer, String id) throws Exception {
        if (StringUtil.isNullOrEmpty(id)) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        CustomerMessageMongo message = customerMessageMongoDao.getById(id);
        setRead(customer, message);
    }

    /**
     * 设置消息已读 TODO:mq
     *
     * @param customer 客户
     * @param message  message
     * @throws Exception Exception
     */

    private void setRead(Customer customer, CustomerMessageMongo message) throws Exception {
        if (null == message) {
            return;
        }
        if (message.getCustomerId() <= 0) {
            CustomerMessageReadMongo readMongo = customerMessageReadMongoDao.getByCustomerIdAndMessageId(customer.getId(), message.id);
            if (readMongo == null) {
                readMongo = new CustomerMessageReadMongo();
                readMongo.setCustomerId(customer.getId());
                readMongo.setMessageId(message.id);
                readMongo.setCreated(DateUtil.formatMongo(new Date()));
                customerMessageReadMongoDao.add(readMongo);
            }
        } else {
            if (!message.getRead() && message.getCustomerId() == customer.getId()) {
                if (message.getTipsId() == CustomerMessageTips.NEW.getKey() || message.getTipsId() == CustomerMessageTips.HOME_PAGE.getKey()) {
                    // 去掉lable[新]
                    message.setTipsId(CustomerMessageTips.DEFAULT.getKey());
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
                String sellout = "01";
                if (message.getRead()) {
                    sellout = "00";
                } else if (remark.equals(DateUtil.getWeekSection())) {
                    int weekNum = DateUtil.getWeekOfDateNumber(now);
                    if (StringUtil.convartWeekToNumber(plan.getDay()) < weekNum) {
                        sellout = "00";
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
        Date now = new Date();
        for (CustomerMessageMongo message : messages) {
            CustomerMessageModel cusMsg = new CustomerMessageModel();
            cusMsg.setId(message.id);
            String typeName = Constants.STRING_EMPTY;
            String typeIcon;
            String tip;
            String backColor;
            String date = method.equals(defaultMethod) ? DateUtil.format(message.getCreated(), Constants.TIME_HOUR_MINUTE_FORMAT) : DateUtil.format(message.getCreated(), Constants.DATE_TIME_FORMAT);

            if (message.getTipsId() == CustomerMessageTips.HOME_PAGE.getKey()) {
                if (message.getCustomerId() <= 0) {
                    CustomerMessageReadMongo readMongo = customerMessageReadMongoDao.getByCustomerIdAndMessageId(customer.getId(), message.id);
                    if (null != readMongo) {
                        message.setTipsId(CustomerMessageTips.DEFAULT.getKey());
                    } else {
                        message.setTipsId(CustomerMessageTips.NEW.getKey());
                    }
                } else {
                    if (!message.getRead()) {
                        message.setTipsId(CustomerMessageTips.NEW.getKey());
                    } else {
                        message.setTipsId(CustomerMessageTips.DEFAULT.getKey());
                    }
                }
            } else {
                if (message.getCustomerId() <= 0 && !message.getRead()) {
                    CustomerMessageReadMongo readMongo = customerMessageReadMongoDao.getByCustomerIdAndMessageId(customer.getId(), message.id);
                    if (null != readMongo && (!message.getType().equals(CustomerMessageType.OPEN_PROJECT.getName()))) {
                        message.setTipsId(CustomerMessageTips.DEFAULT.getKey());
                    }
                }
            }
            typeIcon = CustomerMessageIcon.formart(message.getIcon());
            tip = CustomerMessageTips.formartName(message.getTipsId());
            backColor = CustomerMessageTips.formartColor(message.getTipsId());
            String title = message.getTitle();
            if (CustomerMessageType.OPEN_PROJECT.getName().equals(message.getType())) {
                try {
                    // 第十三周开标公告（03月26日-04月01日）
                    String splitStr = "-";
                    int weekInt = DateUtil.getYearWeek(DateUtil.formatDefault(now));
                    String weekStr = NumberUtil.numberToChina(weekInt);
                    if (weekStr.startsWith("一")) {
                        // 一十三 改为 十三
                        weekStr = weekStr.substring(1);
                    }
                    String pre = "第" + weekStr + "周开标计划";
                    String[] plans = title.split(splitStr);
                    String leftBracket = "（";
                    String weekStart = plans[0].substring(5);
                    String weekEnd = plans[1].substring(5);
                    String rightBracket = "）";
                    title = pre + leftBracket + weekStart + splitStr + weekEnd + rightBracket;
                } catch (Exception ignored) {

                }
            }

            cusMsg.setTitle(title);
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
