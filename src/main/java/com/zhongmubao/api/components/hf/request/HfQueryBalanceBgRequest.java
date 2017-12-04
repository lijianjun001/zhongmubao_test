package com.zhongmubao.api.components.hf.request;

/**
 * 余额查询接口请求参数
 *
 * @author 孙阿龙
 */
public class HfQueryBalanceBgRequest {
    /**
     * 客户号
     */
    private String usrCustId;

    public String getUsrCustId() {
        return usrCustId;
    }

    public void setUsrCustId(String usrCustId) {
        this.usrCustId = usrCustId;
    }
}
