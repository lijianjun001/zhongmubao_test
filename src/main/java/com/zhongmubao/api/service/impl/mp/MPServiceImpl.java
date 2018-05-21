package com.zhongmubao.api.service.impl.mp;

import com.zhongmubao.api.config.MPConfig;
import com.zhongmubao.api.dto.request.mp.JsCode2SessionRequestModel;
import com.zhongmubao.api.dto.response.mp.JsCode2SessionViewModel;
import com.zhongmubao.api.exception.ApiException;
import com.zhongmubao.api.service.mp.MPService;
import com.zhongmubao.api.util.HttpUtil;
import com.zhongmubao.api.util.SerializeUtil;
import com.zhongmubao.api.util.StringUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;

/***
 * MPService实现
 * @author 孙阿龙
 */
@Service
public class MPServiceImpl implements MPService {
    @Override
    public JsCode2SessionViewModel jsCode2Session(JsCode2SessionRequestModel requestModel) {
        JsCode2SessionViewModel jsCode2SessionViewModel = null;
        try {
            String jsonStr = HttpUtil.get("https://api.weixin.qq.com/sns/jscode2session?appid=" + MPConfig.APP_ID + "&secret=" + MPConfig.APP_SECRET + "&js_code=" + requestModel.getCode() + "&grant_type=authorization_code");
            String openId = SerializeUtil.getJsonStringValueByKey(jsonStr, "openid");
            String sessionKey = SerializeUtil.getJsonStringValueByKey(jsonStr, "session_key");
            if (StringUtil.isNullOrEmpty(openId) || StringUtil.isNullOrEmpty(sessionKey)) {
                throw new ApiException("获取openId失败！");
            }
            jsCode2SessionViewModel = new JsCode2SessionViewModel();
            jsCode2SessionViewModel.openId = openId;
            jsCode2SessionViewModel.session_key = sessionKey;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsCode2SessionViewModel;
    }
}
