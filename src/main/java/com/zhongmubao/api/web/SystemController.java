package com.zhongmubao.api.web;

import com.zhongmubao.api.authorization.annotation.Authorization;
import com.zhongmubao.api.authorization.annotation.CurrentUser;
import com.zhongmubao.api.dto.Request.SendSmsCodeRequestModel;
import com.zhongmubao.api.dto.Request.PageIndexRequestModel;
import com.zhongmubao.api.dto.Request.Address.SystemDistrictRequestModel;
import com.zhongmubao.api.dto.Request.TouTiaoAdvRequestModel;
import com.zhongmubao.api.dto.Response.Address.ListSystemDistrictModel;
import com.zhongmubao.api.dto.Response.ReponseModel;
import com.zhongmubao.api.dto.Response.Notice.PageNoticeModel;
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

@RestController
@RequestMapping("/system")
public class SystemController {
    private final SystemService systemService;

    @Autowired
    public SystemController(SystemService systemService) {
        this.systemService = systemService;
    }

    /**
     * 通知公告列表
     * @param model 参数对象
     * @author 米立林
     * @return
     */
    @RequestMapping(value = "/pageNotice", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ReponseModel> pageNotice(HttpEntity<PageIndexRequestModel> model) {
        try {
            PageNoticeModel pageNoticeModel = systemService.pageNotice(model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(pageNoticeModel), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /**
     * 获取省市区列表ByParentCode
     * 省份列表 parentCode不传或传空值
     * @param model 给参数parentCode赋值
     * @author 米立林
     * @return 省/市/区集合
     */
    @RequestMapping(value = "/getSystemDistrict", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ReponseModel> getSystemDistrict(HttpEntity<SystemDistrictRequestModel> model) {
        try {

            ListSystemDistrictModel listSystemDistrictModel = systemService.getSystemDistrictList(model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(listSystemDistrictModel), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /**
     * 发送验证码
     * @param model 手机号码
     * @author 米立林 2017-10-09
     * @return 成功or失败
     */
    @RequestMapping(value = "/sendSmsCode", method = RequestMethod.POST, consumes = "application/json")
    @Authorization
    public ResponseEntity<ReponseModel> sendSmsCode(@CurrentUser Customer customer, HttpEntity<SendSmsCodeRequestModel> model) {
        try {
            systemService.sendSmsCode(customer,model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

    /**
     *
     * @param model 手机号码
     * @author 米立林 2017-10-09
     * @return 成功or失败
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




}
