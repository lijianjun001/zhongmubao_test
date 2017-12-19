package com.zhongmubao.api.web;

import com.zhongmubao.api.authorization.annotation.Authorization;
import com.zhongmubao.api.authorization.annotation.CurrentUser;
import com.zhongmubao.api.dto.request.my.menu.ListRequestModel;
import com.zhongmubao.api.dto.request.my.redpackage.RedPackageDetailRequestModel;
import com.zhongmubao.api.dto.request.my.redpackage.RedPackageGroupRequestModel;
import com.zhongmubao.api.dto.request.my.redpackage.RedPackageListRequestModel;
import com.zhongmubao.api.dto.request.my.transaction.TransactionDetailRequestModel;
import com.zhongmubao.api.dto.request.my.transaction.TransactionMonthlyBillRequestModel;
import com.zhongmubao.api.dto.request.my.transaction.TransactionRequestModel;
import com.zhongmubao.api.dto.response.ReponseModel;
import com.zhongmubao.api.dto.response.my.menu.ListViewModel;
import com.zhongmubao.api.dto.response.my.redpackage.RedPackageDetailViewModel;
import com.zhongmubao.api.dto.response.my.redpackage.RedPackageGroupViewModel;
import com.zhongmubao.api.dto.response.my.redpackage.RedPackageListViewModel;
import com.zhongmubao.api.dto.response.my.redpackage.RedPackageHistoryViewModel;
import com.zhongmubao.api.dto.response.my.transaction.TransactionDetailViewModel;
import com.zhongmubao.api.dto.response.my.transaction.TransactionListViewModel;
import com.zhongmubao.api.dto.response.my.transaction.TransactionMonthlyBillViewModel;
import com.zhongmubao.api.entity.Customer;
import com.zhongmubao.api.exception.ApiException;
import com.zhongmubao.api.service.CustomerService;
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
    private final CustomerService customerService;

    @Autowired
    public MyController(MenuService menuService, ReadPackageService readPackageService, CustomerService customerService) {
        this.menuService = menuService;
        this.readPackageService = readPackageService;
        this.customerService = customerService;
    }

    // region 个人中心菜单

    /***
     * 个人中心 菜单
     * @param customer 客户
     * @author xy
     * @return ReponseModel
     */
    @RequestMapping(value = "/menu/list", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> menuList(@CurrentUser Customer customer, HttpEntity<ListRequestModel> model) {
        try {
            ListViewModel listViewModel = menuService.list(customer, model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(listViewModel), HttpStatus.OK);
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
     * @return RedPackageGroupViewModel
     * @author 米立林
     */
    @RequestMapping(value = "/readPackage/group", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> readPackageGroup(@CurrentUser Customer customer, HttpEntity<RedPackageGroupRequestModel> model) {
        try {
            RedPackageGroupViewModel redPackageGroupModel = readPackageService.readPackageGroup(customer, model.getBody());
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
     * @return RedPackageListViewModel
     * @author 米立林
     */
    @RequestMapping(value = "/readPackage/groupList", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> readPackageGroupList(@CurrentUser Customer customer, HttpEntity<RedPackageListRequestModel> model) {
        try {
            RedPackageListViewModel redPackageList = readPackageService.readPackageGroupList(customer, model.getBody());
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
    @RequestMapping(value = "/readPackage/history", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> readPackageHistory(@CurrentUser Customer customer, HttpEntity<RedPackageGroupRequestModel> model) {
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
    @RequestMapping(value = "/readPackage/detail", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> readPackageDetail(@CurrentUser Customer customer, HttpEntity<RedPackageDetailRequestModel> model) {
        try {
            RedPackageDetailViewModel detailViewModel = readPackageService.readPackageDetail(customer, model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(detailViewModel), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /**
     * 排序红包列表
     *
     * @param customer 客户
     * @param model    请求model
     * @return RedPackageListViewModel
     * @author 米立林
     */
    @RequestMapping(value = "/readPackage/list", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> readPackageList(@CurrentUser Customer customer, HttpEntity<RedPackageListRequestModel> model) {
        try {
            RedPackageListViewModel redPackageList = readPackageService.readPackageList(customer, model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(redPackageList), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }
    //endregion

    //region 交易明细

    /**
     * 列表
     *
     * @param customer 客户
     * @param model    请求model
     * @return TransactionListViewModel
     * @author 米立林
     */
    @RequestMapping(value = "/transaction/list", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> transactionList(@CurrentUser Customer customer, HttpEntity<TransactionRequestModel> model) {
        try {
            TransactionListViewModel transactionListViewModel = customerService.transactionList(customer, model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(transactionListViewModel), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /**
     * 交易记录详情
     *
     * @param customer 客户
     * @param model    请求model
     * @return TransactionDetailViewModel
     * @author 米立林
     */
    @RequestMapping(value = "/transaction/detail", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> transactionDetail(@CurrentUser Customer customer, HttpEntity<TransactionDetailRequestModel> model) {
        try {
            TransactionDetailViewModel detailViewModel = customerService.transactionDetail(customer, model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(detailViewModel), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /**
     * 交易明细 -- 月账单
     *
     * @param customer 客户
     * @param model    请求model
     * @return TransactionDetailViewModel
     * @author 米立林
     */
    @RequestMapping(value = "/transaction/monthlyBill", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> transactionMonthlyBill(@CurrentUser Customer customer, HttpEntity<TransactionMonthlyBillRequestModel> model) {
        try {
            TransactionMonthlyBillViewModel viewModel = customerService.transactionMonthlyBill(customer, model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(viewModel), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    //endregion
}
