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