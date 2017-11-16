package com.zhongmubao.api.components.hf.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * 取现对账
 *
 * @author 米立林
 */
public class HfCashReconciliationResponse extends HfBaseResponse {
    /**
     * 版本
     */
    @SerializedName("Version")
    private String version;
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
     * 取现的手续费收取方
     * U--向用户收取 M--向商户收取
     */
    @SerializedName("FeeObj")
    private String feeObj;
    /**
     * 对账结果单
     */
    @SerializedName("CashReconciliationDtoList")
    private ArrayList<HfCashReconciliationDto> cashReconciliationDtoList;
    /**
     * 文本
     */
    @SerializedName("plainText")
    private String plainText;

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

    public String getFeeObj() {
        return feeObj;
    }

    public void setFeeObj(String feeObj) {
        this.feeObj = feeObj;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPlainText() {
        return plainText;
    }

    public void setPlainText(String plainText) {
        this.plainText = plainText;
    }

    public ArrayList<HfCashReconciliationDto> getCashReconciliationDtoList() {
        return cashReconciliationDtoList;
    }

    public void setCashReconciliationDtoList(ArrayList<HfCashReconciliationDto> cashReconciliationDtoList) {
        this.cashReconciliationDtoList = cashReconciliationDtoList;
    }
}
