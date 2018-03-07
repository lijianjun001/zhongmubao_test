package com.zhongmubao.api.service.impl;

import com.zhongmubao.api.config.Constants;
import com.zhongmubao.api.config.ResultStatus;
import com.zhongmubao.api.config.enmu.*;
import com.zhongmubao.api.dao.*;
import com.zhongmubao.api.dto.request.customer.RecommendInfoRequestModel;
import com.zhongmubao.api.dto.request.customer.RegisterRequestModel;
import com.zhongmubao.api.dto.request.my.RealNameRequestModel;
import com.zhongmubao.api.dto.response.customer.RecommendInfoViewModel;
import com.zhongmubao.api.dto.response.my.RealNameViewModel;
import com.zhongmubao.api.entity.Customer;
import com.zhongmubao.api.entity.CustomerHF;
import com.zhongmubao.api.entity.CustomerSina;
import com.zhongmubao.api.exception.ApiException;
import com.zhongmubao.api.mongo.dao.CustomerHFIndexMongoDao;
import com.zhongmubao.api.mongo.dao.SystemSmsLogMongoDao;
import com.zhongmubao.api.mongo.entity.CustomerHFIndexMongo;
import com.zhongmubao.api.mongo.entity.SystemSmsLogMongo;
import com.zhongmubao.api.service.CustomerService;
import com.zhongmubao.api.util.ApiUtil;
import com.zhongmubao.api.util.DateUtil;
import com.zhongmubao.api.util.RegExpMatcher;
import com.zhongmubao.api.util.SecurityUtil;
import com.zhongmubao.api.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Customer
 *
 * @author xy
 */
@Service
public class CustomerServiceImpl extends BaseService implements CustomerService {
    private final CustomerHFDao customerHFDao;
    private final CustomerSinaDao customerSinaDao;
    private final CustomerHFIndexMongoDao customerHFIndexMongoDao;
    private final CustomerDao customerDao;
    private final SystemSmsLogMongoDao systemSmsLogMongoDao;

    @Autowired
    public CustomerServiceImpl(CustomerHFDao customerHFDao, CustomerSinaDao customerSinaDao, CustomerHFIndexMongoDao customerHFIndexMongoDao, CustomerDao customerDao, SystemSmsLogMongoDao systemSmsLogMongoDao) {
        this.customerHFDao = customerHFDao;
        this.customerSinaDao = customerSinaDao;
        this.customerHFIndexMongoDao = customerHFIndexMongoDao;
        this.customerDao = customerDao;
        this.systemSmsLogMongoDao = systemSmsLogMongoDao;
    }

    //region 是否实名
    @Override
    public RealNameViewModel choosePaymentRealName(Customer customer, RealNameRequestModel model) throws Exception {

        RealNameViewModel realNameViewModel = new RealNameViewModel();
        CustomerHF customerHF = customerHFDao.getCustomerHFById(customer.getId());
        CustomerSina customerSina = customerSinaDao.getCustomerSinaById(customer.getId());
        realNameViewModel.setCenterShowHFRealName(false);
        realNameViewModel.setIndexShowHFRealName(false);
        realNameViewModel.setCenterShowSinaRealName(false);
        realNameViewModel.setIndexShowSinaRealName(false);
        realNameViewModel.setCustomerType("L");
        boolean ishf = false;
        String dateFormat = "yyyy-MM-dd HH:mm:ss";
        String dateStr = "2017-12-01 00:00:00";

        //首页显示
        CustomerHFIndexMongo customerHFIndexMongo = customerHFIndexMongoDao.getByCustomerId(customer.getId());
        if (null != customerHFIndexMongo) {
            if (customerHF == null) {
                realNameViewModel.setCenterShowHFRealName(true);
                realNameViewModel.setIndexShowHFRealName(true);
            } else {
                if (!(customerHF.getIsBandCard() && !StringUtil.isNullOrEmpty(customerHF.getUsrCustId()))) {
                    realNameViewModel.setCenterShowHFRealName(true);
                    realNameViewModel.setIndexShowHFRealName(true);
                }
            }
        }

        if (customer.getCreated().getTime() > (new SimpleDateFormat(dateFormat).parse(dateStr)).getTime()) {
            realNameViewModel.setCustomerType("X");
            ishf = true;
        }
        if (!ishf && customerSina == null) {
            ishf = true;
        }
        if (!ishf && customerHF != null) {
            if (customerHF.getIsBandCard() && !StringUtil.isNullOrEmpty(customerHF.getUsrCustId())) {
                ishf = true;
            }
        }

        //显示汇付 or 新浪
        if (ishf) {
            if (customerHF == null) {
                realNameViewModel.setCenterShowHFRealName(true);
                realNameViewModel.setIndexShowHFRealName(true);
                realNameViewModel.setRealName(RealNameStatus.HFF.getName());
                realNameViewModel.setRealNameSatus(RealNameStatus.HFF.getStatus());
                realNameViewModel.setRealNameType(RealNameStatus.HFF.getType());
                realNameViewModel.setRealNameImg(Constants.RESOURES_ADDRESS_IMAGES + RealNameStatus.HFF.getImg());
                return realNameViewModel;
            }
            if (!StringUtil.isNullOrEmpty(customerHF.getUsrCustId()) && customerHF.getIsBandCard() && customerHF.getIsBosAcct()) {
                realNameViewModel.setRealName(RealNameStatus.HFS.getName());
                realNameViewModel.setRealNameSatus(RealNameStatus.HFS.getStatus());
                realNameViewModel.setRealNameType(RealNameStatus.HFS.getType());
                realNameViewModel.setRealNameImg(Constants.RESOURES_ADDRESS_IMAGES + RealNameStatus.HFS.getImg());
            } else {
                if (!StringUtil.isNullOrEmpty(customerHF.getUsrCustId()) && !customerHF.getIsBosAcct()) {
                    //未激活
                    realNameViewModel.setCenterShowHFRealName(true);
                    realNameViewModel.setIndexShowHFRealName(true);
                    realNameViewModel.setRealName(RealNameStatus.HFB.getName());
                    realNameViewModel.setRealNameSatus(RealNameStatus.HFB.getStatus());
                    realNameViewModel.setRealNameType(RealNameStatus.HFB.getType());
                    realNameViewModel.setRealNameImg(Constants.RESOURES_ADDRESS_IMAGES + RealNameStatus.HFB.getImg());
                } else {
                }
                realNameViewModel.setCenterShowHFRealName(true);
                realNameViewModel.setIndexShowHFRealName(true);
                realNameViewModel.setRealName(RealNameStatus.HFF.getName());
                realNameViewModel.setRealNameSatus(RealNameStatus.HFF.getStatus());
                realNameViewModel.setRealNameType(RealNameStatus.HFF.getType());
                realNameViewModel.setRealNameImg(Constants.RESOURES_ADDRESS_IMAGES + RealNameStatus.HFF.getImg());
            }

        } else {

            if (customerSina == null) {
                realNameViewModel.setCenterShowSinaRealName(true);
                realNameViewModel.setIndexShowSinaRealName(true);
                realNameViewModel.setRealName(RealNameStatus.XLF.getName());
                realNameViewModel.setRealNameSatus(RealNameStatus.XLF.getStatus());
                realNameViewModel.setRealNameType(RealNameStatus.XLF.getType());
                realNameViewModel.setRealNameImg(Constants.RESOURES_ADDRESS_IMAGES + RealNameStatus.XLF.getImg());
                return realNameViewModel;
            }
            if (customerSina.getIsRealName()) {
                realNameViewModel.setRealName(RealNameStatus.XLS.getName());
                realNameViewModel.setRealNameSatus(RealNameStatus.XLS.getStatus());
                realNameViewModel.setRealNameType(RealNameStatus.XLS.getType());
                realNameViewModel.setRealNameImg(Constants.RESOURES_ADDRESS_IMAGES + RealNameStatus.XLS.getImg());
            } else {
                realNameViewModel.setCenterShowSinaRealName(true);
                realNameViewModel.setIndexShowSinaRealName(true);
                realNameViewModel.setRealName(RealNameStatus.XLF.getName());
                realNameViewModel.setRealNameSatus(RealNameStatus.XLF.getStatus());
                realNameViewModel.setRealNameType(RealNameStatus.XLF.getType());
                realNameViewModel.setRealNameImg(Constants.RESOURES_ADDRESS_IMAGES + RealNameStatus.XLF.getImg());
            }

        }
        return realNameViewModel;
    }
    //endregion


    @Override
    public void register(RegisterRequestModel register) throws Exception {
        //region verification
        if (register == null) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        if (StringUtil.isNullOrEmpty(register.getAccount()) || StringUtil.isNullOrEmpty(register.getPassword()) || StringUtil.isNullOrEmpty(register.getReferenceCode())) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        if (StringUtil.isNullOrEmpty(register.getSmsCode())) {
            throw new ApiException(ResultStatus.PARAMETER_CODE_ERROR);
        }
        if (!RegExpMatcher.matcherMobile(register.getAccount())) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        if (register.getPassword().length() < 6 || register.getPassword().length() > 16) {
            throw new ApiException(ResultStatus.USER_PASSWORD_LENGTH_ERROR);
        }
        Customer regCustomer = customerDao.getCustomerByAccount(register.getAccount());
        if (null != regCustomer || regCustomer.getId() > 0) {
            throw new ApiException(ResultStatus.USER_EXISTS_ERROR);
        }
        //endregion
        Date now = new Date();
        int refrenceId = ApiUtil.dInviteCode(register.getReferenceCode());
        Customer refCustomer = customerDao.getCustomerById(refrenceId);
        if (refCustomer == null) {
            refrenceId = 0;
        }
        SystemSmsLogMongo systemSms = systemSmsLogMongoDao.getFirstOrderByCreatedDesc(register.getAccount(), SmsType.VERIFICATION.getName());
        if (systemSms == null || !register.getSmsCode().equals(systemSms.getCode())) {
            throw new ApiException(ResultStatus.PARAMETER_CODE_ERROR);
        }
        if (DateUtil.subDateOfSecond(systemSms.getExpired(), now) <= 0) {
            throw new ApiException(ResultStatus.PARAMETER_CODE_INVALID);
        }
        systemSms.setExpired(now);
        systemSmsLogMongoDao.update(systemSms);

        // Sign 生成规则，手机号 md5 16位
        String sign = SecurityUtil.encrypt16(register.getAccount());
        Customer customer = new Customer();
        customer.setAccount(register.getAccount());
        customer.setPassword(register.getPassword());
        customer.setSign(sign);
        customer.setNickName(Constants.EMPTY_STRING);
        customer.setName(Constants.EMPTY_STRING);
        customer.setPhone(register.getAccount());
        customer.setEmail(Constants.EMPTY_STRING);
        customer.setOpenId(Constants.EMPTY_STRING);
        customer.setCardType(Constants.EMPTY_STRING);
        customer.setCardNo(Constants.EMPTY_STRING);
        customer.setPhone(Constants.DEFAULT_PHOTO);
        customer.setReferenceId(refrenceId);
        customer.setIsGrantLibrary(false);
        customer.setCount(0);
        customer.setPlatform(register.getPlatform());
        customer.setRegisterIP(Constants.EMPTY_STRING);
        customer.setRegisterAddredss(Constants.EMPTY_STRING);
        customer.setDeleted(false);
        customer.setCreated(now);
        customer.setModified(now);
        customer.setRedeemPassword(Constants.EMPTY_STRING);
        customer.setEnabledFingerprint(false);
        customer.setHadaCount(0);
        customer.setIsAutoRedeem(false);
        customer.setIsSetPassword(true);
        customerDao.insert(customer);
    }

    @Override
    public RecommendInfoViewModel recommendInfo(RecommendInfoRequestModel register) throws Exception {
        if (register == null || StringUtil.isNullOrEmpty(register.getCode())) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        int id = ApiUtil.dInviteCode(register.getCode());
        Customer customer = customerDao.getCustomerById(id);
        if (null == customer) {
            throw new ApiException(ResultStatus.PARAMETER_ERROR);
        }

        String photo = ApiUtil.formartPhoto(customer.getPhoto());

        RecommendInfoViewModel viewModel = new RecommendInfoViewModel();
        viewModel.setNickName(customer.getNickName());
        viewModel.setPhone(photo);

        return viewModel;
    }
}
