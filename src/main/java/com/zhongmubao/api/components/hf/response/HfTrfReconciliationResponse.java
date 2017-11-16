package com.zhongmubao.api.components.hf.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * 商户扣款对账结果
 *
 * @author 孙阿龙
 */
public class HfTrfReconciliationResponse extends HfBaseResponse {
    @SerializedName("BeginDate")
    public String beginDate;

    @SerializedName("EndDate")
    public String EndDate;

    @SerializedName("PageNum")
    public String PageNum;

    @SerializedName("PageSize")
    public String PageSize;

    @SerializedName("TotalItems")
    public String TotalItems;

    @SerializedName("TrfReconciliationDtoList")
    public ArrayList<HfTrfReconciliationDto> TrfReconciliationDtoList;

    @SerializedName("PlainText")
    public String PlainText;

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public String getPageNum() {
        return PageNum;
    }

    public void setPageNum(String pageNum) {
        PageNum = pageNum;
    }

    public String getPageSize() {
        return PageSize;
    }

    public void setPageSize(String pageSize) {
        PageSize = pageSize;
    }

    public String getTotalItems() {
        return TotalItems;
    }

    public void setTotalItems(String totalItems) {
        TotalItems = totalItems;
    }

    public ArrayList<HfTrfReconciliationDto> getTrfReconciliationDtoList() {
        return TrfReconciliationDtoList;
    }

    public void setTrfReconciliationDtoList(ArrayList<HfTrfReconciliationDto> trfReconciliationDtoList) {
        TrfReconciliationDtoList = trfReconciliationDtoList;
    }

    public String getPlainText() {
        return PlainText;
    }

    public void setPlainText(String plainText) {
        PlainText = plainText;
    }

}
