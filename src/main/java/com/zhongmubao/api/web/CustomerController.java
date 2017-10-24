package com.zhongmubao.api.web;

import com.zhongmubao.api.authorization.annotation.Authorization;
import com.zhongmubao.api.authorization.annotation.CurrentUser;
import com.zhongmubao.api.dto.request.sign.*;
import com.zhongmubao.api.dto.response.ReponseModel;
import com.zhongmubao.api.dto.response.sign.MyGiftCardModel;
import com.zhongmubao.api.dto.response.sign.signlist.PageSignGiftModel;
import com.zhongmubao.api.dto.response.sign.SignModel;
import com.zhongmubao.api.dto.response.sign.signpackagelist.PageSignPackageModel;
import com.zhongmubao.api.entity.Customer;
import com.zhongmubao.api.exception.ApiException;
import com.zhongmubao.api.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 客户控制器
 *
 * @author 孙阿龙
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //region 签到

    /***
     * 签到
     * @param customer 客户
     * @author 孙阿龙
     * @return ReponseModel
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
     * @param customer 客户
     * @param model 请求实体
     * @author 孙阿龙
     * @return ReponseModel
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
     * @param customer 客户
     * @author 孙阿龙
     * @return 请求实体
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
     * @param customer 客户
     * @param model 请求实体
     * @author 孙阿龙
     * @return ReponseModel
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
     * @param customer 客户
     * @param model 请求实体
     * @author 孙阿龙
     * @return ReponseModel
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
     * @param customer 客户
     * @param model 请求实体
     * @author 孙阿龙
     * @return ReponseModel
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
     * @param customer 客户
     * @param model 请求实体
     * @author 孙阿龙
     * @return ReponseModel
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
     * @param customer 客户
     * @param model 请求实体
     * @author 米立林 2017-10-10
     * @return ReponseModel
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
}
