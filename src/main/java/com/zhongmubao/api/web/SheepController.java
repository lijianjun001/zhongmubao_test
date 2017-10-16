package com.zhongmubao.api.web;

import com.zhongmubao.api.authorization.annotation.Authorization;
import com.zhongmubao.api.authorization.annotation.CurrentUser;
import com.zhongmubao.api.dto.Request.OnlyPrimaryIdRequestModel;
import com.zhongmubao.api.dto.Request.Sheep.SheepOrderRequestModel;
import com.zhongmubao.api.dto.Response.Index.IndexModel;
import com.zhongmubao.api.dto.Response.LoginResponseModel;
import com.zhongmubao.api.dto.Response.ReponseModel;
import com.zhongmubao.api.dto.Response.Sheep.PageSheepOrderModel;
import com.zhongmubao.api.dto.Response.Sheep.SheepOrderDetailModel;
import com.zhongmubao.api.dto.Response.Sheep.SheepVendorViewModel;
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
     * @param user1
     * @author 孙阿龙
     * @return
     */
    @Authorization(onlyGetCustomer = true)
    @RequestMapping(value = "/index", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ReponseModel> index(@CurrentUser Customer customer, HttpEntity<LoginResponseModel> user1) {
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
     * @return 获取买羊订单列表
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
     * @return 获取买羊订单列表
     * @author 米立林
     */
    @Authorization
    @RequestMapping(value = "/GetSheepOrderDetailById", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ReponseModel> GetSheepOrderDetail(@CurrentUser Customer customer, HttpEntity<OnlyPrimaryIdRequestModel> requestModel) {
        try {
            SheepOrderDetailModel sheepOrderDetailModel = sheepService.GetDetailByIdAndCustomerId(customer.getId(), requestModel.getBody());
            return new ResponseEntity<>(ReponseModel.ok(sheepOrderDetailModel), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /**
     * @param customer     当前用户
     * @param requestModel 请求参数
     * @return 获取牧场详情
     * @author 米立林
     */
    @Authorization
    @RequestMapping(value = "/GetSheepVendorDetail", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ReponseModel> GetSheepVendorDetail(@CurrentUser Customer customer, HttpEntity<OnlyPrimaryIdRequestModel> requestModel) {
        try {
            SheepVendorViewModel sheepVendorViewModel = sheepService.GetPastureDetailById(customer.getId(), requestModel.getBody());
            return new ResponseEntity<>(ReponseModel.ok(sheepVendorViewModel), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    //endregion

}
