package com.zhongmubao.api.web;

import com.zhongmubao.api.authorization.annotation.Authorization;
import com.zhongmubao.api.authorization.annotation.CurrentUser;
import com.zhongmubao.api.config.ResultStatus;
import com.zhongmubao.api.dto.Request.Notify.NotifyRemindRequestModel;
import com.zhongmubao.api.dto.Request.Notify.NotifyRemindSaveRequestModel;
import com.zhongmubao.api.dto.Request.OnlyPrimaryIdRequestModel;
import com.zhongmubao.api.dto.Request.*;
import com.zhongmubao.api.dto.Request.Address.CustomerAddressRequestModel;
import com.zhongmubao.api.dto.Request.Address.UpdateCustomerAddressRequestModel;
import com.zhongmubao.api.dto.Request.customer.AutoRedeemRequestModel;
import com.zhongmubao.api.dto.Request.customer.ResetPasswordRequestModel;
import com.zhongmubao.api.dto.Request.customer.farmIncome.InBarSheepIncomeModel;
import com.zhongmubao.api.dto.Response.Address.CustomerAddressResponseModel;
import com.zhongmubao.api.dto.Response.Ext.PageExtRedPackageModel;
import com.zhongmubao.api.dto.Response.Notice.NoticeRemindModel;
import com.zhongmubao.api.dto.Response.Notice.RemindNoticeCycleModel;
import com.zhongmubao.api.dto.Response.Notice.RemindNoticeTypeModel;
import com.zhongmubao.api.dto.Response.ReponseModel;
import com.zhongmubao.api.dto.Response.Sign.MyGiftCardModel;
import com.zhongmubao.api.dto.Response.Sign.SignList.PageSignGiftModel;
import com.zhongmubao.api.dto.Response.Sign.SignModel;
import com.zhongmubao.api.dto.Response.Sign.SignPackageList.PageSignPackageModel;
import com.zhongmubao.api.entity.Customer;
import com.zhongmubao.api.exception.ApiException;
import com.zhongmubao.api.service.CustomerService;
import com.zhongmubao.api.util.redis.RedisHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final RedisHelper redisHelper;

    @Autowired
    public CustomerController(CustomerService customerService, RedisHelper redisHelper) {
        this.customerService = customerService;
        this.redisHelper = redisHelper;
    }

//    @RequestMapping(value = "/login")
//    public ResponseEntity<ReponseModel> login() {
//        try {
//            String token = customerService.login("15656287151", "K1BRaFVGTC9VdDg9", "00");
//            LoginResponseModel response = new LoginResponseModel(token);
//            redisHelper.save("dddxxxx", "dddxxxxxx");
//            return new ResponseEntity<>(ReponseModel.ok(response), HttpStatus.OK);
//        } catch (Exception ex) {
//            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
//            //return new ResponseEntity<>(ResultModel.error(ResultStatus.USERNAME_OR_PASSWORD_ERROR), HttpStatus.NOT_FOUND);
//        }
//
//    }

    //region 签到

    /***
     * 签到
     * @param customer
     * @author 孙阿龙
     * @return
     */
    @RequestMapping(value = "/sign", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> sign(@CurrentUser Customer customer) {
        try {
            SignModel signModel = customerService.sign(customer);
            return new ResponseEntity<>(ReponseModel.ok(signModel), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /***
     * 礼物分页
     * @param customer
     * @param model
     * @author 孙阿龙
     * @return
     */
    @RequestMapping(value = "/sign/pageGift", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> pageGift(@CurrentUser Customer customer, HttpEntity<PageSignGiftRequestModel> model) {
        try {
            PageSignGiftModel giftModel = customerService.pageGift(customer, model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(giftModel), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /***
     * 我的卡包
     * @param customer
     * @author 孙阿龙
     * @return
     */
    @RequestMapping(value = "/sign/myGiftCard", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> myGiftCard(@CurrentUser Customer customer) {
        try {
            MyGiftCardModel cardModel = customerService.myGiftCard(customer.getId());
            return new ResponseEntity<>(ReponseModel.ok(cardModel), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /***
     * 红包合并
     * @param customer
     * @param model
     * @author 孙阿龙
     * @return
     */
    @RequestMapping(value = "/sign/megreCard", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> megreCard(@CurrentUser Customer customer, HttpEntity<MegreCardRequestModel> model) {
        try {
            customerService.megreCard(customer, model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /***
     * 红包延时
     * @param customer
     * @param model
     * @author 孙阿龙
     * @return
     */
    @RequestMapping(value = "/sign/delayedCard", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> delayedCard(@CurrentUser Customer customer, HttpEntity<DelayedCardRequestModel> model) {
        try {
            customerService.delayedCard(customer.getId(), model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /***
     * 关于签到的所有红包分页
     * @param customer
     * @param model
     * @author 孙阿龙
     * @return
     */
    @RequestMapping(value = "/sign/pageSignPackage", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> pageSignPackage(@CurrentUser Customer customer, HttpEntity<PageSignPackageRequestModel> model) {
        try {
            PageSignPackageModel pageSignPackage = customerService.pageSignPackage(customer.getId(), model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(pageSignPackage), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /***
     * 领取神秘礼物
     * @param customer
     * @param model
     * @author 孙阿龙
     * @return
     */
    @RequestMapping(value = "/sign/recevieSecretGift", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> recevieSecretGift(@CurrentUser Customer customer, HttpEntity<RrcevieSecretGiftRequestModel> model) {
        try {
            customerService.recevieSecretGift(customer.getId(), model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /***
     * 领取话费充值卡
     * @param customer
     * @param model
     * @author 米立林 2017-10-10
     * @return
     */
    @RequestMapping(value = "/sign/receiveRechargeGift", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> receiveRechargeGift(@CurrentUser Customer customer, HttpEntity<ReceiveRechargeGiftRequestModel> model) {
        try {
            customerService.receiveRechargeGift(customer, model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    //endregion

    // region 个人中心 -- 红包列表

    /**
     * @param customer 主键ID
     * @param model    请求参数Model
     * @return 获取用户红包列表
     * @author 米立林
     */
    @RequestMapping(value = "/pageRedPackage", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> pageExtRedPackage(@CurrentUser Customer customer, HttpEntity<PageExtRedPackageRequestModel> model) {
        try {
            PageExtRedPackageModel extRedPackageModel = customerService.pageExtRedPackage(customer.getId(), model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(extRedPackageModel), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }
    // endregion

    //region 个人中心 -- 地址管理

    /**
     * 新增
     *
     * @param customer 所属用户
     * @param model    地址请求实体
     * @return
     * @author 米立林
     */
    @RequestMapping(value = "/addCustomerAddress", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> addCustomerAddress(@CurrentUser Customer customer, HttpEntity<CustomerAddressRequestModel> model) {
        try {
            customerService.addCustomerAddress(customer.getId(), model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /**
     * 删除
     *
     * @param customer 所属用户
     * @param model    地址请求实体
     * @return
     * @author 米立林
     */
    @RequestMapping(value = "/deleteCustomerAddress", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> deleteCustomerAddress(@CurrentUser Customer customer, HttpEntity<OnlyPrimaryIdRequestModel> model) {
        try {
            customerService.deleteCustomerAddress(customer.getId(), model.getBody());

            return new ResponseEntity<>(ReponseModel.ok(), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /**
     * 修改
     *
     * @param customer 所属用户
     * @param model    地址请求实体
     * @return
     * @author 米立林
     */
    @RequestMapping(value = "/updateCustomerAddress", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> updateCustomerAddress(@CurrentUser Customer customer, HttpEntity<UpdateCustomerAddressRequestModel> model) {
        try {
            customerService.updateCustomerAddress(customer.getId(), model.getBody());

            return new ResponseEntity<>(ReponseModel.ok(), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /**
     * 获取用户地址
     *
     * @param customer 当前用户
     * @param model    地址信息
     * @return 用户地址集合
     * @author 米立林
     */
    @RequestMapping(value = "/pageCustomerAddress", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> pageCustomerAddress(@CurrentUser Customer customer, HttpEntity<OnlyCustomerIdRequestModel> model) {
        try {
            CustomerAddressResponseModel customerAddressModel = customerService.getCustomerAddress(customer.getId(), model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(customerAddressModel), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    //endregion

    //region 个人中心 -- 设置

    /***
     * 重置登录密码
     * @param customer
     * @param model
     * @author 米立林 2017-09-30
     * @return
     */
    @RequestMapping(value = "/setting/resetPassword", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> resetPassword(@CurrentUser Customer customer, HttpEntity<ResetPasswordRequestModel> model) {
        try {
            customerService.resetPassword(customer.getId(), model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /***
     * 重置赎回密码
     * @param customer
     * @param model
     * @author 米立林 2017-09-30
     * @return
     */
    @RequestMapping(value = "/setting/resetRedeemPassword", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> resetRedeemPassword(@CurrentUser Customer customer, HttpEntity<ResetPasswordRequestModel> model) {
        try {
            customerService.resetRedeemPassword(customer.getId(), model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /***
     * 开启/关闭 自动赎回
     * @param customer
     * @author 米立林 2017-09-30
     * @return
     */
    @RequestMapping(value = "/setting/autoRedeemAmount", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> autoRedeemAmount(@CurrentUser Customer customer, HttpEntity<AutoRedeemRequestModel> model) {
        try {
            boolean isSuccess = customerService.autoRedeemAmount(customer, model.getBody());
            if (isSuccess) {
                return new ResponseEntity<>(ReponseModel.ok(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(ReponseModel.error(ResultStatus.REDEEM_PASSWORD_ERROR), HttpStatus.OK);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }
    //endregion

    //region 个人中心 -- 购羊提醒

    /***
     * 购羊提醒列表
     * @param customer 当前用户
     * @author 米立林 2017-10-18
     * @return
     */
    @RequestMapping(value = "/buySheepRemind/index", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> buySheepRemindIndex(@CurrentUser Customer customer) {
        try {
            NoticeRemindModel noticeRemind = customerService.buySheepRemindIndex(customer.getId());
            return new ResponseEntity<>(ReponseModel.ok(noticeRemind), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /***
     * 购羊提醒类型
     * @param customer 当前用户
     * @author 米立林 2017-10-18
     * @return
     */
    @RequestMapping(value = "/buySheepRemind/notifyType", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> buySheepRemindNotifyType(@CurrentUser Customer customer) {
        try {
            RemindNoticeTypeModel notifyType = customerService.buySheepRemindNotifyType(customer.getId());
            return new ResponseEntity<>(ReponseModel.ok(notifyType), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /***
     * 购羊提醒周期
     * @param customer 当前用户
     * @author 米立林 2017-10-18
     * @return
     */
    @RequestMapping(value = "/buySheepRemind/notifyCycle", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> buySheepRemindNotifyCycle(@CurrentUser Customer customer) {
        try {
            RemindNoticeCycleModel notifyCycle = customerService.buySheepRemindNotifyCycle(customer.getId());
            return new ResponseEntity<>(ReponseModel.ok(notifyCycle), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /***
     * 保存购羊提醒
     * @param customer 当前用户
     * @param model 购羊提醒参数
     * @author 米立林 2017-10-18
     * @return
     */
    @RequestMapping(value = "/buySheepRemind/notifySave", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> buySheepRemindNotifySave(@CurrentUser Customer customer, HttpEntity<NotifyRemindSaveRequestModel> model) {
        try {
            customerService.buySheepRemindNotifySave(customer.getId(), model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /***
     * 打开/关闭购羊提醒
     * @param customer 当前用户
     * @param model 购羊提醒参数
     * @author 米立林 2017-10-19
     * @return
     */
    @RequestMapping(value = "/buySheepRemind/notifyOnOrOff", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> buySheepRemindNotifyOnOrOff(@CurrentUser Customer customer, HttpEntity<NotifyRemindRequestModel> model) {
        try {
            customerService.buySheepRemindNotifyOnOrOff(customer.getId(), model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /***
     * 删除购羊提醒
     * @param customer 当前用户
     * @param model 购羊提醒参数
     * @author 米立林 2017-10-19
     * @return
     */
    @RequestMapping(value = "/buySheepRemind/notifyDel", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> buySheepRemindNotifyDel(@CurrentUser Customer customer, HttpEntity<NotifyRemindRequestModel> model) {
        try {
            customerService.buySheepRemindNotifyDel(customer.getId(), model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    //endregion

    //region 个人中心 -- 牧场收益

    /***
     * 在栏羊只收益
     * @param customer 当前用户
     * @author 米立林 2017-10-19
     * @return
     */
    @RequestMapping(value = "/farmIncome/inBarSheepIncome", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> inBarSheepIncome(@CurrentUser Customer customer, HttpEntity<PageIndexRequestModel> model) {
        try {
            InBarSheepIncomeModel inBarIncome = customerService.inBarSheepIncome(customer.getId(),model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(inBarIncome), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }


    //endregion

}
