package com.zhongmubao.api.components.hf.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * 放还款对账响应对象
 *
 * @author 米立林
 */
public class HfReconciliationResponse extends HfBaseResponse {

    /**
     * 版本号
     */
    @SerializedName("Version")
    private String version;
    /**
     * LOANS：放款交易查询 REPAYMENT：还款交易查询
     */
    @SerializedName("QueryTransType")
    private String queryTransType;
    /**
     * YYYYMMDD 格式 BeginDate 和 EndDate 日期跨度不能大于 90 天
     */
    @SerializedName("BeginDate")
    private String beginDate;
    /**
     * YYYYMMDD 格式 BeginDate 和 EndDate 日期跨度不能大于 90 天
     */
    @SerializedName("EndDate")
    private String endDate;
    /**
     * 查询数据的所在页号，>0 的整数
     */
    @SerializedName("PageNum")
    private String pageNum;
    /**
     * 查询数据的所在页号，>0 且<=1000 的整数
     */
    @SerializedName("PageSize")
    private String pageSize;
    /**
     * 记录总条数
     */
    @SerializedName("TotalItems")
    private String totalItems;
    /**
     * 对账结果单
     */
    @SerializedName("ReconciliationDtoList")
    private ArrayList<HfReconciliationDto> reconciliationDtoList;
    /**
     * 文本
     */
    @SerializedName("PlainText")
    private String plainText;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getQueryTransType() {
        return queryTransType;
    }

    public void setQueryTransType(String queryTransType) {
        this.queryTransType = queryTransType;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(String totalItems) {
        this.totalItems = totalItems;
    }

    public ArrayList<HfReconciliationDto> getReconciliationDtoList() {
        return reconciliationDtoList;
    }

    public void setReconciliationDtoList(ArrayList<HfReconciliationDto> reconciliationDtoList) {
        this.reconciliationDtoList = reconciliationDtoList;
    }

    public String getPlainText() {
        return plainText;
    }

    public void setPlainText(String plainText) {
        this.plainText = plainText;
    }
}


//{"CmdId":"Reconciliation","RespCode":"000","RespDesc":"成功",
//        "ChkValue":"BDBE324E300C4B5EF9F9B083A8BE3E08DAC7C1969F5FCD4A22DF50BA1CBF2D3ADD47506A8F872EF0E8957336EB603D063F8DC9A24ED531C8575190F8795F6587284C9518FD53705A3FB8151625A8FAC91C10E1433DD9520577E08662B1EF230C545C331ED2E87EC267C3D6294DE96B7039F1884443D0A2AB10FEF96C633A6024",
//        "Version":null,"MerCustId":"6000060007633813","QueryTransType":"LOANS","BeginDate":"20171111","EndDate":"20171117","PageNum":"1","PageSize":"100","TotalItems":"2",
//        "ReconciliationDtoList":[
//        {"OrdId":"20171116111922722","OrdDate":"20171116","MerCustId":"6000060007633813","InvestCustId":"6000060007867339","BorrCustId":"6000060007653943","TransAmt":"1000.00","TransStat":"P","PnrDate":"20171116","PnrSeqId":"0024793129","DivDetails":[],"PrincipalAmt":null,"InterestAmt":null},
//        {"OrdId":"20171115145808992","OrdDate":"20171115","MerCustId":"6000060007633813","InvestCustId":"6000060007867339","BorrCustId":"6000060007653943","TransAmt":"1000.00","TransStat":"P","PnrDate":"20171115","PnrSeqId":"0024787846","DivDetails":[],"PrincipalAmt":null,"InterestAmt":null}
//        ],"PlainText":"Reconciliation0006000060007633813201711112017111711002LOANS"}