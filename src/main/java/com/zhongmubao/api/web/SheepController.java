package com.zhongmubao.api.web;

import com.zhongmubao.api.authorization.annotation.Authorization;
import com.zhongmubao.api.authorization.annotation.CurrentUser;
import com.zhongmubao.api.dto.response.ReponseModel;
import com.zhongmubao.api.dto.response.my.MyPastureViewModel;
import com.zhongmubao.api.entity.Customer;
import com.zhongmubao.api.exception.ApiException;
import com.zhongmubao.api.service.SheepService;
import org.springframework.beans.factory.annotation.Autowired;
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

    //region 小程序接口
    /**
     * 我的资产
     *
     * @author 米立林
     */
    @RequestMapping(value = "/miniapps/myPasture", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> miniappsMyPasture(@CurrentUser Customer customer) {
        try {
            MyPastureViewModel viewModel = sheepService.miniappsMyPasture(customer);
            return new ResponseEntity<>(ReponseModel.ok(viewModel), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }
    //endregion
}
