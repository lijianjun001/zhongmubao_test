package com.zhongmubao.api.components.hf.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


/**
 * 商户子账户查询结果
 *
 * @author 孙阿龙
 */
public class HfQueryAcctsResponse extends HfBaseResponse {
    /**
     * 版本
     */
    @SerializedName("Version")
    private String version;

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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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
