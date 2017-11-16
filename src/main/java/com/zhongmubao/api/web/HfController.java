package com.zhongmubao.api.web;

import com.zhongmubao.api.components.hf.HfCore;
import com.zhongmubao.api.components.hf.request.*;
import com.zhongmubao.api.dto.request.system.TouTiaoAdvRequestModel;
import com.zhongmubao.api.dto.response.ReponseModel;
import com.zhongmubao.api.exception.ApiException;
import com.zhongmubao.api.util.DateUtil;
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
            HfTrfReconciliationRequest request = new HfTrfReconciliationRequest();
            request.setBeginDate(DateUtil.strToDate("2017-11-11 00:00:00"));
            request.setEndDate(DateUtil.strToDate("2017-11-17 00:00:00"));
            request.setPageNum(1);
            request.setPageSize(100);
            HfCore.trfReconciliation(request);
//            HfQueryTransStatRequest request =new HfQueryTransStatRequest();
//            request.setOrdId("20171115175643195");
//            request.setOrdDate(DateUtil.strToDate("2017-11-15 00:00:00"));
//            request.setQueryTransType(HfQueryTransStatType.CASH);
//            HfCore.queryTransStat(request);
//            HfSendSmsCodeRequest sendSmsCodeRequest = new HfSendSmsCodeRequest();
//            sendSmsCodeRequest.setBusiType(HfSendSmsCodeBusiType.RECHARGE);
//            sendSmsCodeRequest.setUsrMp("18811496596");
//            sendSmsCodeRequest.setUsrCustId("6000060007867339");
//            HfCore.sendSmsCode(sendSmsCodeRequest);
////            HfQueryBalanceBgRequest requestModel =new HfQueryBalanceBgRequest();
////            requestModel.setMerCustId("6000060007633813");
////            requestModel.setUsrCustId("6000060007653943");
////            HfCore.QueryBalanceBg(requestModel);
//            HfCore.directRecharge(null);
////            HfQueryAcctsRequest requestModel = new HfQueryAcctsRequest();
////            requestModel.setMerCustId("6000060007633813");
////            HfCore.queryaccts(requestModel);
            return null;
        } catch (ApiException ex) {
            return new ResponseEntity<>(ReponseModel.error(ex.getStatus()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ReponseModel.error(ex, this.getClass()), HttpStatus.OK);
        }
    }

}
