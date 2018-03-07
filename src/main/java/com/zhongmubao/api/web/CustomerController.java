package com.zhongmubao.api.web;

import com.zhongmubao.api.authorization.annotation.Authorization;
import com.zhongmubao.api.authorization.annotation.CurrentUser;
import com.zhongmubao.api.config.Constants;
import com.zhongmubao.api.dto.request.BaseRequest;
import com.zhongmubao.api.dto.request.customer.RecommendInfoRequestModel;
import com.zhongmubao.api.dto.request.customer.RegisterRequestModel;
import com.zhongmubao.api.dto.request.my.RealNameRequestModel;
import com.zhongmubao.api.dto.request.sign.*;
import com.zhongmubao.api.dto.response.ReponseModel;
import com.zhongmubao.api.dto.response.customer.RecommendInfoViewModel;
import com.zhongmubao.api.dto.response.my.RealNameViewModel;
import com.zhongmubao.api.dto.response.sign.MyGiftCardModel;
import com.zhongmubao.api.dto.response.sign.SignShareInfoModel;
import com.zhongmubao.api.dto.response.sign.SignTodayIsShareModel;
import com.zhongmubao.api.dto.response.sign.list.PageSignGiftModel;
import com.zhongmubao.api.dto.response.sign.SignModel;
import com.zhongmubao.api.dto.response.sign.packagelist.PageSignPackageModel;
import com.zhongmubao.api.entity.Customer;
import com.zhongmubao.api.exception.ApiException;
import com.zhongmubao.api.service.CustomerService;
import com.zhongmubao.api.service.SignService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 客户控制器
 *
 * @author 孙阿龙
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final SignService signService;
    private final CustomerService customerService;

    @Autowired
    public CustomerController(SignService signService, CustomerService customerService) {
        this.signService = signService;
        this.customerService = customerService;
    }


    //region 个人中心 新浪OR汇付

    /***
     * 个人中心 实名OR开户
     * @param customer 客户
     * @author xy
     * @return ReponseModel
     */
    @RequestMapping(value = "/choosePaymentRealName", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> choosePaymentRealName(@CurrentUser Customer customer, HttpEntity<RealNameRequestModel> model) {
        try {

            RealNameViewModel realNameViewModel = customerService.choosePaymentRealName(customer, model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(realNameViewModel), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }
    //endregion

    //region 签到

    /***
     * 签到
     * @param customer 客户
     * @author 孙阿龙
     * @return ReponseModel
     */
    @RequestMapping(value = "/sign", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> sign(@CurrentUser Customer customer, HttpEntity<BaseRequest> model) {
        try {
            //暂时这样处理request，等以后app更新就不需要这要操作
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            BaseRequest baseRequestModel = model.getBody();
            baseRequestModel.setPlatform(request.getHeader(Constants.PLATFORM));

            SignModel signModel = signService.sign(customer, baseRequestModel);

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
            PageSignGiftModel giftModel = signService.pageGift(customer, model.getBody());
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
            MyGiftCardModel cardModel = signService.myGiftCard(customer.getId());
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
            signService.megreCard(customer, model.getBody());
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
            signService.delayedCard(customer.getId(), model.getBody());
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
            PageSignPackageModel pageSignPackage = signService.pageSignPackage(customer.getId(), model.getBody());
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
            signService.recevieSecretGift(customer.getId(), model.getBody());
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
            signService.receiveRechargeGift(customer, model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/sign/todayIsShare", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> todayIsShare(@CurrentUser Customer customer) {
        try {
            SignTodayIsShareModel signTodayIsShareModel = signService.todayIsShare(customer.getId());
            return new ResponseEntity<>(ReponseModel.ok(signTodayIsShareModel), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/sign/shareInfo", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> shareInfo(@CurrentUser Customer customer) {
        try {
            SignShareInfoModel signShareInfoModel = signService.shareInfo(customer.getId());
            return new ResponseEntity<>(ReponseModel.ok(signShareInfoModel), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /**
     * 用户注册
     *
     * @param model 请求参数
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ReponseModel> register(HttpEntity<RegisterRequestModel> model) {
        try {
            customerService.register(model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /**
     * 通过Code获取信息
     *
     * @param model 请求参数
     * @return
     */
    @RequestMapping(value = "recommendInfo", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ReponseModel> infoByCode(HttpEntity<RecommendInfoRequestModel> model) {
        try {
            RecommendInfoViewModel viewModel = customerService.recommendInfo(model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(viewModel), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    //endregion
}
