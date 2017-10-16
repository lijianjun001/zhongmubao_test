package com.zhongmubao.api.web;

import com.zhongmubao.api.authorization.annotation.Authorization;
import com.zhongmubao.api.authorization.annotation.CurrentUser;
import com.zhongmubao.api.components.recharge.Recharge;
import com.zhongmubao.api.dto.Request.OnlyPrimaryIdRequestModel;
import com.zhongmubao.api.dto.Request.*;
import com.zhongmubao.api.dto.Request.Address.CustomerAddressRequestModel;
import com.zhongmubao.api.dto.Request.Address.UpdateCustomerAddressRequestModel;
import com.zhongmubao.api.dto.Response.Address.CustomerAddressResponseModel;
import com.zhongmubao.api.dto.Response.Ext.PageExtRedPackageModel;
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
            PageSignGiftModel giftModel = customerService.pageGift(customer.getId(), model.getBody());
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

    //endregion


    /**
     * @param customer 主键ID
     * @param model    请求参数Model
     * @return
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

    //region 地址管理 SystemDistrict

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
            customerService.deleteCustomerAddressByIdAndCustomerId(customer.getId(), model.getBody());

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
            customerService.updateCustomerAddressByIdAndCustomerId(customer.getId(), model.getBody());

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

}
