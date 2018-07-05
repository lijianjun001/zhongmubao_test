package com.zhongmubao.api.components.hf;

/**
 * 类说明.
 *
 * @author guolin.chen
 * @version v1.0.0
 * @since v1.0.0
 */
public enum CFCARespEnum {

    FAILED("999", "系统异常"), SUCCESS("000", "验签成功"), CERT_EXPRIED("001", "证书过期"), CERT_ILLEGAL("002",
            "证书非法，不符合X509规范"), MER_FAILED("003", "当前证书不属于颁发者"), SIGN_ERROR("004", "签名无效"), CERT_REVOKED("005", "证书被吊销");

    private String code;
    private String message;

    CFCARespEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
