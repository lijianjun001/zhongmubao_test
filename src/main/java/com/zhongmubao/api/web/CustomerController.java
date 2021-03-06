package com.zhongmubao.api.web;

import com.zhongmubao.api.authorization.annotation.Authorization;
import com.zhongmubao.api.authorization.annotation.CurrentUser;
import com.zhongmubao.api.config.Constants;
import com.zhongmubao.api.dto.request.BaseRequest;
import com.zhongmubao.api.dto.request.customer.AccountExistRequestModel;
import com.zhongmubao.api.dto.request.customer.LoginRequestModel;
import com.zhongmubao.api.dto.request.message.DetailRequestModel;
import com.zhongmubao.api.dto.request.message.ListRequestModel;
import com.zhongmubao.api.dto.request.customer.RecommendInfoRequestModel;
import com.zhongmubao.api.dto.request.customer.RegisterRequestModel;
import com.zhongmubao.api.dto.request.message.ReadRequestModel;
import com.zhongmubao.api.dto.request.my.RealNameRequestModel;
import com.zhongmubao.api.dto.request.sign.*;
import com.zhongmubao.api.dto.response.ReponseModel;
import com.zhongmubao.api.dto.response.customer.AccountExistViewModel;
import com.zhongmubao.api.dto.response.customer.LoginViewmodel;
import com.zhongmubao.api.dto.response.message.*;
import com.zhongmubao.api.dto.response.customer.RecommendInfoViewModel;
import com.zhongmubao.api.dto.response.customer.RegisterViewModel;
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
import com.zhongmubao.api.service.MessageService;
import com.zhongmubao.api.service.SignService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    private final MessageService messageService;

    @Autowired
    public CustomerController(SignService signService, CustomerService customerService, MessageService messageService) {
        this.signService = signService;
        this.customerService = customerService;
        this.messageService = messageService;
    }

    //region 登录

    /***
     * 用户登录
     * @param model 请求参数
     * @author 米立林
     * @return LoginViewmodel
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ReponseModel> login(HttpEntity<LoginRequestModel> model) {
        try {

            LoginViewmodel viewModel = customerService.login(model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(viewModel), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }
    //endregion

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
     * @return ReponseModel
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json")
    @Authorization(onlyGetCustomer = true)
    public ResponseEntity<ReponseModel> register(HttpEntity<RegisterRequestModel> model) {
        try {
            RegisterViewModel viewModel = customerService.register(model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(viewModel), HttpStatus.OK);
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
     * @return ReponseModel
     */
    @RequestMapping(value = "recommendInfo", method = RequestMethod.POST, consumes = "application/json")
    @Authorization(onlyGetCustomer = true)
    public ResponseEntity<ReponseModel> recommendInfo(HttpEntity<RecommendInfoRequestModel> model) {
        try {
            RecommendInfoViewModel viewModel = customerService.recommendInfo(model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(viewModel), HttpStatus.OK);
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
     * @return ReponseModel
     */
    @RequestMapping(value = "exist", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Authorization(onlyGetCustomer = true)
    public ResponseEntity<ReponseModel> exist(HttpEntity<AccountExistRequestModel> model) {
        try {
            AccountExistViewModel viewModel = customerService.exist(model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(viewModel), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }
    //endregion

    //region 客户消息

    /***
     * 获取最新消息数
     * @param customer 客户
     * @author 米立林
     * @return ReponseModel
     */
    @RequestMapping(value = "/message/count", method = RequestMethod.POST, consumes = "application/json")
    @Authorization(onlyGetCustomer = true)
    public ResponseEntity<ReponseModel> messageCount(@CurrentUser Customer customer) {
        try {
            NewMessageCountViewModel count = messageService.count(customer);
            return new ResponseEntity<>(ReponseModel.ok(count), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /***
     * 消息中心
     * @param customer 客户
     * @author 米立林
     * @return ReponseModel
     */
    @RequestMapping(value = "/message/center", method = RequestMethod.POST, consumes = "application/json")
    @Authorization(onlyGetCustomer = true)
    public ResponseEntity<ReponseModel> messageCenter(@CurrentUser Customer customer) {
        try {
            CenterViewModel viewModel = messageService.center(customer);
            return new ResponseEntity<>(ReponseModel.ok(viewModel), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /***
     * 消息列表
     * @param customer 客户
     * @param model 请求参数
     * @author 米立林
     * @return ReponseModel
     */
    @RequestMapping(value = "/message/list", method = RequestMethod.POST, consumes = "application/json")
    @Authorization(onlyGetCustomer = true)
    public ResponseEntity<ReponseModel> messageList(@CurrentUser Customer customer, HttpEntity<ListRequestModel> model) {
        try {
            ListViewModel viewModel = messageService.list(customer, model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(viewModel), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /***
     * 获取消息详情
     * @param customer 客户
     * @param model 请求参数
     * @author 米立林
     * @return ReponseModel
     */
    @RequestMapping(value = "/message/detail", method = RequestMethod.POST, consumes = "application/json")
    @Authorization(onlyGetCustomer = true)
    public ResponseEntity<ReponseModel> messageDetail(@CurrentUser Customer customer, HttpEntity<DetailRequestModel> model) {
        try {
            DetailViewModel viewModel = messageService.detail(customer, model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(viewModel), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /***
     * 首页弹层
     * @param customer 客户
     * @param model 请求参数
     * @author 孙阿龙
     * @return ReponseModel
     */
    @RequestMapping(value = "/message/indexLayer", method = RequestMethod.POST, consumes = "application/json")
    @Authorization(onlyGetCustomer = true)
    public ResponseEntity<ReponseModel> indexLayer(@CurrentUser Customer customer, HttpEntity<BaseRequest> model) {
        try {
            IndexLayerViewModel viewModel = messageService.indexLayer(customer, model.getBody().getPlatform());
            return new ResponseEntity<>(ReponseModel.ok(viewModel), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /***
     * 设置消息已读
     * @param customer 客户
     * @param model 请求参数
     * @author 孙阿龙
     * @return ReponseModel
     */
    @RequestMapping(value = "/message/read", method = RequestMethod.POST, consumes = "application/json")
    @Authorization(onlyGetCustomer = true)
    public ResponseEntity<ReponseModel> read(@CurrentUser Customer customer, HttpEntity<ReadRequestModel> model) {
        try {
            messageService.read(customer, model.getBody().getId());
            return new ResponseEntity<>(ReponseModel.ok(), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /***
     * 一键阅读消息
     * @param customer 客户
     * @author 孙阿龙
     * @return ReponseModel
     */
    @RequestMapping(value = "/message/oneKeyRed", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> oneKeyRed(@CurrentUser Customer customer) {
        try {
            messageService.oneKeyRed(customer);
            return new ResponseEntity<>(ReponseModel.ok(), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }
    //endregion
}
