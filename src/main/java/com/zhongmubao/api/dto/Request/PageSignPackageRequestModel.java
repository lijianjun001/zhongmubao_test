package com.zhongmubao.api.dto.Request;

public class PageSignPackageRequestModel {

    private int pageIndex;
    private String type;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
