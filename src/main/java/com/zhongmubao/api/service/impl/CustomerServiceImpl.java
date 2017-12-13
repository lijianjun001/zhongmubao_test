package com.zhongmubao.api.service.impl;

import com.zhongmubao.api.config.Constants;
import com.zhongmubao.api.config.enmu.RealNameStatus;
import com.zhongmubao.api.dao.CustomerHFDao;
import com.zhongmubao.api.dao.CustomerSinaDao;
import com.zhongmubao.api.dto.request.my.RealNameRequestModel;
import com.zhongmubao.api.dto.response.my.RealNameViewModel;
import com.zhongmubao.api.entity.Customer;
import com.zhongmubao.api.entity.CustomerHF;
import com.zhongmubao.api.entity.CustomerSina;
import com.zhongmubao.api.mongo.dao.CustomerHFIndexMongoDao;
import com.zhongmubao.api.mongo.entity.CustomerHFIndexMongo;
import com.zhongmubao.api.service.CustomerService;
import com.zhongmubao.api.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

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

    @Autowired
    public CustomerServiceImpl(CustomerHFDao customerHFDao, CustomerSinaDao customerSinaDao, CustomerHFIndexMongoDao customerHFIndexMongoDao) {
        this.customerHFDao = customerHFDao;
        this.customerSinaDao = customerSinaDao;
        this.customerHFIndexMongoDao = customerHFIndexMongoDao;
    }

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
            //不删除恒显示
            //customerHFIndexMongoDao.delete(customerHFIndexMongo);
        }

        if (customer.getCreated().getTime() > (new SimpleDateFormat(dateFormat).parse(dateStr)).getTime()) {
            realNameViewModel.setCustomerType("X");
            ishf = true;
        }
        if (!ishf && customerSina == null) {
            ishf = true;
        }
        if (!ishf && customerHF != null) {
            ishf = true;
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

}
