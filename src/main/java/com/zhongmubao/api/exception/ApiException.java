package com.zhongmubao.api.exception;

import com.zhongmubao.api.config.ResultStatus;

public class ApiException extends RuntimeException {
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

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
