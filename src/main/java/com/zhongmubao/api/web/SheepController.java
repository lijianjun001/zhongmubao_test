package com.zhongmubao.api.web;

import com.zhongmubao.api.authorization.annotation.Authorization;
import com.zhongmubao.api.authorization.annotation.CurrentUser;
import com.zhongmubao.api.dto.request.OnlyPrimaryIdRequestModel;
import com.zhongmubao.api.dto.request.PageIndexRequestModel;
import com.zhongmubao.api.dto.request.sheep.MySheepRoomRequestModel;
import com.zhongmubao.api.dto.request.sheep.SheepOrderRequestModel;
import com.zhongmubao.api.dto.request.ProjectPlanRequestModel;
import com.zhongmubao.api.dto.request.SystemMonitorRequestModel;
import com.zhongmubao.api.dto.response.index.IndexModel;
import com.zhongmubao.api.dto.response.ReponseModel;
import com.zhongmubao.api.dto.response.sheep.*;
import com.zhongmubao.api.dto.response.sheepfold.SheepProjectOrdersModel;
import com.zhongmubao.api.entity.Customer;
import com.zhongmubao.api.service.SheepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Sheep控制器
 *
 * @author 孙阿龙
 */
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
     * @return 已售羊只订单列表
     * @author 米立林 2017-09-30
     */
    @Authorization
    @RequestMapping(value = "/mySheepfold/soldSheepOrderEarnings", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ReponseModel> soldSheepOrderEarnings(@CurrentUser Customer customer, HttpEntity<SheepOrderRequestModel> requestModel) {
        try {
            PageSheepOrderEarningsModel earningsModel = sheepService.pageSheepOrderEarnings(customer.getId(), requestModel.getBody());
            return new ResponseEntity<>(ReponseModel.ok(earningsModel), HttpStatus.OK);
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
     * @return 我的羊圈View
     * @author 米立林 2017-10-09
     */
    @Authorization
    @RequestMapping(value = "/mySheepfold", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ReponseModel> mySheepfold(@CurrentUser Customer customer,HttpEntity<PageIndexRequestModel> model) {
        try {
            MySheepfoldModel mySheepfoldModel = sheepService.mySheepfold(customer.getId(),model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(mySheepfoldModel), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /**
     * @param customer      当前用户
     * @param requestModel  SheepProject主键
     * @return 我的羊圈-- 羊标订单列表
     * @author 米立林 2017-10-09
     */
    @Authorization
    @RequestMapping(value = "/mySheepfold/sheepProjectOrders", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ReponseModel> mySheepfoldSheepProjectOrders(@CurrentUser Customer customer, HttpEntity<OnlyPrimaryIdRequestModel> requestModel) {
        try {
            SheepProjectOrdersModel sheepProjectOrders = sheepService.mySheepfoldSheepProjectOrders(customer.getId(),requestModel.getBody());
            return new ResponseEntity<>(ReponseModel.ok(sheepProjectOrders), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /**
     * @param customer     当前用户
     * @param requestModel SheepOrder主键
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
     * @param requestModel SheepProject主键
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

    /**
     * @param customer     当前用户
     * @param requestModel SheepProject主键
     * @return 羊只有保险
     * @author 米立林 2017-10-20
     */
    @Authorization
    @RequestMapping(value = "/mySheepfold/projectInsurance", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ReponseModel> mySheepfoldProjectInsurance(@CurrentUser Customer customer, HttpEntity<OnlyPrimaryIdRequestModel> requestModel) {
        try {
            SheepProjectInsurance sheepProjectInsurance = sheepService.mySheepfoldProjectInsurance(customer.getId(),requestModel.getBody());
            return new ResponseEntity<>(ReponseModel.ok(sheepProjectInsurance), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /**
     * @param customer  当前用户
     * @return 获取养殖流程
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
     * 我的羊圈 -- 可赎回
     * @param customer  当前用户
     * @param requestModel SheepProject主键
     * @return 可赎回订单信息
     * @author 米立林 2017-10-23
     */
    @Authorization
    @RequestMapping(value = "/sheepRedeemableProperty", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ReponseModel> sheepRedeemableProperty(@CurrentUser Customer customer, HttpEntity<OnlyPrimaryIdRequestModel> requestModel) {
        try {
            SheepRedeemableViewModel sheepRedeemableModel = sheepService.sheepRedeemableProperty(customer.getId(),requestModel.getBody());
            return new ResponseEntity<>(ReponseModel.ok(sheepRedeemableModel), HttpStatus.OK);
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

    /**
     * @param customer     当前用户
     * @return 我的羊圈
     * @author xy 2017-10-09
     */
    @Authorization
    @RequestMapping(value = "/mySheepRoom", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ReponseModel> mySheepRoom(@CurrentUser Customer customer,HttpEntity<MySheepRoomRequestModel> model) {
        try {
            MySheepRoomListViewModel mySheepRoomListViewModel = sheepService.mySheepRoom(customer.getId(),model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(mySheepRoomListViewModel), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }


    /**
     * @param customer     当前用户
     * @return 我的羊圈 已售出列表
     * @author xy 2017-10-09
     */
    @Authorization
    @RequestMapping(value = "/mySheepRoomRedeemed", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ReponseModel> mySheepRoomRedeemed(@CurrentUser Customer customer,HttpEntity<MySheepRoomRequestModel> model) {
        try {
            MySheepRoomRedeemedListViewModel mySheepFoldRedeemedListViewModel = sheepService.mySheepRoomRedeemed(customer.getId(),model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(mySheepFoldRedeemedListViewModel), HttpStatus.OK);
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
