package com.zhongmubao.api.dto.response;

import com.zhongmubao.api.config.ResultStatus;
import org.apache.log4j.Logger;

/**
 * 封装json对象，所有返回结果都使用它
 *
 * @author 孙阿龙
 */
public class ReponseModel {

    /**
     * 返回码
     */
    private int code;

    /**
     * 返回结果描述
     */
    private String message;

    /**
     * 返回内容
     */
    private Object data;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public ReponseModel(int code, String message) {
        this.code = code;
        this.message = message;
        this.data = "";
    }

    public ReponseModel(int code, String message, Object content) {
        this.code = code;
        this.message = message;
        this.data = content;
    }

    public ReponseModel(ResultStatus status) {
        this.code = status.getCode();
        this.message = status.getMessage();
        this.data = "";
    }

    public ReponseModel(ResultStatus status, Object content) {
        this.code = status.getCode();
        this.message = status.getMessage();
        this.data = content;
    }

    public static ReponseModel ok(Object content) {
        return new ReponseModel(ResultStatus.SUCCESS, content);
    }

    public static ReponseModel ok() {
        return new ReponseModel(ResultStatus.SUCCESS);
    }

    public static ReponseModel error(ResultStatus error) {
        return new ReponseModel(error);
    }

    public static ReponseModel error(Exception ex, Class clazz) {
        Logger.getLogger(clazz).error(ex.getMessage(), ex);
        return new ReponseModel(ResultStatus.FAIL);
    }
}
