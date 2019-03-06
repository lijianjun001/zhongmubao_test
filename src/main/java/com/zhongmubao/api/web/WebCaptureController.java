package com.zhongmubao.api.web;

import com.zhongmubao.api.dto.response.ReponseModel;
import com.zhongmubao.api.exception.ApiException;
import com.zhongmubao.api.dto.request.RequestModel;
import com.zhongmubao.api.service.WebCaptureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/webCapture")
public class WebCaptureController {


    private WebCaptureService webCaptureService;

    @Autowired
    public WebCaptureController(WebCaptureService webCaptureService) {

        this.webCaptureService = webCaptureService;
    }

    @RequestMapping(value = "/autoBuySheep", method = RequestMethod.GET)
    public ResponseEntity<ReponseModel> autoBuySheep(RequestModel requestModel) {

        try {
            webCaptureService.autoBuySheep(requestModel.getTelephone(), requestModel.getPassword(),requestModel.getProjectNum());
            return new ResponseEntity<>(ReponseModel.ok(), HttpStatus.OK);
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }


}







