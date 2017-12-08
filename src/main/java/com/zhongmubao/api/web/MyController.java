package com.zhongmubao.api.web;

import com.zhongmubao.api.authorization.annotation.Authorization;
import com.zhongmubao.api.authorization.annotation.CurrentUser;
import com.zhongmubao.api.dto.request.my.RealNameRequestModel;
import com.zhongmubao.api.dto.request.my.center.PersonalCenterRequestModel;
import com.zhongmubao.api.dto.request.my.readpackage.ReadPackageDetailRequestModel;
import com.zhongmubao.api.dto.request.my.readpackage.ReadPackageGroupRequestModel;
import com.zhongmubao.api.dto.request.my.readpackage.ReadPackageListRequestModel;
import com.zhongmubao.api.dto.response.ReponseModel;
import com.zhongmubao.api.dto.response.my.RealNameViewModel;
import com.zhongmubao.api.dto.response.my.center.PersonalCenterViewModel;
import com.zhongmubao.api.dto.response.my.readpackage.ReadPackageDetailViewModel;
import com.zhongmubao.api.dto.response.my.readpackage.ReadPackageGroupViewModel;
import com.zhongmubao.api.dto.response.my.readpackage.ReadPackageListViewModel;
import com.zhongmubao.api.dto.response.my.readpackage.RedPackageHistoryViewModel;
import com.zhongmubao.api.entity.Customer;
import com.zhongmubao.api.exception.ApiException;
import com.zhongmubao.api.service.my.MenuService;
import com.zhongmubao.api.service.my.ReadPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 我的控制器
 *
 * @author 孙阿龙
 */
@RestController
@RequestMapping("/my")
public class MyController {

    private final MenuService menuService;
    private final ReadPackageService readPackageService;

    @Autowired
    public MyController(MenuService menuService, ReadPackageService readPackageService) {
        this.menuService = menuService;
        this.readPackageService = readPackageService;
    }

    // region 个人中心列表

    /***
     * 个人中心 列表
     * @param customer 客户
     * @author xy
     * @return ReponseModel
     */
    @RequestMapping(value = "/personalCenter", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> personalCenter(@CurrentUser Customer customer, HttpEntity<PersonalCenterRequestModel> model) {
        try {
            PersonalCenterViewModel personalCenterViewModel = menuService.list(customer, model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(personalCenterViewModel), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }
    //endregion

    //region 个人中心 实名OR开户

    /***
     * 个人中心 实名OR开户
     * @param customer 客户
     * @author xy
     * @return ReponseModel
     */
    @RequestMapping(value = "/realName", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> realName(@CurrentUser Customer customer, HttpEntity<RealNameRequestModel> model) {
        try {
            RealNameViewModel realNameViewModel = menuService.choosePaymentRealName(customer, model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(realNameViewModel), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }
    //endregion

    //region 个人中心红包
    /**
     * 我的红包分组
     *
     * @param customer 客户
     * @param model    请求model
     * @author 米立林
     * @return ReadPackageGroupViewModel
     */
    @RequestMapping(value = "/readPackageGroup", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> readPackageGroup(@CurrentUser Customer customer, HttpEntity<ReadPackageGroupRequestModel> model) {
        try {
            ReadPackageGroupViewModel redPackageGroupModel = readPackageService.readPackageGroup(customer, model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(redPackageGroupModel), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /**
     * 分组红包列表
     *
     * @param customer 客户
     * @param model    请求model
     * @author 米立林
     * @return ReadPackageListViewModel
     */
    @RequestMapping(value = "/readPackageList", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> readPackageList(@CurrentUser Customer customer, HttpEntity<ReadPackageListRequestModel> model) {
        try {
            ReadPackageListViewModel redPackageList = readPackageService.readPackageList(customer, model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(redPackageList), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /**
     * 已过期红包
     *
     * @param customer 客户
     * @param model    请求model
     * @return 结果
     */
    @RequestMapping(value = "/readPackageHistory", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> readPackageHistory(@CurrentUser Customer customer, HttpEntity<ReadPackageGroupRequestModel> model) {
        try {
            RedPackageHistoryViewModel readPackageExpiredModel = readPackageService.readPackageHistory(customer, model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(readPackageExpiredModel), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /**
     * 红包详情请求接口
     *
     * @param customer 客户
     * @param model    请求model
     * @return 结果
     */
    @RequestMapping(value = "/readPackageDetail", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> readPackageDetail(@CurrentUser Customer customer, HttpEntity<ReadPackageDetailRequestModel> model) {
        try {
            ReadPackageDetailViewModel detailViewModel = readPackageService.readPackageDetail(customer, model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(detailViewModel), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }
    //endregion
}
