package com.zhongmubao.api.web;

import com.zhongmubao.api.authorization.annotation.Authorization;
import com.zhongmubao.api.authorization.annotation.CurrentUser;
import com.zhongmubao.api.dto.Request.OnlyPrimaryIdRequestModel;
import com.zhongmubao.api.dto.Request.Sheep.MySheepFoldRequestModel;
import com.zhongmubao.api.dto.Request.Sheep.SheepOrderRequestModel;
import com.zhongmubao.api.dto.Request.ProjectPlanRequestModel;
import com.zhongmubao.api.dto.Request.SystemMonitorRequestModel;
import com.zhongmubao.api.dto.Response.Index.IndexModel;
import com.zhongmubao.api.dto.Response.LoginResponseModel;
import com.zhongmubao.api.dto.Response.ReponseModel;
import com.zhongmubao.api.dto.Response.Sheep.*;
import com.zhongmubao.api.entity.Customer;
import com.zhongmubao.api.service.SheepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sheep")
public class SheepController {
    private final SheepService sheepService;

    @Autowired
    public SheepController(SheepService sheepService) {
        this.sheepService = sheepService;
    }

    /***
     * 首页
     * @param customer
     * @author 孙阿龙
     * @return
     */
    @Authorization(onlyGetCustomer = true)
    @RequestMapping(value = "/index", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ReponseModel> index(@CurrentUser Customer customer) {
        try {
            IndexModel indexModel = sheepService.index(customer);
            return new ResponseEntity<>(ReponseModel.ok(indexModel), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    //region SheepOrder

    /**
     * @param customer     当前用户
     * @param requestModel 请求参数
     * @return 买羊订单列表
     * @author 米立林
     */
    @Authorization
    @RequestMapping(value = "/pageSheepOrder", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ReponseModel> pageSheepOrder(@CurrentUser Customer customer, HttpEntity<SheepOrderRequestModel> requestModel) {
        try {
            PageSheepOrderModel sheepOrderModel = sheepService.pageSheepOrder(customer.getId(), requestModel.getBody());
            return new ResponseEntity<>(ReponseModel.ok(sheepOrderModel), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /**
     * @param customer     当前用户
     * @param requestModel 请求参数
     * @return 已售羊只订单列表（State其他参数也能获取，此接口包括收益金额）
     * @author 米立林 2017-09-30
     */
    @Authorization
    @RequestMapping(value = "/pageSheepOrderEarnings", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ReponseModel> pageSheepOrderEarnings(@CurrentUser Customer customer, HttpEntity<SheepOrderRequestModel> requestModel) {
        try {
            PageSheepOrderEarningsModel earningsModel = sheepService.pageSheepOrderEarnings(customer.getId(), requestModel.getBody());
            return new ResponseEntity<>(ReponseModel.ok(earningsModel), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /**
     * @param customer     当前用户
     * @param requestModel 请求参数
     * @return 买羊订单详情
     * @author 米立林 2017-09-25
     */
    @Authorization
    @RequestMapping(value = "/sheepOrderDetail", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ReponseModel> sheepOrderDetail(@CurrentUser Customer customer, HttpEntity<OnlyPrimaryIdRequestModel> requestModel) {
        try {
            SheepOrderDetailModel sheepOrderDetailModel = sheepService.sheepOrderDetail(customer.getId(), requestModel.getBody());
            return new ResponseEntity<>(ReponseModel.ok(sheepOrderDetailModel), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /**
     * @param customer     当前用户
     * @param requestModel 请求参数
     * @return 牧场详情
     * @author 米立林 2017-09-26
     */
    @Authorization
    @RequestMapping(value = "/sheepVendorDetail", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ReponseModel> sheepVendorDetail(@CurrentUser Customer customer, HttpEntity<OnlyPrimaryIdRequestModel> requestModel) {
        try {
            SheepVendorViewModel sheepVendorViewModel = sheepService.pastureDetail(customer.getId(), requestModel.getBody());
            return new ResponseEntity<>(ReponseModel.ok(sheepVendorViewModel), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    //endregion

    /**
     * @param customer     当前用户
     * @param requestModel 请求参数
     * @return 养标计划
     * @author 米立林 2017-09-27
     */
    @Authorization
    @RequestMapping(value = "/sheepProjectPlan", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ReponseModel> sheepProjectPlan(@CurrentUser Customer customer, HttpEntity<OnlyPrimaryIdRequestModel> requestModel) {
        try {
            SheepProjectPlanViewModel sheepProjectPlanViewModel = sheepService.sheepProjectPlan(customer.getId(), requestModel.getBody());
            return new ResponseEntity<>(ReponseModel.ok(sheepProjectPlanViewModel), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /**
     * @param customer     当前用户
     * @return 获取养殖流程(包括羊只和商铺)
     * @author 米立林 2017-09-27
     */
    @Authorization
    @RequestMapping(value = "/sheepStage", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ReponseModel> sheepStage(@CurrentUser Customer customer, HttpEntity<OnlyPrimaryIdRequestModel> requestModel) {
        try {
            PageSheepStageModel sheepStageViewModel = sheepService.sheepStage(customer.getId(),requestModel.getBody());
            return new ResponseEntity<>(ReponseModel.ok(sheepStageViewModel), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /**
     * @param customer     当前用户
     * @return 我的羊圈(包括羊只和商铺)
     * @author 米立林 2017-10-09
     */
    @Authorization
    @RequestMapping(value = "/mySheepfold", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ReponseModel> mySheepfold(@CurrentUser Customer customer) {
        try {
            MySheepfoldModel mySheepfoldModel = sheepService.mySheepfold(customer.getId());
            return new ResponseEntity<>(ReponseModel.ok(mySheepfoldModel), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }
    /**
     * @param customer     当前用户
     * @return 我的羊圈
     * @author xy 2017-10-09
     */
    @Authorization
    @RequestMapping(value = "/mySheepFoldList", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ReponseModel> mySheepFoldList(@CurrentUser Customer customer,HttpEntity<MySheepFoldRequestModel> model) {
        try {
            MySheepFoldListViewModel mySheepFoldListViewModel = sheepService.mySheepFoldList(customer.getId(),model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(mySheepFoldListViewModel), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }


    /**
     * @param customer     当前用户
     * @return 我的羊圈--牧场摄像头
     * @author 米立林 2017-10-09
     */
    @Authorization
    @RequestMapping(value = "/mySheepfold/pastureMonitor", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ReponseModel> pastureMonitor(@CurrentUser Customer customer, HttpEntity<SystemMonitorRequestModel> requestModel) {
        try {
            PastureMonitorModel monitorModel = sheepService.pastureMonitor(customer.getId(),requestModel.getBody());
            return new ResponseEntity<>(ReponseModel.ok(monitorModel), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/projectPlan", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ReponseModel> ProjectPlan(HttpEntity<ProjectPlanRequestModel> requestModel) {
        try {
            ProjectPlanModel projectPlanModel = sheepService.projectPlan(requestModel.getBody());
            return new ResponseEntity<>(ReponseModel.ok(projectPlanModel), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }
}
