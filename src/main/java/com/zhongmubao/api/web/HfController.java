package com.zhongmubao.api.web;

import com.zhongmubao.api.components.hf.Core;
import com.zhongmubao.api.components.hf.request.HfQueryAcctsRequest;
import com.zhongmubao.api.components.hf.request.HfQueryBalanceBgRequest;
import com.zhongmubao.api.dto.request.system.TouTiaoAdvRequestModel;
import com.zhongmubao.api.dto.response.ReponseModel;
import com.zhongmubao.api.exception.ApiException;
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
@RequestMapping("/hf")
public class HfController {

    @RequestMapping(value = "/test", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ReponseModel> test(HttpEntity<TouTiaoAdvRequestModel> model) {
        try {
//            HfQueryBalanceBgRequest requestModel =new HfQueryBalanceBgRequest();
//            requestModel.setMerCustId("6000060007633813");
//            requestModel.setUsrCustId("6000060007653943");
//            Core.QueryBalanceBg(requestModel);
            HfQueryAcctsRequest requestModel = new HfQueryAcctsRequest();
            requestModel.setMerCustId("6000060007633813");
            Core.QueryAccts(requestModel);
            return null;
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

}
