package com.zhongmubao.api.components.hf.request;

import com.zhongmubao.api.components.hf.request.enmu.HfSendSmsCodeBusiType;
import com.zhongmubao.api.components.hf.request.enmu.HfSendSmsTempType;

/**
 * 发送短信码请求
 *
 * @author 孙阿龙
 */
public class HfSendSmsCodeRequest extends HfBaseRequest {

    /**
     * 用户客户号
     */
    private String usrCustId;

    /**
     * 业务类型
     */
    private HfSendSmsCodeBusiType busiType;

    /**
     * 银行卡号
     */
    private String openAcctId;

    /**
     * 手机号
     */
    private String usrMp;

    /**
     * 短信类型
     */
    private HfSendSmsTempType smsTempType;

    public String getUsrCustId() {
        return usrCustId;
    }

    public void setUsrCustId(String usrCustId) {
        this.usrCustId = usrCustId;
    }

    public HfSendSmsCodeBusiType getBusiType() {
        return busiType;
    }

    public void setBusiType(HfSendSmsCodeBusiType busiType) {
        this.busiType = busiType;
    }

    public String getOpenAcctId() {
        return openAcctId;
    }

    public void setOpenAcctId(String openAcctId) {
        this.openAcctId = openAcctId;
    }

    public String getUsrMp() {
        return usrMp;
    }

    public void setUsrMp(String usrMp) {
        this.usrMp = usrMp;
    }

    public HfSendSmsTempType getSmsTempType() {
        return smsTempType;
    }

    public void setSmsTempType(HfSendSmsTempType smsTempType) {
        this.smsTempType = smsTempType;
    }
}
