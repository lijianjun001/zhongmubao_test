package com.zhongmubao.api.service;

import com.zhongmubao.api.dto.request.notify.NotifyRemindRequestModel;
import com.zhongmubao.api.dto.request.notify.NotifyRemindSaveRequestModel;
import com.zhongmubao.api.dto.request.OnlyPrimaryIdRequestModel;
import com.zhongmubao.api.dto.request.*;
import com.zhongmubao.api.dto.request.address.CustomerAddressRequestModel;
import com.zhongmubao.api.dto.request.address.UpdateCustomerAddressRequestModel;
import com.zhongmubao.api.dto.request.customer.AutoRedeemRequestModel;
import com.zhongmubao.api.dto.request.customer.ResetPasswordRequestModel;
import com.zhongmubao.api.dto.response.customer.InBarSheepIncomeModel;
import com.zhongmubao.api.dto.response.address.CustomerAddressResponseModel;
import com.zhongmubao.api.dto.response.ext.PageExtRedPackageModel;
import com.zhongmubao.api.dto.response.notice.NoticeRemindModel;
import com.zhongmubao.api.dto.response.notice.RemindNoticeCycleModel;
import com.zhongmubao.api.dto.response.notice.RemindNoticeTypeModel;
import com.zhongmubao.api.dto.response.sign.MyGiftCardModel;
import com.zhongmubao.api.dto.response.sign.SignList.PageSignGiftModel;
import com.zhongmubao.api.dto.response.sign.SignModel;
import com.zhongmubao.api.dto.response.sign.SignPackageList.PageSignPackageModel;
import com.zhongmubao.api.dto.response.customer.WalletBalanceIncomeModel;
import com.zhongmubao.api.entity.Customer;

public interface CustomerService {

    String login(String account, String password, String platform) throws Exception;

    SignModel sign(Customer customer) throws Exception;

    PageSignGiftModel pageGift(Customer customer, PageSignGiftRequestModel model) throws Exception;

    MyGiftCardModel myGiftCard(int customerId) throws Exception;

    void megreCard(Customer customer, MegreCardRequestModel model) throws Exception;

    void delayedCard(int customerId, DelayedCardRequestModel model) throws Exception;

    PageSignPackageModel pageSignPackage(int customerId, PageSignPackageRequestModel model);

    void recevieSecretGift(int customerId, RrcevieSecretGiftRequestModel request)throws Exception;

    PageExtRedPackageModel pageExtRedPackage(int customerId, PageExtRedPackageRequestModel model) throws Exception;

    int addCustomerAddress(int customerId, CustomerAddressRequestModel model) throws Exception;

    int deleteCustomerAddress(int customerId, OnlyPrimaryIdRequestModel model) throws Exception;

    int updateCustomerAddress(int customerId, UpdateCustomerAddressRequestModel model) throws Exception;

    CustomerAddressResponseModel getCustomerAddress(int customerId, OnlyCustomerIdRequestModel model) throws Exception;

    void resetPassword(int customerId, ResetPasswordRequestModel model) throws Exception;

    void resetRedeemPassword(int customerId, ResetPasswordRequestModel model) throws Exception;

    boolean autoRedeemAmount(Customer customer, AutoRedeemRequestModel model) throws Exception;

    void receiveRechargeGift(Customer customer, ReceiveRechargeGiftRequestModel model) throws Exception;

    NoticeRemindModel buySheepRemindIndex(int customerId) throws Exception;

    RemindNoticeTypeModel buySheepRemindNotifyType(int customerId) throws Exception;

    RemindNoticeCycleModel buySheepRemindNotifyCycle(int customerId) throws Exception;

    void buySheepRemindNotifySave(int customerId, NotifyRemindSaveRequestModel model) throws Exception;

    void buySheepRemindNotifyOnOrOff(int customerId, NotifyRemindRequestModel model) throws Exception;

    void buySheepRemindNotifyDel(int customerId, NotifyRemindRequestModel model) throws Exception;

    InBarSheepIncomeModel inBarSheepIncome(int customerId,PageIndexRequestModel model) throws Exception;

    WalletBalanceIncomeModel walletBalanceIncome(int customerId) throws Exception;

}
