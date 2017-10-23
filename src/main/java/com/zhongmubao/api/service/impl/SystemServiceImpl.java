package com.zhongmubao.api.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhongmubao.api.cache.RedisCache;
import com.zhongmubao.api.config.Constants;
import com.zhongmubao.api.config.ResultStatus;
import com.zhongmubao.api.config.SmsTemplate;
import com.zhongmubao.api.config.enmu.SmsType;
import com.zhongmubao.api.config.enmu.SystemPushType;
import com.zhongmubao.api.dao.CustomerDao;
import com.zhongmubao.api.dao.ExtNoticeDao;
import com.zhongmubao.api.dao.SystemDistrictDao;
import com.zhongmubao.api.dto.request.address.SystemDistrictRequestModel;
import com.zhongmubao.api.dto.request.PageIndexRequestModel;
import com.zhongmubao.api.dto.request.SendSmsCodeRequestModel;
import com.zhongmubao.api.dto.response.address.ListSystemDistrictModel;
import com.zhongmubao.api.dto.response.address.SystemDistrictViewModel;
import com.zhongmubao.api.dto.response.Notice.PageNoticeModel;
import com.zhongmubao.api.dto.response.Notice.PageNoticeViewModel;
import com.zhongmubao.api.entity.Customer;
import com.zhongmubao.api.entity.ExtNotice;
import com.zhongmubao.api.entity.SystemDistrict;
import com.zhongmubao.api.exception.ApiException;
import com.zhongmubao.api.mongo.dao.SystemSmsLogMongoDao;
import com.zhongmubao.api.mongo.entity.SystemSmsLogMongo;
import com.zhongmubao.api.service.BaseService;
import com.zhongmubao.api.service.SystemService;
import com.zhongmubao.api.util.DateUtil;
import com.zhongmubao.api.util.MathUtil;
import com.zhongmubao.api.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class SystemServiceImpl extends BaseService implements SystemService {

//    private final ExtNoticeMongoDao extNoticeMongoDao;

    private final ExtNoticeDao extNoticeDao;
    private final SystemDistrictDao systemDistrictDao;
    private final CustomerDao customerDao;
    private final RedisCache redisCache;
    private final SystemSmsLogMongoDao systemSmsLogMongoDao;

    public SystemServiceImpl(ExtNoticeDao extNoticeDao, SystemDistrictDao systemDistrictDao, CustomerDao customerDao, RedisCache redisCache, SystemSmsLogMongoDao systemSmsLogMongoDao) {
//        this.extNoticeMongoDao = extNoticeMongoDao;
        this.extNoticeDao = extNoticeDao;
        this.systemDistrictDao = systemDistrictDao;
        this.customerDao = customerDao;
        this.redisCache = redisCache;
        this.systemSmsLogMongoDao = systemSmsLogMongoDao;
    }

    //region 系统通知
    /**
     * @param requestModel
     * @return 通知公告
     * @throws Exception
     * @author 米立林
     */
    @Override
    public PageNoticeModel pageNotice(PageIndexRequestModel requestModel) throws Exception {
        if (null == requestModel) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        PageHelper.startPage(requestModel.getPageIndex(), Constants.PAGE_SIZE);
        Page<ExtNotice> page = extNoticeDao.pageEffectiveExtNotice();
        int pages = page.getPages();
        List<PageNoticeViewModel> list = page.getResult().stream().map(
                en -> new PageNoticeViewModel(
                        en.getId(),
                        en.getPic(),
                        en.getTitle(),
                        en.getContent(),
                        DateUtil.format(en.getCreated(), Constants.DATE_FORMAT)
                ))
                .collect(Collectors.toList());
        PageHelper.clearPage();

        return new PageNoticeModel(pages, list);
    }
    //endregion

    //region 系统省市区

    /**
     * 获取省市区By ParentCode
     *
     * @param requestModel 取ParentCode
     * @return SystemDistrict列表
     * @throws Exception
     */
    @Override
    public ListSystemDistrictModel getSystemDistrictList(SystemDistrictRequestModel requestModel) throws Exception {
        if (null == requestModel) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        List<SystemDistrict> systemDistrictList = redisCache.getDistrictList();
        if (null == systemDistrictList) {
            List<SystemDistrict> districts = systemDistrictDao.pagerSystemDistrictList(0, 4000);
            redisCache.savaDistrictList(districts);
            systemDistrictList = districts;
        }

        // 筛选过滤
        Predicate<SystemDistrict> parentCodeFilter;
        String parentCode = requestModel.getParentCode();
        if (StringUtil.isNullOrEmpty(parentCode)) {
            // 取所有省
            parentCodeFilter = (code) -> (code.getParentCode() == null);
        } else {
            parentCodeFilter = (code) -> (code.getParentCode() != null && code.getParentCode().equals(parentCode));
        }

        List<SystemDistrictViewModel> list = systemDistrictList.stream()
                .filter(parentCodeFilter).map(
                        en -> new SystemDistrictViewModel(
                                en.getCode(),
                                en.getParentCode(),
                                en.getName()
                        ))
                .collect(Collectors.toList());

        return new ListSystemDistrictModel(list);
    }

    //endregion

    /**
     * 发送验证码
     *
     * @param model 请求参数
     * @throws Exception
     * @author 米立林 2017-10-09
     */
    @Override
    public void sendSmsCode(Customer customer, SendSmsCodeRequestModel model) throws Exception {
        if (null == model || null == customer) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        // 1、校验用户
        if (!customer.getPhone().equals(model.getPhone())) {
            throw new ApiException(ResultStatus.PARAMETER_ERROR);
        }
        // 2、根据类型获取短信验证码范文
        SystemSmsLogMongo smsLog=new SystemSmsLogMongo();
        String title = "";
        String content = "";
        String code = Integer.toString(MathUtil.random(100000, 999999));
        if (SmsType.VERIFICATION.getName().equals(model.getType())) {
            title = "重置密码";
            content = SmsTemplate.resetPasswordTemplate.replace("{0}", code);
            smsLog.setType("00");
        } else if (SmsType.REDEEM_PWD.getName().equals(model.getType())) {
            title = "重置赎回密码";
            content = SmsTemplate.resetRedeemPasswordTemplate.replace("{0}", code);
            smsLog.setType("02");
        }
        // 添加到短信推送SystemPushMongo
        push(customer, title, content, SystemPushType.SMS);
        // 添加到SystemSmsLogMongo
        Date mongoNow = DateUtil.formatMongo(new Date());
        smsLog.setSqlId(0);
        smsLog.setPhone(customer.getPhone());
        smsLog.setCode(code);
        smsLog.setMessage(content);
        smsLog.setExpired(DateUtil.addMinute(mongoNow,30));
        smsLog.setCreated(mongoNow);
        smsLog.setAsyncType(0);
        systemSmsLogMongoDao.add(smsLog);
    }
}
