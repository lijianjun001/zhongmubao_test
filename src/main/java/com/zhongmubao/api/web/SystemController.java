package com.zhongmubao.api.web;

import com.zhongmubao.api.authorization.annotation.Authorization;
import com.zhongmubao.api.authorization.annotation.CurrentUser;
import com.zhongmubao.api.config.system.ConfigurationFields;
import com.zhongmubao.api.dto.request.system.*;
import com.zhongmubao.api.dto.response.ReponseModel;
import com.zhongmubao.api.dto.response.system.ShareInfoViewModel;
import com.zhongmubao.api.entity.Customer;
import com.zhongmubao.api.exception.ApiException;
import com.zhongmubao.api.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 系统控制器
 *
 * @author 孙阿龙
 */
@RestController
@RequestMapping("/system")
public class SystemController {
    private final SystemService systemService;

    @Autowired
    public SystemController(SystemService systemService) {
        this.systemService = systemService;
    }

    /**
     * 头条广告
     *
     * @param model 请求实体
     * @return TouTiaoAdvRequestModel
     * @author xy
     */
    @RequestMapping(value = "/touTiaoAdv", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ReponseModel> touTiaoAdv(HttpEntity<TouTiaoAdvRequestModel> model) {
        try {
            systemService.touTiaoAdv(model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /**
     * 平台跟踪
     *
     * @param model 请求实体
     * @return TouTiaoAdvRequestModel
     * @author 孙阿龙
     */
    @Authorization
    @RequestMapping(value = "/platformTracking", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ReponseModel> platformTracking(@CurrentUser Customer customer, HttpEntity<PlatformTrackingRequestModel> model) {
        try {
            systemService.platformTracking(customer, model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /**
     * 分享
     *
     * @param model 请求实体
     * @return ShareInfoViewModel
     * @author 米立林
     */
    @RequestMapping(value = "/shareInfo", method = RequestMethod.POST, consumes = "application/json")
    @Authorization(onlyGetCustomer = true)
    public ResponseEntity<ReponseModel> shareInfo(@CurrentUser Customer customer, HttpEntity<ShareInfoRequestModel> model) {
        try {
            ShareInfoViewModel viewModel = systemService.shareInfo(customer, model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(viewModel), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /**
     * 发送验证码
     *
     * @param model 请求实体
     * @author 米立林
     */
    @RequestMapping(value = "/sendSms", method = RequestMethod.POST, consumes = "application/json")
    @Authorization(onlyGetCustomer = true)
    public ResponseEntity<ReponseModel> sendSms(@CurrentUser Customer customer, HttpEntity<SendSmsRequestModel> model) {
        try {
            systemService.sendSms(customer, model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }


//    @RequestMapping(value = "/test", method = RequestMethod.POST, consumes = "application/json")
//    public ResponseEntity<ReponseModel> testTransaction() {
//        try {
//            // 发送消息
//            /*messageSender.sendDataToQueue("topic.justin.exchange", "topic.justin.queue", "insert Queue");*/
//
//            return new ResponseEntity<>(ReponseModel.ok(), HttpStatus.OK);
//        } catch (ApiException ex) {
//            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
//        } catch (Exception ex) {
//            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
//        }
//    }
}
