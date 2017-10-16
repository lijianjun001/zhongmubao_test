package com.zhongmubao.api.web;

import com.zhongmubao.api.dto.Request.PageNoticeRequestModel;
import com.zhongmubao.api.dto.Request.Address.SystemDistriceRequestModel;
import com.zhongmubao.api.dto.Response.Address.ListSystemDistrictModel;
import com.zhongmubao.api.dto.Response.ReponseModel;
import com.zhongmubao.api.dto.Response.Notice.PageNoticeModel;
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
@RequestMapping("/System")
public class SystemController {
    private final SystemService systemService;

    @Autowired
    public SystemController(SystemService systemService) {
        this.systemService = systemService;
    }

    /**
     * 获取消息通知列表
     *
     * @param model 参数对象
     * @return
     * @author 米立林
     */
    @RequestMapping(value = "/pageNotice", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ReponseModel> pageNotice(HttpEntity<PageNoticeRequestModel> model) {
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
     *
     * @param model 给参数parentCode赋值
     * @return 省/市/区集合
     * @author 米立林
     */
    @RequestMapping(value = "/GetSystemDistrictByParentCode", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ReponseModel> GetSystemDistrictByParentCode(HttpEntity<SystemDistriceRequestModel> model) {
        try {

            ListSystemDistrictModel listSystemDistrictModel = systemService.GetSystemDistrictByParentCode(model.getBody());
            return new ResponseEntity<>(ReponseModel.ok(listSystemDistrictModel), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }


}
