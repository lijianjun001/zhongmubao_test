package com.zhongmubao.api.service.impl;

import com.zhongmubao.api.config.Constants;
import com.zhongmubao.api.config.ResultStatus;
import com.zhongmubao.api.config.enmu.*;
import com.zhongmubao.api.dao.*;
import com.zhongmubao.api.dto.request.customer.AccountExistRequestModel;
import com.zhongmubao.api.dto.request.customer.RecommendInfoRequestModel;
import com.zhongmubao.api.dto.request.customer.RegisterRequestModel;
import com.zhongmubao.api.dto.request.my.RealNameRequestModel;
import com.zhongmubao.api.dto.response.customer.AccountExistViewModel;
import com.zhongmubao.api.dto.response.customer.RecommendInfoViewModel;
import com.zhongmubao.api.dto.response.customer.RegisterViewModel;
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
    public RegisterViewModel register(RegisterRequestModel register) throws Exception {
        Date now = new Date();
        //region verification
        if (register == null) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        int passwordMinLength = 6;
        int passwordMaxLength = 16;
        String account = register.getAccount();
        String password = register.getPassword();
        String platform = register.getPlatform();

        if (StringUtil.isNullOrEmpty(account)) {
            throw new ApiException(ResultStatus.ACCOUNT_EMPTY_ERROR);
        }
        if (StringUtil.isNullOrEmpty(password)) {
            throw new ApiException(ResultStatus.PASSWORD_EMPTY_ERROR);
        }
        if (!RegExpMatcher.matcherMobile(account)) {
            throw new ApiException(ResultStatus.INVALID_PHONE_ERROR);
        }
        if (register.getSmsCode() <= 0) {
            throw new ApiException(ResultStatus.PARAMETER_CODE_ERROR);
        }
        if (password.length() < passwordMinLength || password.length() > passwordMaxLength) {
            throw new ApiException(ResultStatus.USER_PASSWORD_LENGTH_ERROR);
        }
        Customer regCustomer = customerDao.getCustomerByAccount(account);
        if (null != regCustomer) {
            throw new ApiException(ResultStatus.USER_EXISTS_ERROR);
        }
        //endregion

        int refrenceId = ApiUtil.dInviteCode(register.getReferenceCode());
        Customer refCustomer = customerDao.getCustomerById(refrenceId);
        if (refCustomer == null) {
            refrenceId = 0;
        }
        SystemSmsLogMongo systemSms = systemSmsLogMongoDao.getFirstOrderByCreatedDesc(account, SmsType.VERIFICATION.getName());
        if (systemSms == null || register.getSmsCode() != systemSms.getCode()) {
            throw new ApiException(ResultStatus.PARAMETER_CODE_ERROR);
        }
        if (systemSms.getExpired().getTime() < now.getTime()) {
            throw new ApiException(ResultStatus.PARAMETER_CODE_INVALID);
        }

        systemSms.setExpired(DateUtil.addDay(now, -365));
        systemSmsLogMongoDao.update(systemSms);

        // Sign 生成规则，手机号 md5 16位
        String sign = SecurityUtil.encrypt16(account);

        Customer customer = new Customer();
        customer.setAccount(account);
        customer.setPassword(ApiUtil.encrypt(password));
        customer.setSign(sign);
        customer.setNickName(Constants.CATTLEMAN);
        customer.setPhone(account);
        customer.setPhoto(Constants.DEFAULT_PHOTO);
        customer.setReferenceId(refrenceId);
        customer.setPlatform(platform);
        customer.setCreated(now);
        customer.setModified(now);
        customer.setIsSetPassword(true);
        customerDao.insert(customer);
        // 新手红包
        sendRedPackage(customer, RedPackageType.REGISTER, 8, DateUtil.addDay(now, 2), 2);
        sendRedPackage(customer, RedPackageType.REGISTER, 5, DateUtil.addDay(now, 7), 6);
        sendRedPackage(customer, RedPackageType.REGISTER, 2, DateUtil.addDay(now, 30), 17);

        RegisterViewModel viewModel = new RegisterViewModel();
        viewModel.setId(customer.getId());
        viewModel.setAccount(customer.getAccount());
        viewModel.setNickName(customer.getNickName());
        viewModel.setOpenId(customer.getOpenId());
        viewModel.setSign(customer.getSign());
        viewModel.setPhoto(customer.getPhoto());
        viewModel.setToken(setToken(platform, customer.getId()));

        return viewModel;
    }

    @Override
    public RecommendInfoViewModel recommendInfo(RecommendInfoRequestModel register) throws Exception {
        if (register == null) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        RecommendInfoViewModel viewModel = new RecommendInfoViewModel();
        int id = ApiUtil.dInviteCode(register.getCode());
        Customer customer = customerDao.getCustomerById(id);
        if (null == customer) {
            String nickName = "牧场主";
            String photo = ApiUtil.formartPhoto(Constants.EMPTY_STRING);
            viewModel.setNickName(nickName);
            viewModel.setPhoto(photo);
        } else {
            String photo = ApiUtil.formartPhoto(customer.getPhoto());
            viewModel.setNickName(customer.getNickName());
            viewModel.setPhoto(photo);
        }

        return viewModel;
    }

    @Override
    public AccountExistViewModel exist(AccountExistRequestModel model) throws Exception {
        if (model == null) {
            throw new ApiException(ResultStatus.PARAMETER_MISSING);
        }
        if (StringUtil.isNullOrEmpty(model.getAccount())) {
            throw new ApiException(ResultStatus.INVALID_PHONE_ERROR);
        }
        AccountExistViewModel viewModel = new AccountExistViewModel();
        Customer customer = customerDao.getCustomerByAccount(model.getAccount());
        viewModel.setExist(customer != null);
        return viewModel;
    }
}
