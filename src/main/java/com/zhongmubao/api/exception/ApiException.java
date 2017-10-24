package com.zhongmubao.api.exception;

import com.zhongmubao.api.config.ResultStatus;

/**
 * 接口异常类
 *
 * @author 孙阿龙
 */
public class ApiException extends RuntimeException{
    private ResultStatus status;

    public ResultStatus getStatus() {
        return status;
    }

    public void setStatus(ResultStatus status) {
        this.status = status;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(ResultStatus status) {
        this.status = status;
    }

}
