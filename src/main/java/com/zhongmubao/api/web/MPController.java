package com.zhongmubao.api.web;

import com.zhongmubao.api.authorization.annotation.Authorization;
import com.zhongmubao.api.authorization.annotation.CurrentUser;
import com.zhongmubao.api.dto.request.mp.FriendsRequestModel;
import com.zhongmubao.api.dto.request.mp.OpenRequestModel;
import com.zhongmubao.api.dto.request.mp.CalcProfitRequestModel;
import com.zhongmubao.api.dto.request.mp.PageRequestModel;
import com.zhongmubao.api.dto.response.ReponseModel;
import com.zhongmubao.api.dto.response.mp.FriendsViewModel;
import com.zhongmubao.api.dto.response.mp.IndexViewModel;
import com.zhongmubao.api.dto.response.mp.ListArticleViewModel;
import com.zhongmubao.api.dto.response.mp.MyPastureViewModel;
import com.zhongmubao.api.dto.response.mp.CalcProfitViewModel;
import com.zhongmubao.api.entity.Customer;
import com.zhongmubao.api.exception.ApiException;
import com.zhongmubao.api.service.MPShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信小程序
 *
 * @author 米立林
 */
@RestController
@RequestMapping("/mp")
public class MPController {
    private final MPShareService mpShareService;

    @Autowired
    public MPController(MPShareService mpShareService) {
        this.mpShareService = mpShareService;
    }

    /**
     * 我的资产
     *
     * @author 米立林
     */
    @RequestMapping(value = "/index", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> index(@CurrentUser Customer customer) {
        try {
            IndexViewModel viewModel = mpShareService.index(customer);
            return new ResponseEntity<>(ReponseModel.ok(viewModel), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /**
     * 我的牧场
     *
     * @author 米立林
     */
    @RequestMapping(value = "/myPasture", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> myPasture(@CurrentUser Customer customer) {
        try {
            MyPastureViewModel viewModel = mpShareService.myPasture(customer);
            return new ResponseEntity<>(ReponseModel.ok(viewModel), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /**
     * 收益计算
     *
     * @author 米立林
     */
    @RequestMapping(value = "/calcProfit", method = RequestMethod.POST, consumes = "application/json")
    @Authorization(onlyGetCustomer = true)
    public ResponseEntity<ReponseModel> calcProfit(@CurrentUser Customer customer, HttpEntity<CalcProfitRequestModel> model) {
        try {
            CalcProfitViewModel viewModel = mpShareService.calcProfit(customer, model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(viewModel), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /**
     * 展示红包进度信息
     *
     * @param model 请求实体
     * @return FriendsViewModel
     * @author 米立林
     */
    @RequestMapping(value = "/friends", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> friends(@CurrentUser Customer customer, HttpEntity<FriendsRequestModel> model) {
        try {
            FriendsViewModel viewModel = mpShareService.friends(customer, model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(viewModel), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /**
     * 拆开红包
     *
     * @param model 请求实体
     * @author 米立林
     */
    @RequestMapping(value = "/open", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> open(@CurrentUser Customer customer, HttpEntity<OpenRequestModel> model) {
        try {
            mpShareService.open(customer, model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /**
     * 文章列表
     *
     * @author 米立林
     */
    @RequestMapping(value = "/article", method = RequestMethod.POST, consumes = "application/json")
    @Authorization(onlyGetCustomer = true)
    public ResponseEntity<ReponseModel> article(@CurrentUser Customer customer, HttpEntity<PageRequestModel> model) {
        try {
            ListArticleViewModel viewModel = mpShareService.article(customer, model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(viewModel), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }
}
