package com.zhongmubao.api.service.mp;

import com.zhongmubao.api.dto.request.mp.JsCode2SessionRequestModel;
import com.zhongmubao.api.dto.response.mp.JsCode2SessionViewModel;

/***
 * 微信服务
 * @author 孙阿龙
 */
public interface MPService {
    /**
     * 根据openId获取session和openid
     *
     * @param requestModel 请求参数
     * @return JsCode2SessionViewModel
     */
    JsCode2SessionViewModel jsCode2Session(JsCode2SessionRequestModel requestModel);
}
