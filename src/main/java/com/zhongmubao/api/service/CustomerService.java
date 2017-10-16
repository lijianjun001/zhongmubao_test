package com.zhongmubao.api.service;

import com.zhongmubao.api.dto.Request.OnlyPrimaryIdRequestModel;
import com.zhongmubao.api.dto.Request.*;
import com.zhongmubao.api.dto.Request.Address.CustomerAddressRequestModel;
import com.zhongmubao.api.dto.Request.Address.UpdateCustomerAddressRequestModel;
import com.zhongmubao.api.dto.Response.Address.CustomerAddressResponseModel;
import com.zhongmubao.api.dto.Response.Ext.PageExtRedPackageModel;
import com.zhongmubao.api.dto.Response.Sign.MyGiftCardModel;
import com.zhongmubao.api.dto.Response.Sign.SignList.PageSignGiftModel;
import com.zhongmubao.api.dto.Response.Sign.SignModel;
import com.zhongmubao.api.dto.Response.Sign.SignPackageList.PageSignPackageModel;
import com.zhongmubao.api.entity.Customer;

public interface CustomerService {

    String login(String account, String password, String platform) throws Exception;

    SignModel sign(Customer customer) throws Exception;

    PageSignGiftModel pageGift(int customerId, PageSignGiftRequestModel model) throws Exception;

    MyGiftCardModel myGiftCard(int customerId) throws Exception;

    void megreCard(Customer customer, MegreCardRequestModel model) throws Exception;

    void delayedCard(int customerId, DelayedCardRequestModel model) throws Exception;

    void recevieTelePhoneFare(int customerId, PhoneFareRequestModel model) throws Exception;

    PageSignPackageModel pageSignPackage(int customerId, PageSignPackageRequestModel model);

    void recevieSecretGift(int customerId, RrcevieSecretGiftRequestModel request) throws Exception;

    PageExtRedPackageModel pageExtRedPackage(int customerId, PageExtRedPackageRequestModel model) throws Exception;

    int addCustomerAddress(int customerId, CustomerAddressRequestModel model) throws Exception;

    int deleteCustomerAddressByIdAndCustomerId(int customerId, OnlyPrimaryIdRequestModel model) throws Exception;

    int updateCustomerAddressByIdAndCustomerId(int customerId, UpdateCustomerAddressRequestModel model) throws Exception;

    CustomerAddressResponseModel getCustomerAddress(int customerId, OnlyCustomerIdRequestModel model) throws Exception;
}
