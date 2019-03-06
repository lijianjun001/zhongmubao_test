package com.zhongmubao.api.service.impl.buySheep;

import com.zhongmubao.api.config.ResultStatus;
import com.zhongmubao.api.exception.ApiException;

public class BuySheepException extends ApiException {


    public BuySheepException(String message) {
        super(message);
    }
    public BuySheepException(ResultStatus state) {
        super(state);
    }

}
