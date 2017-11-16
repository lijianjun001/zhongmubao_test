package com.zhongmubao.api.components.hf.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * 充值对账
 *
 * @author 米立林
 */
public class HfSaveReconciliationResponse extends HfBaseResponse {

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
    private ArrayList<HfSaveReconciliationDto> saveReconciliationDtoList;

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

    public ArrayList<HfSaveReconciliationDto> getSaveReconciliationDtoList() {
        return saveReconciliationDtoList;
    }

    public void setSaveReconciliationDtoList(ArrayList<HfSaveReconciliationDto> saveReconciliationDtoList) {
        this.saveReconciliationDtoList = saveReconciliationDtoList;
    }
}
