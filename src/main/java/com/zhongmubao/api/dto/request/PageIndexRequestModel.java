package com.zhongmubao.api.dto.request;

/**
 * 分页索引请求参数
 * @author 米立林
 */
public class PageIndexRequestModel {
    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    private int pageIndex;
}
