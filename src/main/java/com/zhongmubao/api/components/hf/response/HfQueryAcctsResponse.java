package com.zhongmubao.api.components.hf.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


/**
 * 商户子账户查询结果
 *
 * @author 孙阿龙
 */
public class HfQueryAcctsResponse {

    /**
     * 商户号
     */
    @SerializedName("CmdId")
    private String cmdId;

    /**
     * 响应码
     */
    @SerializedName("RespCode")
    private String respCode;

    /**
     * 响应描述
     */
    @SerializedName("RespDesc")
    private String respDesc;

    /**
     * 签名
     */
    @SerializedName("ChkValue")
    private String chkValue;

    /**
     * 版本
     */
    @SerializedName("Version")
    private String version;

    /**
     * 商户号
     */
    @SerializedName("MerCustId")
    private String merCustId;

    /**
     * 账户明细
     */
    @SerializedName("AcctDetails")
    private ArrayList<HfQueryAcctDetail> acctDetails;

    /**
     * 验签签名
     */
    @SerializedName("PlaintStr")
    private String plaintStr;

    public String getCmdId() {
        return cmdId;
    }

    public void setCmdId(String cmdId) {
        this.cmdId = cmdId;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespDesc() {
        return respDesc;
    }

    public void setRespDesc(String respDesc) {
        this.respDesc = respDesc;
    }

    public String getChkValue() {
        return chkValue;
    }

    public void setChkValue(String chkValue) {
        this.chkValue = chkValue;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMerCustId() {
        return merCustId;
    }

    public void setMerCustId(String merCustId) {
        this.merCustId = merCustId;
    }


    public String getPlaintStr() {
        return plaintStr;
    }

    public void setPlaintStr(String plaintStr) {
        this.plaintStr = plaintStr;
    }

    public ArrayList<HfQueryAcctDetail> getAcctDetails() {
        return acctDetails;
    }

    public void setAcctDetails(ArrayList<HfQueryAcctDetail> acctDetails) {
        this.acctDetails = acctDetails;
    }
}
